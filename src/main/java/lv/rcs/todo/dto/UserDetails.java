package lv.rcs.todo.dto;
import java.util.Date;

public class UserDetails { // dto

    private String firstname;
    private String lastname;
    private String email;
    private Date lastLogin;

    public UserDetails(String email) {
        this(email, null, null);
    }

    public UserDetails(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "UserDetails [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", lastLogin="
                + lastLogin + "]";
    }
    
    

}
