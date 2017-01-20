package sample.com.firebaseauth.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by user on 6/17/2016.
 */
public class Constants {

    public static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static final FirebaseUser user = mAuth.getCurrentUser();

    //CHILD REFERENCES FOR USERS
    public static final String USER = "users";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";

}
