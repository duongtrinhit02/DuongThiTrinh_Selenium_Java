package Account;

import Common.RandomString;
import Constant.Constant;

public class Account {

    // fields
    private String password;
    private String email;
    private String pid;

    // constructors
    public Account(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public Account(String email, String password, String pid) {
        this.password = password;
        this.email = email;
        this.pid = pid;
    }

    public Account() {
    }

    // factory method
    public static Account generalAccount() {
        String email = RandomString.generateRandomString(Constant.DEFAULT_EMAIL_LENGTH);
        String password = RandomString.generateRandomString(Constant.DEFAULT_PASSWORD_LENGTH);
        String pid = RandomString.generateRandomNumberString(Constant.DEFAULT_PID_LENGTH);
        return new Account(email, password, pid);
    }

    // getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
