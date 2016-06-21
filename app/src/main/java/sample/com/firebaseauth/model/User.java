package sample.com.firebaseauth.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by user on 6/17/2016.
 */
@IgnoreExtraProperties
public class User {

    String username;
    String email;
    HashMap<String, Object> createdAt;

    public User() {
    }

    public User(String email, String username,HashMap<String, Object> createdAt) {
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreatedAt(HashMap<String, Object> createdAt) {
        this.createdAt = createdAt;
    }

}
