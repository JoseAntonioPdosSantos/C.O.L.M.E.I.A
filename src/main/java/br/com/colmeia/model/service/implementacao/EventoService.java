package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.EventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.service.generics.Service;
import br.com.colmeia.model.utils.HibernateUtil;

public class EventoService extends Service<Evento, Long, EventoHibernateDAO> {

	public boolean validarSalvarAlterar(Evento entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getCoordenador() == null)
			throw new Exception("Desculpe! O campo 'Coordenador' é obrigatório");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if (entity.getDataInicial() == null)
			throw new Exception("Desculpe! O campo 'Data Inicial' é obrigatório");
		if (entity.getDataFinal() == null)
			throw new Exception("Desculpe! O campo 'Data Final' é obrigatório");
		return true;
	}

	@Override
	public List<Evento> buscar(Evento entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion coordenador = null;
		Criterion dataInicial = null;
		Criterion dataFinal = null;
		Criterion ativo = null;

		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null) {
				if (!entity.getNome().trim().isEmpty())
					nome = Restrictions.ilike("nome", "%" + entity.getNome() + "%");
			}
			if (entity.getCoordenador() != null) {
				coordenador = Restrictions.eq("coordenador", entity.getCoordenador());
			}
			if (entity.getDataInicial() != null) {
				dataInicial = Restrictions.eq("dataInicial", entity.getDataInicial());
			}
			if (entity.getDataFinal() != null) {
				dataFinal = Restrictions.eq("dataFinal", entity.getDataFinal());
			}
			if (entity.getAtivo() != null) {
				ativo = Restrictions.eq("ativo", entity.getAtivo());
			}
		}

		return getDao().findByCriteria(id, nome, coordenador, dataInicial, dataFinal, ativo);
	}

	public List<Evento> buscarEventosEncerrados() {
		Criterion dataInicial = Restrictions.le("dataInicial", HibernateUtil.getCurrentDate());
		Criterion ativo = Restrictions.eq("ativo", true);
		return getDao().findByCriteria(dataInicial, ativo);
	}

	public List<Evento> buscarEventosVigentes() {
		Criterion dataInicial = Restrictions.ge("dataInicial", HibernateUtil.getCurrentDate());
		Criterion ativo = Restrictions.eq("ativo", true);
		return getDao().findByCriteria(dataInicial, ativo);
	}

	@Override
	public EventoHibernateDAO getDao() {
		return new EventoHibernateDAO();
	}

	@Override
	public boolean validarExcluir(Evento entity) throws Exception {
		return true;
	}

	private boolean apagarAtividadesEvento(Evento entity) throws Exception {
		for (AtividadeEvento atividadeEvento : entity.getAtividadesEvento()) {
			new AtividadeEventoService().apagar(atividadeEvento);
		}
		return true;
	}

	public void apagar(Evento entity) throws Exception {
		if (validarExcluir(entity)) {
			if (apagarAtividadesEvento(entity))
				super.apagar(entity);
		}
	}

}
