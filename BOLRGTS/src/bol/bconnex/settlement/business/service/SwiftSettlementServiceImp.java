package bol.bconnex.settlement.business.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bol.bconnex.settlement.business.util.UtilityService;
import bol.bconnex.settlement.data.entity.Account;
import bol.bconnex.settlement.data.entity.Settlement;
@Service("swiftSettlementService")
public class SwiftSettlementServiceImp implements SwiftSettlementService {
	private static final int MAX = 1000000;
	private static final int MIN = 100000;
	@Autowired
	private SettlementService settlementService;
	@Autowired
	private AccountService accountService;
	private String path;
	private String lastSettleDate;
	public void setPath(String path){
		this.path = path;
	}
	public void setAccountService(AccountService accountService){
		this.accountService = accountService;
	}
	public void setSettlementService(SettlementService settlementService){
		this.settlementService = settlementService;
	}
	@Override
	public void setLastSettleDate(String lastSettleDate){
		this.lastSettleDate = lastSettleDate;
	}
	@Override
	public void swiftFileOutPut(Settlement from, Settlement to, String name)
			throws Exception {
		String backDate = UtilityService.backStrDate();
		String directoryName = path+"/"+backDate;
		File directory = new File(directoryName);
		if(!directory.exists()){
			if(!directory.mkdir()){
				throw new IOException("Could not create directory to store file");
			}
		}
		String currentDate = UtilityService.getStrDate();
		String header 	= "{1:F01COEBLALAAATM}{2:I202LPDRLALAXRTGN}{3:{113:0010}{108:PARTICIPANT}}{4:";
		String body 	= ":20:BCON";
		String body1 	= ":21:NONREF";
		String body2 	= ":32A:";
		String body3	= ":53A:/D/";
		String body31	= ":53D:/D/";
		String body4	= null;
		String body5	= ":58A:/";
		String body51	= ":58D:/";
		String body6	= null;
		String body7	= ":72:/CODTYPTR/001";
		String body8	= "/BNF/Bconnex Net Settlment";
		String body9	="//TXNDATE ";
		String body10	= "-}";
		
		String fileName = directoryName+"/"+name+backDate+".swift";
		File file = new File(fileName);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		body	=	body+from.getRrn();
		body2	=	body2+currentDate+"LAK"+(int)from.getDebit()+",";
		if(from.getAccount().getSwiftReg().equals("Y"))
			body3	= 	body3+from.getAccount().getAccount();
		else
			body3	= 	body31+from.getAccount().getAccount();
		body4	= 	from.getAccount().getBic();
		if(to.getAccount().getSwiftReg().equals("Y"))
			body5	= 	body5+to.getAccount().getAccount();
		else
			body5	= 	body51+to.getAccount().getAccount();
		body6	= 	to.getAccount().getBic();
		// set description for settlement 
		if(name.equals("BCONXFEE"))
			body8 = body8+" FEE";
		// set description for transaction date
		if(backDate.equals(lastSettleDate))
			body9 = body9+backDate;
		else
			body9 = body9 + lastSettleDate + " to " + backDate;
		// write to file
		pw.println(header);
		pw.println(body);
		pw.println(body1);
		pw.println(body2);
		pw.println(body3);
		pw.println(body4);
		pw.println(body5);
		pw.println(body6);
		pw.println(body7);
		pw.println(body8);
		pw.println(body9);
		pw.println(body10);
		pw.close();
			
	}
	@Override
	public void accountSettlement(Settlement bolSettle, Account account, String name)
			throws Exception {
		Settlement settle = new Settlement();
		Account acc = accountService.getAccount(account.getBic());
		settle.setAccount(acc);
		settle.setSettleDate(UtilityService.getDate());
		settle.setTxnDate(UtilityService.backDate());
		double amount = account.getAmount();
		double absAmount = Math.abs(amount);
		String trace = String.format("%06d", new Random().nextInt(MAX-MIN)+MIN);
		String rrn = UtilityService.buildRefference(trace);
		// set amount for settle
		settle.setRrn(rrn);
		bolSettle.setRrn(rrn);
		if(amount>0){
			bolSettle.setDebit(absAmount);
			bolSettle.setCredit(0);
			
			settle.setDebit(0);
			settle.setCredit(absAmount);
			// set description
			String desc = "BCONEX|"+bolSettle.getAccount().getAccount()+"|"+bolSettle.getAccount().getBic()+"|"+rrn;
			String str = "BCONEX|"+settle.getAccount().getAccount()+"|"+settle.getAccount().getBic()+"|"+rrn;
			bolSettle.setDescription(desc);
			settle.setDescription(str);
			// settle for each Member
			settlementService.save(bolSettle,settle);
			swiftFileOutPut(bolSettle, settle, name);
		}
		else{
			settle.setDebit(absAmount);
			settle.setCredit(0);
			
			bolSettle.setDebit(0);
			bolSettle.setCredit(absAmount);
			String desc = "BCONEX|"+bolSettle.getAccount().getAccount()+"|"+bolSettle.getAccount().getBic()+"|"+rrn;
			String str = "BCONEX|"+settle.getAccount().getAccount()+"|"+settle.getAccount().getBic()+"|"+rrn;
			bolSettle.setDescription(desc);
			settle.setDescription(str);
			settlementService.save(settle, bolSettle);
			swiftFileOutPut(settle, bolSettle, name);
		}
	}
	public void postTxnDetail(Settlement from,Settlement to){}
	@Override
	public void swiftReadOutput() throws IOException {}
}
