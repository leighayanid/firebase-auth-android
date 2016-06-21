package sample.com.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.com.firebaseauth.model.User;
import sample.com.firebaseauth.utils.Constants;
import sample.com.firebaseauth.utils.Utils;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;

    String username, email, password;
    private static final String TAG = "RegisterActivity";

    @OnClick(R.id.register)
    void register() {

        if (Utils.isConnectionAvailable(RegisterActivity.this)) {
            showProgressDialog(this, "", "Signing up..");
            //get input values
            username = mUsername.getText().toString().trim();
            email = mEmail.getText().toString().trim();
            password = mPassword.getText().toString().trim();

            // [Register user]
            Constants.mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                dismissDialog();
                                //success! proceed to mainactivity
                                authenticateUser(email,task.getResult().getUser());
                            } else {
                                //something went wrong
                            }
                        }
                    });
        } else {
            Toast.makeText(RegisterActivity.this, "No internet connection available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void authenticateUser(String email, FirebaseUser user) {

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        //create hashmap for timestamp then add on newUser object
        HashMap<String,Object> createdAt = new HashMap<>();
        createdAt.put(Constants.CREATED_AT, ServerValue.TIMESTAMP);

        newUser.setCreatedAt(createdAt);

        Constants.mDatabase.child("users" + "/" + getUid()).setValue(newUser, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError==null){
                    //let the user access the main screen of the application
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    //THERE WAS AN ERROR SIGNING UP USER
                    Log.e(TAG, "Error authenticating user.");
                }
            }
        });

    }

    private void toast(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
