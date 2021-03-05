package com.ras.model.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    
    @NotBlank
    @Size(min = 3, max = 20)
	private String firstName;
	
    @NotBlank
    @Size(min = 3, max = 20)
	private String lastName;
	
//    
//    @NotBlank
//    @Size(min = 3)
//	private String loginType;

    @NotBlank
    @Size(min = 3)
	private String institute;
	
    @NotBlank
    @Size(min = 3)
	private String department;
	
    @NotBlank
    private String roles;
    
//    private Set<String> roles;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    
    
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

//	public String getLoginType() {
//		return loginType;
//	}
//
//	public void setLoginType(String loginType) {
//		this.loginType = loginType;
//	}

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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
//	public void setRoles(Set<String> roles) {
//		this.roles = roles;
//	}
//
//	public Set<String> getRoles() {
//      return this.roles;
//    }
//    
//    public void setRole(Set<String> roles) {
//      this.roles = roles;
//    }
}