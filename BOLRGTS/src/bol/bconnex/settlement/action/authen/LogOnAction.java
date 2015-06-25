package bol.bconnex.settlement.action.authen;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bol.bconnex.settlement.business.service.AccountService;
import bol.bconnex.settlement.business.service.UserService;
import bol.bconnex.settlement.data.entity.Account;
import bol.bconnex.settlement.data.entity.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Namespace("/authenticated")
@ResultPath(value="/")
public class LogOnAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	private User user;
	private List<Account> accounts;
	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}
	public void setAccounts(List<Account> members){
		this.accounts = members;
	}
	public List<Account> getAccounts(){
		return accounts;
	}
	@Override
	public User getModel() {
		return user;
	}
	@Action(value="logon", results={
			@Result(name="success",location="welcome.jsp"),
			@Result(name="error",location="/logon.jsp",type="redirect")
		})
	public String execute() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		boolean check = userService.checkUser(user);
		session.setAttribute("user", user);
		return check ? SUCCESS : ERROR;
	}
}
