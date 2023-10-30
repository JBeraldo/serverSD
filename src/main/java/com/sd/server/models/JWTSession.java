package com.sd.server.models;


import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class JWTSession {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private String ip;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public JWTSession(String token, User user,String ip) {
        this.token = token;
        this.user = user;
        this.ip = ip;
    }

    public JWTSession() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
