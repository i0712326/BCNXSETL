package bol.bconnex.settlement.business.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bol.bconnex.settlement.data.dao.SettleTxnDao;
import bol.bconnex.settlement.data.entity.SettleTxn;
@Service("settleTxnService")
public class SettleTxnServiceImp implements
		SettleTxnService {
	@Autowired
	private SettleTxnDao settleTxnDao;
	public void setSettleTxnDao(SettleTxnDao settleTxnDao){
		this.settleTxnDao = settleTxnDao;
	}
	@Override
	public void save(SettleTxn settle) {
		settleTxnDao.save(settle);
	}
	@Override
	public SettleTxn getTransaction(String rrn) {
		return settleTxnDao.getSettleTxn(rrn);
	}
	@Override
	public void update(SettleTxn settle) {
		settleTxnDao.update(settle);
	}
	@Override
	public List<String> getFileList() {
		return settleTxnDao.getFileNames();
	}
	@Override
	public List<SettleTxn> getSettleTxns(Date date) {
		return settleTxnDao.getSettleTxn(date);
	}

}
