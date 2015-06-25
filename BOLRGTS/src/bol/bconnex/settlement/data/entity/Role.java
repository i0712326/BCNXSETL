package bol.bconnex.settlement.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="USER_ROLES")
public class Role implements Serializable {
	
	private static final long serialVersionUID = -8950084434865556847L;
	@Id
	@Column(name="USER_ROLES_ID")
	private String roleId;
	@Column(name="AUTHORITY")
	private String authority;
	@ManyToOne
	private User user;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
