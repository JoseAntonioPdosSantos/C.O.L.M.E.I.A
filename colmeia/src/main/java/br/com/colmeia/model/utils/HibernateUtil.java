package br.com.colmeia.model.utils;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

public abstract class HibernateUtil {

	protected static EntityManagerFactory emFactory;
	protected static EntityManager em;
	private static Session session;

	static {
		emFactory = Persistence.createEntityManagerFactory("persistence-unit");
		em = emFactory.createEntityManager();
		if (session == null)
			session = (Session) em.getDelegate();
	}

	protected Session getSession() {
		return session;
	}

	protected void beginTransaction() {
		em.getTransaction().begin();
	}

	protected void commitTransaction() {
		em.getTransaction().commit();
	}

	protected void rollbackTransaction() {
		em.getTransaction().rollback();
	}
	
	public static Timestamp getCurrentDate(){
		Query query = em.createQuery("SELECT NOW() FROM Curso ");
		return (Timestamp) query.getSingleResult();
	}

}