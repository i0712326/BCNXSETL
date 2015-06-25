package bol.bconnex.settlement.business.service;

import java.sql.Date;
import java.util.List;

import bol.bconnex.settlement.data.entity.SettleTxn;


public interface SettleTxnService {
	public void save(SettleTxn settle);
	public SettleTxn getTransaction(String rrn);
	public void update(SettleTxn settle);
	public List<String> getFileList();
	public List<SettleTxn> getSettleTxns(Date date);
}
