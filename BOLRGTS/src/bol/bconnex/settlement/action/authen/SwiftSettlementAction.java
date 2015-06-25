package bol.bconnex.settlement.action.authen;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bol.bconnex.settlement.business.service.AccountService;
import bol.bconnex.settlement.business.service.SettlementService;
import bol.bconnex.settlement.business.service.SwiftSettlementService;
import bol.bconnex.settlement.business.util.UtilityService;
import bol.bconnex.settlement.data.entity.Account;
import bol.bconnex.settlement.data.entity.Settlement;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Namespace("/authenticated/settlement")
@ResultPath(value="/")
public class SwiftSettlementAction extends ActionSupport implements ModelDriven<List<Account>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BOLBIC = "LPDRLALA";
	private static final String BCELBIC = "COEBLALA";
	private static final double CRITERIA = 10E-3;
	private List<Account> accounts = new ArrayList<Account>();
	private double fee;
	@Autowired
	private SwiftSettlementService swiftSettlementService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SettlementService settlementService;
	public double getFee(){
		return fee;
	}
	public void setFee(double fee){
		this.fee = fee;
	}
	public void setSwiftSettlementService(SwiftSettlementService swiftSettlementService){
		this.swiftSettlementService = swiftSettlementService;
	}
	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}
	public void setSettlementService(SettlementService settlementService){
		this.settlementService = settlementService;
	}
	public void setAccounts(List<Account> accounts){
		this.accounts = accounts;
	}
	public List<Account> getAccounts(){
		return accounts;
	}
	@Override
	public List<Account> getModel() {
		return accounts;
	}
	@Action(value="settle", results={
			@Result(name="success",location="success.jsp"),
			@Result(name="error",location="error.jsp")
		})
	
	public String execute() throws Exception{
		// set path
		String path = ServletActionContext.getServletContext().getRealPath("/swift/");
		String lastSettleDate = settlementService.getLastSettleDate();
		swiftSettlementService.setPath(path);
		swiftSettlementService.setLastSettleDate(lastSettleDate);
		// get BOL Account information
		Account bol = accountService.getAccount(BOLBIC);
		Settlement bolSettle = new Settlement();
		bolSettle.setAccount(bol);
		bolSettle.setSettleDate(UtilityService.getDate());
		bolSettle.setTxnDate(UtilityService.backDate());
		
		// retrieve data and set amount to settlement entity
		double fee = 0;
		for(Account account : accounts){
			String name = account.getShortName();
			double amt = Double.parseDouble(account.getAmt().replaceAll(",", ""));
			account.setAmount(amt);
			swiftSettlementService.accountSettlement(bolSettle, account, name);
		
			fee-=account.getAmount();
		}
		// fee settle
		Account bcel = accountService.getAccount(BCELBIC);
		String name = "BCONXFEE";
		bcel.setAmount(fee);
		swiftSettlementService.accountSettlement(bolSettle, bcel, name);
		setFee(fee);
		Account acc = new Account();
		acc = bcel;
		acc.setName(name);
		acc.setShortName(name);
		accounts.add(acc);
		double valid = 0.0;
		for(Account account : accounts){
			valid+=account.getAmount();
		}
		
		return Math.abs(valid)<CRITERIA?SUCCESS:ERROR;
	}
	
	
}
