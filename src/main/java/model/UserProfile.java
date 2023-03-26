package model;

public class UserProfile {
    private final String login;
    private final String password;
    private  String email;
    private final String rootDirectory;

    public UserProfile(String login, String password) {
        this.login = login;
        this.password = password;
        rootDirectory = "C:/" + login;
    }
    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        rootDirectory = "C:/" + login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }
}
