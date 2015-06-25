package bol.bconnex.settlement.data.dao;

import java.sql.Date;
import java.util.List;

import bol.bconnex.settlement.data.entity.Settlement;

public interface SettlementDao {
	public void save(Settlement settlement);
	public List<Settlement> getSettlement(Date settleDate);
	public List<Settlement> getSettlement(String rrn);
	public String getLastSettleDate();
}
