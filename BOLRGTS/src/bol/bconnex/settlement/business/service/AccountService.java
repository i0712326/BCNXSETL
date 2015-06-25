package bol.bconnex.settlement.business.service;

import java.util.List;

import bol.bconnex.settlement.data.entity.Account;

public interface AccountService {
	public List<Account> getAccounts();
	public List<Account> getAccounts(String bic);
	public List<Account> getAccountsNotEquals(String bic);
	public Account getAccount(String bic);
}
