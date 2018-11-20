package com.ssyw.exam2.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssyw.exam2.R;
import com.ssyw.exam2.data.UserInfo;
import com.ssyw.exam2.util.DBUserUtils;
import com.ssyw.exam2.util.ToastHelper;

import java.util.List;

//用户注册页面
public class RegisterActivity extends BaseActivity {

    private EditText etusername;
    private EditText etpassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }

    private void initialize() {
        etusername = (EditText) findViewById(R.id.et_username2);
        etpassword = (EditText) findViewById(R.id.et_password2);
        register = (Button) findViewById(R.id.sign2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerData();
            }
        });
    }

    /**
     * 注册
     */
    private void registerData() {
        final String name = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            List<UserInfo> list = DBUserUtils.getInstance().queryUserDependUserName(name);
            if (list != null && list.size() > 0) {
                ToastHelper.showShortMessage("此用户名已注册");
            } else {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(System.currentTimeMillis());
                userInfo.setType(2);
                userInfo.setUserName(name);
                userInfo.setPwd(password);
                DBUserUtils.getInstance().insertOneData(userInfo);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                RegisterActivity.this.finish();
            }
        }
    }
}