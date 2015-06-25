package bol.bconnex.settlement.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import bol.bconnex.settlement.data.entity.User;
@Repository("userDao")
public class UserDaoImp implements UserDao {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public boolean authen(User usr) {
		UserInnerAction action = new UserInnerAction(usr);
		int count = (int) hibernateTemplate.execute(action);
		return count > 0 ? true : false;
	}
	// private class for user check
	private class UserInnerAction implements HibernateCallback<Integer>{
		private User user;
		public UserInnerAction(User user){
			this.user = user;
		}
		@Override
		public Integer doInHibernate(Session session) throws HibernateException,
				SQLException {
			UsrInnerWork work = new UsrInnerWork(user);
			session.doWork(work);
			return work.getNum();
		}
	}
	private class UsrInnerWork implements Work{
		private User usr;
		private int num;
		public UsrInnerWork(User usr){
			this.usr = usr;
		}
		public int getNum(){
			return num;
		}
		@Override
		public void execute(Connection conn) throws SQLException {
			//String sql = "SELECT COUNT(*) AS NUM FROM USERS WHERE USRID = ? AND PASSWD = SHA1(?)";
			String sql = "SELECT COUNT(*) AS NUM FROM USERS WHERE USRID = ? AND PASSWD = PASSWORD(?)";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, usr.getUsrId());
			stat.setString(2, usr.getPasswd());
			ResultSet ret = stat.executeQuery();
			num = 0;
			while(ret.next()){
				num = ret.getInt("NUM");
			}
		}
	}
}
