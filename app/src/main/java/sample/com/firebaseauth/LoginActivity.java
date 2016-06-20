package sample.com.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.com.firebaseauth.utils.Constants;
import sample.com.firebaseauth.utils.Utils;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;

    // [ LOGIN USER ]
    @OnClick(R.id.login)
    void login() {

        if (Utils.isConnectionAvailable(this)) {

            showProgressDialog(this, "", "Logging in..");
            //get inputs
            String emailAdd = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            Constants.mAuth.signInWithEmailAndPassword(emailAdd, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //proceed to main activity
                                dismissDialog();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            } else {
                                //something went wrong
                                dismissDialog();

                            }
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "No internet connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.register)
    void register() {
        //open register activity
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

    }

}
