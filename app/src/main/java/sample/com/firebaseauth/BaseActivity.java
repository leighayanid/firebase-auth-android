package sample.com.firebaseauth;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import sample.com.firebaseauth.utils.Constants;

/**
 * Created by user on 6/17/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    public void showProgressDialog(Context context, String title, String message) {
        if (dialog == null) {
            dialog = ProgressDialog.show(context, title, message);
        }
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public String getUid(){
        return Constants.mAuth.getCurrentUser().getUid();
    }
}
