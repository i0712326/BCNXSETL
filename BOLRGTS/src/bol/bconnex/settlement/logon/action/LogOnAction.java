package bol.bconnex.settlement.logon.action;

import java.util.List;

import bol.bconnex.settlement.data.entity.Member;
import bol.bconnex.settlement.data.entity.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LogOnAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<Member> members;
	public void setUser(User user){
		this.user = user;
	}
	@Override
	public User getModel() {
		return user;
	}
	public String execute() throws Exception{
		
		return null;
	}
}
