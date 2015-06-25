package bol.bconnex.settlement.data.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TXN_DETAIL")
public class SettleTxn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874731313526757453L;
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="TXNDATETIME")
	private Timestamp dateTime;
	@Column(name="RRN")
	private String rrn;
	@Column(name="SRC_ACC")
	private String from;
	@Column(name="SRC_NAME")
	private String fromName;
	@Column(name="DES_ACC")
	private String to;
	@Column(name="DES_NAME")
	private String toName;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="RES")
	private String res;
	@Column(name="SWIFTNAME")
	private String swiftName;
	@Column(name="SYS_DATE")
	private Date sysDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getSwiftName() {
		return swiftName;
	}
	public void setSwiftName(String swiftName) {
		this.swiftName = swiftName;
	}
	public Date getSysDate() {
		return sysDate;
	}
	public void setSysDate(Date sysDate) {
		this.sysDate = sysDate;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
}
