package com.clothing.sarees.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.clothing.sarees.model.Customer;

public class CustomerDao implements CustomerDaoInterface<Customer, Integer> {

	private Session currentSession;
	private Transaction currentTransaction;

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public SessionFactory getSessionFactory() {
		try {
			// For XML mapping
			// return new Configuration().configure().buildSessionFactory();

			// For Annotation
			return new AnnotationConfiguration().configure().buildSessionFactory();

		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void persist(Customer entity) {
		getCurrentSession().save(entity);
	}

	public void update(Customer entity) {
		getCurrentSession().update(entity);
	}

	public Customer findById(int id) {
		Customer Customer = (Customer) getCurrentSession().get(Customer.class, id);
		return Customer;
	}

	public void delete(Customer entity) {
		getCurrentSession().delete(entity);

	}

	public List<Customer> findAll() {
		List<Customer> Customer = (List<Customer>) getCurrentSession().createQuery("from Customer").list();
		return Customer;
	}

	public void deleteAll() {
		 List<Customer> entityList = findAll();
		  for (Customer entity : entityList) {
		  delete(entity);
	}
	}

}
