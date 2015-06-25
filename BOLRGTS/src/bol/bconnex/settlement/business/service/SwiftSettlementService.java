package bol.bconnex.settlement.business.service;

import java.io.IOException;

import bol.bconnex.settlement.data.entity.Account;
import bol.bconnex.settlement.data.entity.Settlement;

public interface SwiftSettlementService {	
	public void setPath(String path);
	public void setLastSettleDate(String lastSettleDate);
	public void swiftFileOutPut(Settlement from, Settlement to,String name) throws Exception;
	public void accountSettlement(Settlement bolSettle, Account account, String name)
			throws Exception;
	public void swiftReadOutput() throws IOException;
}
