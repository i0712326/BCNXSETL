package bol.bconnex.settlement.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bol.bconnex.settlement.business.util.UtilityService;
import bol.bconnex.settlement.data.entity.Settlement;
@Repository("settlementDao")
public class SettlementDaoImp implements SettlementDao {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional
	@Override
	public void save(Settlement settlement) {
		hibernateTemplate.save(settlement);
	}

	@Override
	public List<Settlement> getSettlement(Date settleDate) {
		String hql = "from Settlement s where s.settleDate = :settleDate";
		String paramName = "settleDate";
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, settleDate));
	}

	@Override
	public List<Settlement> getSettlement(String rrn) {
		String hql = "from Settlement s where s.rrn = :rrn";
		String paramName = "rrn";
		return toList(hibernateTemplate.findByNamedParam(hql,paramName,rrn));
	}
	@Override
	public String getLastSettleDate() {
		LastSettleDateCallback action = new LastSettleDateCallback();
		return (String )hibernateTemplate.execute(action);
	}
	private class LastSettleDateCallback implements HibernateCallback<String>{
		@Override
		public String doInHibernate(Session session) throws HibernateException,
				SQLException {
			LastSettleDateWork work = new LastSettleDateWork();
			session.doWork(work);
			return work.getLastSettleDate();
		}
		private class LastSettleDateWork implements Work{
			private Date lastSettleDate;
			@Override
			public void execute(Connection conn) throws SQLException {
				String sql = "SELECT MAX(SETTLEDATE) AS SETTLEDATE FROM SETTLEMENT_TXN ORDER BY SETTLEDATE DESC;";
				PreparedStatement stat = conn.prepareStatement(sql);
				
				ResultSet rs = stat.executeQuery();
				while(rs.next()){
					this.lastSettleDate = rs.getDate("SETTLEDATE");
				}
			}
			public String getLastSettleDate(){
				return UtilityService.dateToStr(this.lastSettleDate);
			}
		}
	}
	private List<Settlement> toList(final List<?> beans){
		if(beans == null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		Settlement[] list = new Settlement[size];
		list = beans.toArray(list);
		return Arrays.asList(list);
	}
}
