package Login;

public class Admin {
    private String admin;
    private String password;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }
    
}
