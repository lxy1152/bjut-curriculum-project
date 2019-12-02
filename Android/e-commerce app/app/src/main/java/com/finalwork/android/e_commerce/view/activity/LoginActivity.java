package com.finalwork.android.e_commerce.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.controller.HttpController;
import com.finalwork.android.e_commerce.controller.IpController;
import com.finalwork.android.e_commerce.controller.ResultController;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import static com.finalwork.android.e_commerce.controller.ResultController.DEFAULT;

public class LoginActivity extends AppCompatActivity {
    final UserController userController = UserController.getInstance();
    private boolean commit = false;

    private boolean isCommit() {
        return this.commit;
    }

    private void setCommit(boolean commit) {
        this.commit = commit;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText loginUserName = findViewById(R.id.edittext_login_username);
        final EditText loginPassword = findViewById(R.id.edittext_login_password);

        if (userController.hasInfo(getApplicationContext())) {
            Log.w("hasInfo:", "true");
            ArrayList<String> info = userController.getInfo(getApplicationContext());
            loginUserName.setText(info.get(0));
            loginPassword.setText(info.get(1));
        } else {
            Log.w("hasInfo:", "false");
        }
        TextView register = findViewById(R.id.text_login_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ImageView back = findViewById(R.id.btn_login_return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Button ok = findViewById(R.id.btn_login);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCommit()) {
                    setCommit(true);
                    String userName = loginUserName.getText().toString();
                    String password = loginPassword.getText().toString();

                    if (userName.equals("")) {
                        Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                        setCommit(false);
                    } else if (password.equals("")) {
                        Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        setCommit(false);
                    } else {
                        NameValuePair nameValuePair = new BasicNameValuePair("loginName", userName);
                        NameValuePair pwdValuePair = new BasicNameValuePair("loginPassword", password);
                        ArrayList<NameValuePair> pairs = new ArrayList<>();
                        pairs.add(nameValuePair);
                        pairs.add(pwdValuePair);

                        HttpController.getInstance().requestThread(new IpController().getProfileAddress(), pairs);
                        int flag = 0;
                        while (true) {
                            String rec = ResultController.getInstance().getResult();
                            if (!rec.equals(DEFAULT)) {
                                if (rec.equals("{\"status\":404}")) {
                                    flag = 404;
                                } else if (rec.equals("{\"status\":401}")) {
                                    flag = 401;
                                } else {
                                    ResultController.getInstance().setUser(rec);
                                    flag = 200;
                                }
                                ResultController.getInstance().setResult(DEFAULT);
                                break;
                            }
                        }
                        if (flag == 200) {
                            Log.w("canLogin:", "true");
                            userController.saveInfo(getApplicationContext(), userName, password, "login");
                            setCommit(false);
                            final AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
                            alert.setTitle("提示");
                            alert.setMessage("您已成功登录！现在将返回登录前页面");
                            alert.setCancelable(false);
                            alert.show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    alert.dismiss();
                                    finish();
                                }
                            }, 3000);
                        } else if (flag == 401) {
                            Log.w("canLogin:", "false");
                            setCommit(false);
                            final AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
                            alert.setTitle("提示");
                            alert.setMessage("用户名或密码错误！");
                            alert.show();
                            alert.setCancelable(true);
                        } else if (flag == 404) {
                            Log.w("canLogin:", "false");
                            setCommit(false);
                            final AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
                            alert.setTitle("提示");
                            alert.setMessage("用户名不存在！");
                            alert.show();
                            alert.setCancelable(true);
                        } else {
                            final AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
                            alert.setTitle("提示");
                            alert.setMessage("无法连接到服务器！");
                            alert.show();
                            alert.setCancelable(true);
                        }
                    }
                }
            }
        });
    }
}
