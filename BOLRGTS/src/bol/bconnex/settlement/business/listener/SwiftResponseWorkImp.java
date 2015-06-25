package bol.bconnex.settlement.business.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.common.Logger;
import bol.bconnex.settlement.business.service.FtpAccessService;
import bol.bconnex.settlement.business.service.SettleTxnService;
import bol.bconnex.settlement.business.service.SettlementService;
import bol.bconnex.settlement.business.util.UtilityService;
import bol.bconnex.settlement.data.entity.SettleTxn;
import bol.bconnex.settlement.data.entity.Settlement;
public class SwiftResponseWorkImp implements SwiftResponseWork {
	private static final Logger logger = Logger.getLogger(SwiftResponseWorkImp.class);
	private FtpAccessService ftpAccessService;
	private SettleTxnService settleTxnService;
	private SettlementService settlementService;
	private String path;
	public void setFtpAccessService(FtpAccessService ftpAccessService){
		this.ftpAccessService = ftpAccessService;
	}
	public void setSettleTxnService(SettleTxnService settleTxnService){
		this.settleTxnService = settleTxnService;
	}
	public void setSettlementService(SettlementService settlementService){
		this.settlementService = settlementService;
	}
	public void setPath(String path){
		this.path = path;
	}
	@Override
	public void swiftResCheck() {
		// get list of processed file's name
		List<String> list = settleTxnService.getFileList();
		// access FTP to retrieve files
		List<String> localPaths = ftpAccessService.swiftDownload(list, path);
		for(String name : localPaths){
			try {
				SettleTxn settleTxn = buildSettleTxn(name);
				settleTxnService.save(settleTxn);
			} catch (IOException e) {
				logger.debug("Exception occur while try to process swift settlement out put file", e);
			}
			
		}
	}
	
	private static final String PATTERN 	="(\\{4:.+\\})"; 					// MT202 message body
	private static final String FIELD21 	= ":21:BCON([0-9]{12}):"; 			// reference number
	private static final String FIELD76 	= ":76:STAT\\/([0-9]{10})";			// transaction date time and response
	private static final String SUCC		= "SETL\\/[0-9]{10}";				// success response
	private static final String ERRP		= "ERRP";							// error response
	
	private static final Pattern pattern 	= Pattern.compile(PATTERN);
	private static final Pattern field21 	= Pattern.compile(FIELD21);
	private static final Pattern field76 	= Pattern.compile(FIELD76);
	private static final Pattern succ	 	= Pattern.compile(SUCC);
	private static final Pattern err	 	= Pattern.compile(ERRP);
	
	private static final double crit = 10E-3;
	
	private Matcher matcher;
	
	private SettleTxn buildSettleTxn(String name) throws IOException{
		File file = new File(name);
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line = null;
		String msg = "";
		while((line = buffer.readLine())!=null){
			msg+=line;
		}
		buffer.close();
		matcher = pattern.matcher(msg);
		SettleTxn settleTxn = new SettleTxn();
		if(matcher.find()){
			String body = matcher.group();
			// Set Reference number
			matcher = field21.matcher(body);
			if(matcher.find()){
				String rrn = matcher.group(1);
				settleTxn.setRrn(rrn);
			}
			// Set transaction time
			matcher = field76.matcher(body);
			if(matcher.find()){
				String time = matcher.group(1);
				Timestamp dateTime = UtilityService.strToTimestamp(time);
				settleTxn.setDateTime(dateTime);
			}
			// Check Response
			matcher = succ.matcher(body);
			if(matcher.find())
				settleTxn.setRes("0");
			// Check Error
			matcher = err.matcher(body);
			if(matcher.find())
				settleTxn.setRes("1");
			
			List<Settlement> settlements = settlementService.getSettlements(settleTxn.getRrn());
			Settlement settle01 = settlements.get(0);
			double amount = settle01.getCredit()+settle01.getDebit();
			Date date = settle01.getSettleDate();
			settleTxn.setAmount(amount);
			settleTxn.setSysDate(date);
			Settlement settle02 = settlements.get(1);
			if(settle01.getCredit() > crit){
				settleTxn.setTo(settle01.getAccount().getAccount());
				settleTxn.setToName(settle01.getAccount().getShortName());
				settleTxn.setFrom(settle02.getAccount().getAccount());
				settleTxn.setFromName(settle02.getAccount().getShortName());
			}
			else{
				settleTxn.setTo(settle02.getAccount().getAccount());
				settleTxn.setToName(settle02.getAccount().getShortName());
				settleTxn.setFrom(settle01.getAccount().getAccount());
				settleTxn.setFromName(settle01.getAccount().getShortName());
			}
			// set swift source name
			String fileName = file.getName();
			settleTxn.setSwiftName(fileName);
		}
		return settleTxn;
	}
}
