package br.com.colmeia.model.persistence.service.imp;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.AtividadeEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class AtividadeEventoService extends Service<AtividadeEvento, Long, AtividadeEventoHibernateDAO> {

	public boolean validarEntity(AtividadeEvento entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if (entity.getEvento() == null)
			throw new Exception("Desculpe! É necessário informar um evento");
		if (entity.getDataInicial() == null)
			throw new Exception("Desculpe! O campo 'Data Inicial' é obrigatório");
		if (entity.getDataFinal() == null)
			throw new Exception("Desculpe! O campo 'Data Final' é obrigatório");
		if (entity.getDataInicial().before(entity.getEvento().getDtini())
				|| entity.getDataInicial().after(entity.getEvento().getDtfim_()))
			throw new Exception("Desculpe! A data inicial da atividade deve entre a data inicial e final do evento");
		if (entity.getDataFinal().before(entity.getEvento().getDtini())
				|| entity.getDataFinal().after(entity.getEvento().getDtfim_()))
			throw new Exception("Desculpe! A data inicial da atividade deve entre a data inicial e final do evento");
		if (entity.getDataInicial().after(entity.getDataFinal()))
			throw new Exception("Desculpe! A data inicial não pode ser depois da data final");
		if(entity.getDescricao() == null || entity.getDescricao().trim().isEmpty()){
			throw new Exception("Desculpe! O campo 'Descrição' é obrigatório");
		}
		if(entity.getPalestrante() == null || entity.getPalestrante().trim().isEmpty()){
			throw new Exception("Desculpe! O campo 'Palestrante' é obrigatório");
		}
		return true;
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
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null) {
				if (!entity.getNome().trim().isEmpty())
					nome = Restrictions.eq("nome", entity.getNome());
			}
			if (entity.getEvento() != null) {
				evento = Restrictions.eq("evento", entity.getEvento());
			}
			if (entity.getDataInicial() != null) {
				dataInicial = Restrictions.eq("datainicial", entity.getDataInicial());
			}
			if (entity.getDataFinal() != null) {
				dataFinal = Restrictions.eq("datafinal", entity.getDataFinal());
			}
			if (entity.getQuantidadeInscritos() != null) {
				quantidadeInscritos = Restrictions.eq("quantidadeinscritos", entity.getQuantidadeInscritos());
			}
			if (entity.getPalestrante() != null) {
				palestrante = Restrictions.eq("palestrante", entity.getPalestrante());
			}
			if (entity.getIngresso() != null) {
				ingresso = Restrictions.eq("ingresso", entity.getIngresso());
			}
		}
		return getDao().findByCriteria(id, nome, evento, dataInicial, dataFinal, quantidadeInscritos, palestrante,
				ingresso);
	}

	public List<AtividadeEvento> buscarAtividadeEventosEncerrados() {
		Criterion dtini = Restrictions.le("datainicial", new Date(Calendar.getInstance().getTimeInMillis()));
		return getDao().findByCriteria(dtini);
	}

	public List<AtividadeEvento> buscarAtividadeEventosVigentes() {
		Criterion dtini = Restrictions.le("datainicial", new Date(Calendar.getInstance().getTimeInMillis()));
		return getDao().findByCriteria(dtini);
	}

	@Override
	public AtividadeEventoHibernateDAO getDao() {
		return new AtividadeEventoHibernateDAO();
	}

}
