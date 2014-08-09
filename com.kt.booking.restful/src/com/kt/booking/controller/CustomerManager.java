package com.kt.booking.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.kt.booking.model.Customer;
import com.kt.booking.util.HibernateUtil;

public class CustomerManager  {

	public Customer updateCustomer (Customer customer)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(customer);
		session.getTransaction().commit();
		return customer;
	}

	public int addCustomer (Customer customer)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		return 200;
	}

	public List <Customer> getCustomersList()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List <Customer> customers = null;

		try {
			Criteria criteria = session.createCriteria(Customer.class);

			customers= criteria.list();

		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();	
		return customers;
	}
	
	public List <Customer> searchCustomerById(Long customerId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List <Customer> customers = null;

		try {

			Criteria criteria = session.createCriteria(Customer.class);
			if (customerId != null)
			{
				criteria.add(Restrictions.eq("id",customerId));
			}

			customers=(List<Customer>)criteria.list();

		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();	
		return customers;
		
	}

	public List <Customer> searchCustomer(Long customerId, String firstName , String lastName, String email)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List <Customer> customers = null;

		try {

			Criteria criteria = session.createCriteria(Customer.class);
			if (customerId != null)
			{
				criteria.add(Restrictions.eq("customerId",customerId));
			}
			if (StringUtils.isNotBlank(firstName)){
				criteria.add(Restrictions.ilike("firstName","%"+firstName+"%"));
			}
			if (StringUtils.isNotBlank(lastName)){
				criteria.add(Restrictions.ilike("lastName","%"+lastName+"%"));
			}
			if (StringUtils.isNotBlank(email)){
				criteria.add(Restrictions.ilike("email","%"+email+"%"));
			}

			customers=(List<Customer>)criteria.list();

		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();	
		return customers;
	}

	public Customer deleteCustomer (Long id)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Customer customer = (Customer) session.load(Customer.class, id);

		if (customer != null)
		{
			session.delete(customer);
		}
		session.getTransaction().commit();
		return customer;
	}

}
