package bol.bconnex.settlement.business.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bol.bconnex.settlement.data.dao.SettlementDao;
import bol.bconnex.settlement.data.entity.Settlement;
@Service("settlementService")
public class SettlementServiceImp implements SettlementService {
	@Autowired
	private SettlementDao settlementDao;
	public void setSettlementDao(SettlementDao settlementDao){
		this.settlementDao = settlementDao;
	}
	@Override
	public void save(Settlement settlement) {
		settlementDao.save(settlement);
	}
	@Override
	public List<Settlement> getSettlements(Date settleDate) {
		return settlementDao.getSettlement(settleDate);
	}

	@Override
	public List<Settlement> getSettlements(String rrn) {
		return settlementDao.getSettlement(rrn);
	}
	@Override
	public void save(Settlement from, Settlement to) {
		settlementDao.save(from);
		settlementDao.save(to);
	}
	@Override
	public String getLastSettleDate() {
		return settlementDao.getLastSettleDate();
	}

}
