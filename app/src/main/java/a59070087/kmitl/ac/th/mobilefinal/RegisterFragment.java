package a59070087.kmitl.ac.th.mobilefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import a59070087.kmitl.ac.th.mobilefinal.db.UserDB;

public class RegisterFragment extends Fragment {
    public static final String TAG = "REGISTER";

    private EditText _userId, _name, _age, _password;
    private Button _registerBtn;
    private String errUserId, errName, errAge, errPwd;

    private UserDB userDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        userDB = UserDB.getInstance(getContext());
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        registerComponents();

        errUserId = getString(R.string.register_userId_error);
        errName = getString(R.string.register_name_error);
        errAge = getString(R.string.register_age_error);
        errPwd = getString(R.string.register_pwd_error);

        initRegisterBtn();
    }

    private void registerComponents() {
        Log.d(TAG, "registerComponents");
        _userId = getView().findViewById(R.id.register_inp_user_id);
        _name = getView().findViewById(R.id.register_inp_name);
        _age = getView().findViewById(R.id.register_inp_age);
        _password = getView().findViewById(R.id.register_inp_pwd);
        _registerBtn = getView().findViewById(R.id.register_btn_register);
    }

    private void initRegisterBtn() {
        Log.d(TAG, "initRegisterBtn");
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "initRegisterBtn: onClick");

                final String USER_ID = _userId.getText().toString();
                final String NAME = _name.getText().toString();
                String AGE = _age.getText().toString();
                final String PASSWORD = _password.getText().toString();

                int errCount = 0;

                if (AGE.isEmpty()) AGE = "0";
                
                if (!isUserValid(USER_ID)) {
                    Toast.makeText(getContext(), errUserId, Toast.LENGTH_SHORT).show();
                    errCount += 1;
                }

                if (!isNameValid(NAME)) {
                    Toast.makeText(getContext(), errName, Toast.LENGTH_SHORT).show();
                    errCount += 1;
                }

                if (!isAgeValid(Integer.parseInt(AGE))) {
                    Toast.makeText(getContext(), errAge, Toast.LENGTH_SHORT).show();
                    errCount += 1;
                }

                if (!isPasswordValid(PASSWORD)) {
                    Toast.makeText(getContext(), errPwd, Toast.LENGTH_SHORT).show();
                    errCount += 1;
                }

                if (errCount == 0) {
                    Log.d(TAG, "initRegisterBtn: To write to DB");
                    userDB.createRecord(USER_ID, NAME, Integer.parseInt(AGE), PASSWORD);
                    Log.d(TAG, "initRegisterBtn: Wrote to DB");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .commit();
                }
            }
        });
    }

    private boolean isUserValid(String userId) {
        return userId.length() >= 6 && userId.length() <= 12;
    }

    private boolean isNameValid(String name) {
        final int SPACES = name.length() - name.replaceAll(" ", "").length();
        return SPACES == 1;
    }

    private boolean isAgeValid(int age) {
        return age >= 10 && age <= 80;
    }

    private boolean isPasswordValid(String pwd) {
        return pwd.length() > 6;
    }
}
