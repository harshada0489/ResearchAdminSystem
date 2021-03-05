package com.ras.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  
  @NotBlank
  @Size(min = 3, max = 20)
	private String firstName;
	
  @NotBlank
  @Size(min = 3, max = 20)
	private String lastName;
	
  
//  @NotBlank
//  @Size(min = 3)
//	private String loginType;

  @NotBlank
  @Size(min = 3)
	private String institute;
	
  @NotBlank
  @Size(min = 3)
	private String department;
	
  
  @DBRef
  private Set<Role> roles = new HashSet<>();


  
  public User(@NotBlank @Size(max = 20) String username,
		  @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password,
		@NotBlank @Size(min = 3, max = 20) String firstName,
		@NotBlank @Size(min = 3, max = 20) String lastName,
		@NotBlank @Size(min = 3) String institute,
		@NotBlank @Size(min = 3) String department) {
	this.username = username;
	this.email = email;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.institute = institute;
	this.department = department;
}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  
  
  public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

//public String getLoginType() {
//	return loginType;
//}
//
//public void setLoginType(String loginType) {
//	this.loginType = loginType;
//}

public String getInstitute() {
	return institute;
}

public void setInstitute(String institute) {
	this.institute = institute;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}