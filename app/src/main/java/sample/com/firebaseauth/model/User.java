package sample.com.firebaseauth.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by user on 6/17/2016.
 */
@IgnoreExtraProperties
public class User {

    String username;
    String email;

    public User() {
    }

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }
    
}
