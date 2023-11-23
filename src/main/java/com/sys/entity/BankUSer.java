package com.sys.entity;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="bank_users")
public class BankUSer {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="PASSWORD")
	private String pasword;
	@Column(name="EMAIL")
	private String email;
	@Column(name="MOBILE")
	private String mobile;
	@Column(name="DOB")
	private Date dob;
    @Column(name = "enabled")
    private boolean enabled;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
    public BankUSer() {
    }

    public BankUSer(String userName, String pasword, boolean enabled) {
        this.userName = userName;
        this.pasword = pasword;
        this.enabled = enabled;
    }

    public BankUSer(String userName, String pasword, boolean enabled,
                Collection<Role> roles) {
        this.userName = userName;
        this.pasword = pasword;
        this.enabled = enabled;
        this.roles = roles;
    }

	@Override
	public String toString() {
		return "BankUSer [id=" + id + ", userName=" + userName + ", pasword=" + pasword + ", email=" + email
				+ ", mobile=" + mobile + ", dob=" + dob + ", roles=" + roles + "]";
	}



	
	
	

}
