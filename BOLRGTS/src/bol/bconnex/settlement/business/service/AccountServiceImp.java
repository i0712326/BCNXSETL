package bol.bconnex.settlement.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bol.bconnex.settlement.data.dao.AccountDao;
import bol.bconnex.settlement.data.entity.Account;
@Service("accountService")
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountDao accountDao;
	public void setMemberDao(AccountDao accountDao){
		this.accountDao = accountDao;
	}
	@Override
	public List<Account> getAccounts() {
		return accountDao.getAccounts();
	}
	@Override
	public List<Account> getAccounts(String bic) {
		return accountDao.getAccountsNotEqual(bic);
	}
	@Override
	public List<Account> getAccountsNotEquals(String bic) {
		return accountDao.getAccountsNotEqual(bic);
	}
	@Override
	public Account getAccount(String bic) {
		return accountDao.getAccount(bic);
	}
}
