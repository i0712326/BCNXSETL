package bol.bconnex.settlement.business.service;

import java.sql.Date;
import java.util.List;

import bol.bconnex.settlement.data.entity.Settlement;

public interface SettlementService {
	public void save(Settlement settle);
	public void save(Settlement from, Settlement to);
	public List<Settlement> getSettlements(Date settleDate);
	public List<Settlement> getSettlements(String rrn);
	public String getLastSettleDate();
}
