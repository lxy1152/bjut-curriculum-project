package com.finalwork.android.e_commerce.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.controller.HttpController;
import com.finalwork.android.e_commerce.controller.IpController;
import com.finalwork.android.e_commerce.controller.ResultController;
import com.finalwork.android.e_commerce.model.entity.BasicDic;
import com.finalwork.android.e_commerce.model.entity.User;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Date;

import static com.finalwork.android.e_commerce.controller.ResultController.DEFAULT;

public class RegisterActivity extends AppCompatActivity {
    private final UserController userController = UserController.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ImageView buttonReturn = findViewById(R.id.btn_register_return);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button verify = findViewById(R.id.btn_register_code);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText numberShow = findViewById(R.id.edittext_register_code);
                numberShow.setText(String.valueOf((int) ((Math.random() * 9 + 1) * 1000)));
            }
        });
        Button ok = findViewById(R.id.btn_commit);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = ((EditText) findViewById(R.id.edittext_register_username)).getText().toString();
                String password = ((EditText) findViewById(R.id.edittext_register_password)).getText().toString();
                String nickName = ((EditText) findViewById(R.id.edittext_register_nickname)).getText().toString();
                String phone = ((EditText) findViewById(R.id.edittext_register_phone)).getText().toString();
                String email = ((EditText) findViewById(R.id.edittext_register_email)).getText().toString();
                String pin = ((EditText) findViewById(R.id.edittext_register_code)).getText().toString();

                if (userName.equals("")) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (nickName.equals("")) {
                    Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(RegisterActivity.this, "电子邮箱不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.equals("")) {
                    Toast.makeText(RegisterActivity.this, "手机不能为空", Toast.LENGTH_SHORT).show();
                } else {
// 主要的字段
                    User user = new User();
                    user.setLoginName(userName);
                    user.setLoginPassword(password);
                    user.setUserName(nickName);
                    user.setUserPhone(phone);
                    user.setUserEmail(email);
// 其他字段
                    BasicDic sex = new BasicDic();
                    sex.setId((long) 6);
                    sex.setName("男");
                    sex.setSort(0);
                    sex.setType("用户性别");
                    sex.setValue(1);
                    BasicDic status = new BasicDic();
                    status.setId((long) 7);
                    status.setName("未激活");
                    status.setSort(1);
                    status.setType("用户状态");
                    status.setValue(0);
                    BasicDic type = new BasicDic();
                    type.setId((long) 1);
                    type.setName("普通注册用户");
                    type.setSort(0);
                    type.setType("用户类型");
                    type.setValue(0);

                    user.setLoginPin(pin);
                    user.setUserType(type);
                    user.setUserSex(sex);
                    user.setUserStatus(status);
                    user.setTrueName("truename");
                    user.setBirthday(new Date());
                    user.setRegisterTime(new Date());
                    user.setUserPhotoLocation("where");
                    user.setUserScore(10);
                    user.setUserTotalScore((long) 100);
                    user.setUserMoney((long) 100);
                    user.setPayPassword("payPassword");

                    String temp = JSON.toJSONString(user);
                    ArrayList<NameValuePair> pairs = new ArrayList<>();
                    BasicNameValuePair pair = new BasicNameValuePair("target",temp);
                    pairs.add(pair);
                    System.out.println(pairs);
                    HttpController.getInstance().requestThread(new IpController().getRegisterAddress(), pairs);

                    int flag = 0;
                    while (true) {
                        String rec = ResultController.getInstance().getResult();
                        if (!rec.equals(DEFAULT)) {
                            if (rec.equals("{\"status\":401}")) {
                                flag = 401;
                            } else if (rec.equals("{\"status\":402}")) {
                                flag = 402;
                            } else {
                                flag = 200;
                            }
                            ResultController.getInstance().setResult(DEFAULT);
                            break;
                        }
                    }

                    if (flag == 200) {
                        final AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
                        alert.setTitle("提示");
                        alert.setMessage("您已成功注册！3秒后返回登录页面");
                        alert.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alert.dismiss();
                                finish();
                            }
                        }, 3000);
                    } else if (flag == 401) {
                        final AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
                        alert.setTitle("提示");
                        alert.setMessage("用户名重复！");
                        alert.show();
                        alert.setCancelable(true);
                    } else if (flag == 402) {
                        final AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
                        alert.setTitle("提示");
                        alert.setMessage("昵称重复！");
                        alert.show();
                        alert.setCancelable(true);
                    } else {
                        final AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
                        alert.setTitle("提示");
                        alert.setMessage("无法连接到服务器！请检查网络配置或与管理员联系");
                        alert.show();
                        alert.setCancelable(true);
                    }
                }
            }
        });
    }
}
