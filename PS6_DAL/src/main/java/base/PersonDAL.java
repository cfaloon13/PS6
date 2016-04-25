package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;


import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(per);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return per;
	}

	public static void deletePerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;

		try {
			tx = session.beginTransaction();

			PersonDomainModel per = (PersonDomainModel) session.get(PersonDomainModel.class, perID);
			session.delete(per);
			

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;

		try {
			tx = session.beginTransaction();

			session.update(per);

			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return per;
	}
}

	public static ArrayList<PersonDomainModel> getPerson() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();

		try {
			tx = session.beginTransaction();

			List persons = session.createQuery("FROM PersonDomainModel where personId = :id ").list();
			for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				pers.add(per);

			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return perGet;

	}


