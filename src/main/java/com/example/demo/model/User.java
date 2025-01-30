package com.example.demo.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id
	@Column(name="userid")
		private String id;
	
	@Column(name="name")
		private String name;
	
	@Column(name="password")
		private String password;
	
	@Column(name="email")
		private String email;
	
	@Column(name="phone")
	 	private String phone;
	
	@Column(name="role")
		private String role;
		

	public User() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoles() {
		return role;
	}

	public void setRoles(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password, phone, role);
	}

	@Override
	public boolean equals(Object obj) {
		if( obj instanceof User) {
			User user = (User) obj;
			return this.password.equals(user.password)
					&& this.email.equals(user.email)
					&& this.role.equals(user.role);
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ ", roles=" + role + "]";
	}
	
	
	
}
