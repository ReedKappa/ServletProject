package model;

import javax.persistence.*;

@Entity
@Table(name = "testtable")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "login")
    private  String login;
    @Column(name = "password")
    private  String password;
    @Column(name = "email")
    private  String email;
    @Column(name = "rootDirectory")
    private  String rootDirectory;


    public UserProfile(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        rootDirectory = "C:/" + login;
    }
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

    public UserProfile() {

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

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }
}
