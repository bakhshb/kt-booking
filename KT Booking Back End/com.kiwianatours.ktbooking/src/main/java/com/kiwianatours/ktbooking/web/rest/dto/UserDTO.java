package com.kiwianatours.ktbooking.web.rest.dto;

import java.util.List;

public class UserDTO {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
    
    private String contactNo;

    private String langKey;
    
    private String role;

    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email,
    		String contactNo, String langKey, String role, List<String> roles) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.langKey = langKey;
        this.role= role;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public String getContactNo() {
		return contactNo;
	}

	public String getLangKey() {
        return langKey;
    }

    public String getRole() {
		return role;
	}

	public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("login='").append(login).append('\'');
        if(password != null) {
            sb.append(", password='").append(password.length()).append('\'');
        }
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", contactNo='").append(contactNo).append('\'');
        sb.append(", langKey='").append(langKey).append('\'');
        sb.append(", role=").append(role);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
