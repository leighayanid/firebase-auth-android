package sample.com.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.com.firebaseauth.model.User;
import sample.com.firebaseauth.utils.Constants;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;

    String username ,email,password;

    @OnClick(R.id.register)
    void register(){
        showProgressDialog(this,"","Signing up..");
        //get input values
        username = mUsername.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();

        // [Register user]
        Constants.mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            dismissDialog();
                            //success! proceed to mainactivity
                            authenticateUser(email, password, task.getResult().getUser());
                        }else{
                            //something went wrong
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void authenticateUser(String email, String password, FirebaseUser user) {
        //let the user access the main screen of the application

        User newUser= new User();
        newUser.setEmail(email);
        newUser.setUsername(username);

        Constants.mDatabase.child("users").setValue(newUser, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

}
