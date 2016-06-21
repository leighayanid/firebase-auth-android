package sample.com.firebaseauth;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import sample.com.firebaseauth.utils.Constants;

/**
 * Created by user on 6/17/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    //show progress dialog
    public void showProgressDialog(Context context, String title, String message) {
        if (dialog == null) {
            dialog = ProgressDialog.show(context, title, message);
            dialog.setCancelable(true);
        }
    }

    //dismiss dialog
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    //validates edittext values
    public static boolean validateForms(EditText editText1, EditText editText2) {
        if (TextUtils.isEmpty(editText1.getText().toString())) {
            editText1.setError("Check input");
            return false;
        }
        if (TextUtils.isEmpty(editText2.getText().toString())|| editText2.getText().toString().length() < 6) {
            editText2.setError("Check input");
            return false;
        }
        else
            return true;
    }

    public String getUid() {
        return Constants.mAuth.getCurrentUser().getUid();
    }
}
