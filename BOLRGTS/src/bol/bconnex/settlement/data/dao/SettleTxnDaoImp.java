package bol.bconnex.settlement.data.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bol.bconnex.settlement.data.entity.SettleTxn;
@Repository("settleTransactionDaoImp")
public class SettleTxnDaoImp implements SettleTxnDao {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional
	@Override
	public void save(SettleTxn settle) {
		hibernateTemplate.save(settle);
	}
	@Override
	public SettleTxn getSettleTxn(String rrn) {
		String hql = "from SettleTransaction st where st.rrn= :rrn";
		String paramName = "rrn";
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, rrn)).get(0);
	}
	private List<SettleTxn> toList(final List<?> beans){
		if(beans==null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		SettleTxn[] list = new SettleTxn[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
	@Override
	public void update(SettleTxn settle) {
		hibernateTemplate.update(settle);
	}
	@Override
	public List<String> getFileNames() {
		FileNameListCallback action = new FileNameListCallback();
		return hibernateTemplate.execute(action);
	}
	private class FileNameListCallback implements HibernateCallback<List<String>>{

		@SuppressWarnings({ "unchecked", "deprecation" })
		@Override
		public List<String> doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT SWIFTNAME AS SWFNAME FROM TXN_DETAIL";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addScalar("SWFNAME", Hibernate.STRING);
			return sqlQuery.list();
		}
		
	}
	@Override
	public List<SettleTxn> getSettleTxn(Date date) {
		String hql = "from SettleTxn s where s.sysDate = :sysDate order by s.id";
		String paramName = "sysDate";
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, date));
	}
}
