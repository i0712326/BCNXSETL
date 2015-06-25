package bol.bconnex.settlement.data.dao;

import java.util.List;

import bol.bconnex.settlement.data.entity.Account;

public interface AccountDao {
	public List<Account> getAccounts();
	public List<Account> getAccountsNotEqual(String bic);
	public Account getAccount(String bic);
}
