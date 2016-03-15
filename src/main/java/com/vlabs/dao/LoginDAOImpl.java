package com.vlabs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vlabs.model.Users;
import com.vlabs.model.Users_Passwords;
import com.vlabs.utility.MailSender;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	MailSender mailSender;

	@Override
	@Transactional
	public boolean checkLogin(String userEmail, String password) {
		List<String> list = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Boolean userFound = false;

		String SQL_QUERY = "from Users as u where u.emailId=? and u.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0, userEmail);
		query.setParameter(1, password);
		list = query.list();

		if ((list != null) && (list.size() > 0))
			userFound = true;

		return userFound;
	}

	@Override
	@Transactional
	public boolean checkLoginNew(String userEmail, String password){
		Session session=sessionFactory.openSession();
		Users user=new Users();
		user=(Users) session.createCriteria(Users.class).add(Restrictions.or(Restrictions.eq("emailId", userEmail),Restrictions.eq("mobileNum", userEmail))).uniqueResult();
		if(user!=null)
			return true;
		
		else
		return false;
	}
	@Override
	@Transactional
	public boolean checkLoginUsingMobileNum(String mobileNum, String password) {
		List<String> list = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Boolean userFound = false;

		String SQL_QUERY = "from Users as u where u.mobileNum=? and u.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0, mobileNum);
		query.setParameter(1, password);
		list = query.list();

		if ((list != null) && (list.size() > 0))
			userFound = true;

		return userFound;
	}
	@Override
	@Transactional
	public Users validateEmail(String emailId) {
		/*
		 * MailSender mailSender=new MailSender();
		 */
		// List<String[]> list=new ArrayList();
		Session session = sessionFactory.openSession();
		boolean validEmailId = false;
		Users users = (Users) session.createCriteria(Users.class)
				.add(Restrictions.eq("emailId", emailId)).uniqueResult();
		/*if (users != null) {
			mailSender.mailService(users);
			validEmailId = true;
		} else {
			validEmailId = false;
		}*/
		return users;

	}

	@Override
	@Transactional
	public boolean addEmployee(Users user) {
		// Users users=null;
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(user);
		Users savedUser=(Users)session.createCriteria(Users.class).add(Restrictions.eq("emailId", user.getEmailId())).uniqueResult();
		Users_Passwords password=new Users_Passwords();
		password.setId(savedUser.getId());
		password.setPassword(user.getPassword());
		session.saveOrUpdate(password);
		return true;
	}

	@Override
	public List<Users> getList() {
		Session session = sessionFactory.openSession();
		List<Users> users = (List<Users>) session.createCriteria(Users.class)
				.list();
		// List<Users> users=session.createQuery("from Users").list();
		return users;
	}

	@Override
	public Users getUserById(int id) {
		System.out.println("-------------in dao");
		Session session = sessionFactory.openSession();
		Users user = (Users) session.createCriteria(Users.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		System.out.println("-------------in dao after query");
		return user;
	}

	@Override
	public boolean update(Users user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {

			session.close();
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(Users user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(user);
			transaction.commit();
			;
		} catch (Exception e) {
			System.out.println(e);
		} finally {

			session.close();
		}
		return false;

	}

	@Override
	@Transactional
	public Users getUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Users user=new Users();
		user=(Users) session.createCriteria(Users.class).add(Restrictions.eq("emailId", emailId)).uniqueResult();
		return user;
	}

	@Override
	@Transactional
	public Users_Passwords getPasswordsById(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Users_Passwords passwords=new Users_Passwords();
		passwords=(Users_Passwords)session.createCriteria(Users_Passwords.class).add(Restrictions.eq("id", id)).uniqueResult();
		return passwords;
	}

	
}
