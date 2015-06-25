package bol.bconnex.settlement.action.authen;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bol.bconnex.settlement.business.service.AccountService;
import bol.bconnex.settlement.data.entity.Account;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Namespace("/authenticated/settlement")
@ResultPath(value="/")
public class SettleMenuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4098137668406782026L;
	private static final String BOLBIC="LPDRLALA";
	private List<Account> accounts;
	@Autowired
	private AccountService accountService;
	public void setAccounts(List<Account> accounts){
		this.accounts = accounts;
	}
	public List<Account> getAccounts(){
		return accounts;
	}
	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}
	@Action(value="settleMenu",results={
			@Result(name="success",location="settlepage.jsp"),
			@Result(name="error",location="error.jsp"),
	})
	public String execute() throws Exception{
		setAccounts(accountService.getAccountsNotEquals(BOLBIC));
		return SUCCESS;
	}
}
