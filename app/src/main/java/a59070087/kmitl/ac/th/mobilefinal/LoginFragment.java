package a59070087.kmitl.ac.th.mobilefinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import a59070087.kmitl.ac.th.mobilefinal.db.UserDB;

public class LoginFragment extends Fragment {
    public static final String TAG = "LOGIN";

    private EditText _userId, _password;
    private Button _loginBtn;
    private TextView _registerLink;
    private String pleaseFillIn, noCredentialFound;

    private UserDB userDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        userDB = UserDB.getInstance(getContext());
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        registerComponents();

        pleaseFillIn = getString(R.string.please_fill_in);
        noCredentialFound = getString(R.string.invalid_user_or_pwd);

        initLoginBtn();
        initRegisterLink();
    }

    private void registerComponents() {
        Log.d(TAG, "registerComponents");
        _userId = getView().findViewById(R.id.login_inp_user);
        _password = getView().findViewById(R.id.login_inp_pwd);
        _loginBtn = getView().findViewById(R.id.login_btn_login);
        _registerLink = getView().findViewById(R.id.login_btn_reg);
    }

    private void initLoginBtn() {
        Log.d(TAG, "initLoginBtn");

        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "initLoginBtn: onClick");

                final String USER_ID = _userId.getText().toString();
                final String PASSWORD = _password.getText().toString();

                if (USER_ID.isEmpty() || PASSWORD.isEmpty()) {
                    Toast.makeText(getContext(), pleaseFillIn, Toast.LENGTH_SHORT).show();
                } else {
                    final Cursor USER = userDB.getRecord(USER_ID, PASSWORD);
                    if (USER != null && USER.getCount() > 0) {
                        Toast.makeText(getContext(), "User found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), noCredentialFound, Toast.LENGTH_SHORT).show();
                    }

                    if (USER != null) { USER.close(); }
                }
            }
        });
    }

    private void initRegisterLink() {
        Log.d(TAG, "initRegisterLink");

        _registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "initRegisterLink: onClick");
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view, new RegisterFragment())
                        .commit();
            }
        });
    }
}
