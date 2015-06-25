package bol.bconnex.settlement.action.authen;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bol.bconnex.settlement.business.service.SettleTxnService;
import bol.bconnex.settlement.data.entity.SettleTxn;

import com.opensymphony.xwork2.ActionSupport;
@Controller
@Namespace("/authenticated/check")
@ResultPath(value="/")
public class SwiftSettlementCheckAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SettleTxnService settleTxnService;
	private Date date;
	private List<SettleTxn> settleTxns;
	public void setDate(Date date){
		this.date = date;
	}
	public List<SettleTxn> getSettleTxns(){
		return settleTxns;
	}
	public void setSettleTxnService(SettleTxnService settleTxnService){
		this.settleTxnService = settleTxnService;
	}
	@Action(value="checkResult",results={
			@Result(name="success", location="resultFetch.jsp"),
	})
	public String execute() throws Exception{
		java.sql.Date sdate = new java.sql.Date(date.getTime());
		settleTxns = settleTxnService.getSettleTxns(sdate);
		return SUCCESS;
	}
}
