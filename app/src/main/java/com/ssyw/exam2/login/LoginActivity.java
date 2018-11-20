package com.ssyw.exam2.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssyw.exam2.MainTabActivity;
import com.ssyw.exam2.R;
import com.ssyw.exam2.constants.AppConstant;
import com.ssyw.exam2.data.UserInfo;
import com.ssyw.exam2.util.DBUserUtils;
import com.ssyw.exam2.util.SharePreferenceUtil;
import com.ssyw.exam2.util.ToastHelper;

import java.util.List;


//登陆
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etusername;
    private EditText etpassword;
    private Button login;
    private Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //缓存用户对象为空时， 可打开用户注册界面…
        initialize();
    }

    private void initialize() {
        etusername = (EditText) findViewById(R.id.et_username);
        etpassword = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                final String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    List<UserInfo> list = DBUserUtils.getInstance().queryUserDependUserName(username);
                    if (list != null && list.size() > 0) {
                        UserInfo userInfo = list.get(0);
                        if (userInfo.getPwd().equals(password)) {
                            ToastHelper.showShortMessage("登录成功");
                            Intent intent = new Intent(LoginActivity.this, MainTabActivity.class);
                            startActivity(intent);
                            SharePreferenceUtil.getInstance().putBoolean(AppConstant.SharedPreferenceKey.WHERE_LOGIN, true);
                            LoginActivity.this.finish();
                        } else {
                            ToastHelper.showShortMessage("密码错误");
                        }
                    } else {
                        ToastHelper.showShortMessage("无此用户");
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
