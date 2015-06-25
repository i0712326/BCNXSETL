package bol.bconnex.settlement.data.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import bol.bconnex.settlement.data.entity.Account;
@Repository("AccountDao")
public class AccountDaoImp implements AccountDao {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public List<Account> getAccounts() {
		String hql = "from Account";
		return toList(hibernateTemplate.find(hql));
	}
	@Override
	public List<Account> getAccountsNotEqual(String bic) {
		String hql = "from Account a where a.bic != :bic";
		String paramName = "bic";
		String value = bic;
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, value));
	}
	@Override
	public Account getAccount(String bic) {
		String hql = "from Account a where a.bic = :bic";
		String paramName = "bic";
		Account account =toList(hibernateTemplate.findByNamedParam(hql, paramName, bic)).get(0);
		return account;
	}
	private List<Account> toList(final List<?> beans){
		if(beans==null)  return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		Account[] list = new Account[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
}
