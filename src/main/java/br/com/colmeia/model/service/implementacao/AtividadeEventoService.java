package br.com.colmeia.model.service.implementacao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.AtividadeEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.service.generics.Service;
import br.com.colmeia.model.service.implementacao.UsuarioEventoService;
import br.com.colmeia.model.utils.HibernateUtil;

public class AtividadeEventoService extends Service<AtividadeEvento, Long, AtividadeEventoHibernateDAO> {

	public boolean validarSalvarAlterar(AtividadeEvento entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if(entity.getQuantidadeInscritos() == null || entity.getQuantidadeInscritos()<= 0)
			throw new Exception("Desculpe! O número máximo de inscritos deve ser maior que zero (0)");
		
		if (entity.getEvento() == null)
			throw new Exception("Desculpe! É necessário informar um evento");
		if (entity.getDataInicial() == null)
			throw new Exception("Desculpe! O campo 'Data Inicial' é obrigatório");
		if (entity.getDataFinal() == null)
			throw new Exception("Desculpe! O campo 'Data Final' é obrigatório");
		if (entity.getDataInicial().before(entity.getEvento().getDataInicial())
				|| entity.getDataInicial().after(entity.getEvento().getDataFinal()))
			throw new Exception("Desculpe! A data inicial da atividade deve entre a data inicial e final do evento");
		if (entity.getDataFinal().before(entity.getEvento().getDataInicial())
				|| entity.getDataFinal().after(entity.getEvento().getDataFinal()))
			throw new Exception("Desculpe! A data inicial da atividade deve entre a data inicial e final do evento");
		if (entity.getDataInicial().after(entity.getDataFinal()))
			throw new Exception("Desculpe! A data inicial não pode ser depois da data final");
		if(entity.getDescricao() == null || entity.getDescricao().trim().isEmpty()){
			throw new Exception("Desculpe! O campo 'Descrição' é obrigatório");
		}
		if(entity.getPalestrante() == null){
			throw new Exception("Desculpe! O campo 'Palestrante' é obrigatório");
		}
		if(!verificaSala(entity)){
			throw new Exception("Já existe uma atividade marcada nesta sala durante este período");
		}
		if(!verificaPalestrante(entity)){
			throw new Exception("Já existe uma atividade marcada para este palestrante durante este período");
		}
		if(minuteConverter(diferenciarData(entity.getDataInicial(), entity.getDataFinal())) < 5){
			throw new Exception("Atividade deve ser maior que 5 minutos");
		}
		
		
		return true;
	}

	public List<AtividadeEvento> buscarAtividadeEventoPorDataFim(Timestamp dataFinal){
		AtividadeEvento atividadeEvento = new AtividadeEvento();
		atividadeEvento.setDataFinal(dataFinal);
		try {
			return buscar(atividadeEvento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AtividadeEvento> buscar(AtividadeEvento entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion evento = null;
		Criterion dataInicial = null;
		Criterion dataFinal = null;
		Criterion quantidadeInscritos = null;
		Criterion palestrante = null;
		Criterion ingresso = null;
		Criterion ativo = null;

		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null) {
				if (!entity.getNome().trim().isEmpty())
					nome = Restrictions.ilike("nome", "%" + entity.getNome()+ "%");
			}
			if (entity.getEvento() != null) {
				evento = Restrictions.eq("evento", entity.getEvento());
			}
			if (entity.getDataInicial() != null) {
				dataInicial = Restrictions.eq("dataInicial", entity.getDataInicial());
			}
			if (entity.getDataFinal() != null) {
				dataFinal = Restrictions.eq("dataFinal", entity.getDataFinal());
			}
			if (entity.getQuantidadeInscritos() != null) {
				quantidadeInscritos = Restrictions.eq("quantidadeInscritos", entity.getQuantidadeInscritos());
			}
			if (entity.getPalestrante() != null) {
				palestrante = Restrictions.eq("palestrante", entity.getPalestrante());
			}
			if (entity.getIngresso() != null) {
				ingresso = Restrictions.eq("ingresso", entity.getIngresso());
			}
			if(entity.getAtivo() != null){
				ativo = Restrictions.eq("ativo", entity.getAtivo());
			}
			
		}
		return getDao().findByCriteria(id, nome, evento, dataInicial, dataFinal, quantidadeInscritos, palestrante,
				ingresso,ativo);
	}

	public List<AtividadeEvento> buscarAtividadeEventosEncerrados() {
		Criterion dataInicial = Restrictions.le("dataInicial", HibernateUtil.getCurrentDate());
		return getDao().findByCriteria(dataInicial);
	}

	public List<AtividadeEvento> buscarAtividadeEventosVigentes() {
		Criterion dataFinal = Restrictions.ge("dataFinal", HibernateUtil.getCurrentDate());
		Criterion ativo = Restrictions.eq("ativo",true);
		return getDao().findByCriteria(dataFinal,ativo);
	}

	public boolean verificaSala(AtividadeEvento entity){
		List<AtividadeEvento> lista = buscarAtividadeEventosVigentes();
		for (AtividadeEvento atividade : lista){
			if (entity.getSala().equals(atividade.getSala())) {
				if (entity.getDataInicial().equals(atividade.getDataInicial())){
					return false;
				}
				if (entity.getDataFinal().equals(atividade.getDataFinal())){
					return false;
				}
				if (entity.getDataFinal().before(atividade.getDataInicial())){
					continue;
				}
				if (entity.getDataInicial().after(atividade.getDataFinal())){
					continue;
				}
				return false;
			}
		}
		return true;
	}
	
	public boolean verificaPalestrante(AtividadeEvento entity){
		List<AtividadeEvento> lista = buscarAtividadeEventosVigentes();
		for (AtividadeEvento atividade : lista){
			if (entity.getPalestrante().equals(atividade.getPalestrante())) {
				if (entity.getDataFinal().equals(atividade.getDataFinal())){
					return false;
				}
				if (entity.getDataInicial().equals(atividade.getDataInicial())){
					return false;
				}
				if (entity.getDataFinal().before(atividade.getDataInicial())){
					continue;
				}
				if (entity.getDataInicial().after(atividade.getDataFinal())){
					continue;
				}
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public AtividadeEventoHibernateDAO getDao() {
		return new AtividadeEventoHibernateDAO();
	}
	
	private long diferenciarData(Date dataInicial, Date dataFinal) {
		return Math.abs(dataFinal.getTime() - dataInicial.getTime());
	}
	
	private long minuteConverter(long value) {
		return value / 1000 / 60;
	}
	
	public boolean verificaInscrito(AtividadeEvento entity){
		List<UsuarioEvento> lista = new UsuarioEventoService().getUsuarioEventoPorAtividadeEvento(entity);
		for(UsuarioEvento usuarioEvento : lista){
			if(entity.equals(usuarioEvento.getAtividadeEvento())){
				return false;
			}
			
		}
		return true;
	}
		
	@Override
	public boolean validarExcluir(AtividadeEvento entity) throws Exception {
		if(!verificaInscrito(entity)){
			return false;
			
		}
		if(!verificaInscrito(entity)){
			throw new Exception("Existem inscritos,atividade não pode ser excluida");
		}
		return true;
	}

}