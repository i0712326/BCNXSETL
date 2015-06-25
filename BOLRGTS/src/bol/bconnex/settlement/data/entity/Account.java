package bol.bconnex.settlement.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="ACCOUNTINFO")
public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ACCOUNTID")
	private int id;
	@Column(name="BIC")
	private String bic;
	@Column(name="SWIFTREG")
	private String swiftReg;
	@Column(name="NAME")
	private String name;
	@Column(name="SHORTNAME")
	private String shortName;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="ACCOUNT")
	private String account;
	@Column(name="ACCNAME")
	private String accName;
	@Column(name="TYPECODE")
	private String typeCode;
	@Column(name="CCY")
	private String ccy;
	@Transient
	private double amount;
	@Transient
	private String amt;
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getSwiftReg() {
		return swiftReg;
	}
	public void setSwiftReg(String swiftReg) {
		this.swiftReg = swiftReg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
}
