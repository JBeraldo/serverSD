package com.sd.server.models;


import com.sd.server.package_data.request.user.CreateUserRequestData;
import com.sd.server.packages.user.CreateUserRequestPackage;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Transient
    private String token;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String type;
    public User() {

    }

    public User(Long id, String name, String password, String email, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }
    public User(String name, String password, String email, String type) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }
    public User(CreateUserRequestData data) {
        this.name = data.getName();
        this.password = data.getPassword();
        this.email = data.getEmail();
        this.type = data.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
