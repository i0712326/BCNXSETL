package bol.bconnex.settlement.data.dao;

import java.sql.Date;
import java.util.List;

import bol.bconnex.settlement.data.entity.SettleTxn;

public interface SettleTxnDao {
	public void save(SettleTxn settle);
	public SettleTxn getSettleTxn(String rrn);
	public void update(SettleTxn settle);
	public List<String> getFileNames();
	public List<SettleTxn> getSettleTxn(Date date);
}
