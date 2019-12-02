package com.finalwork.android.e_commerce.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.model.entity.User;

public class ProfileSettingActivity extends AppCompatActivity {
    private final UserController userController = new UserController();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilesetting);

        ImageView headImg = findViewById(R.id.profile_setting_headimg);
        TextView loginName = findViewById(R.id.profile_setting_loginname);
        TextView userName = findViewById(R.id.profile_setting_username);
        TextView userPhone = findViewById(R.id.profile_setting_phone);
        TextView userEmail = findViewById(R.id.profile_setting_email);

        loginName.setText(User.getInstance().getLoginName());
        userName.setText(User.getInstance().getUserName());
        userPhone.setText(User.getInstance().getUserPhone());
        userEmail.setText(User.getInstance().getUserEmail());

        Button quit = findViewById(R.id.btn_quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(ProfileSettingActivity.this);
                builder.setTitle("提示" ) ;
                builder.setMessage("确认登出？" ) ;
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userController.userQuit(getApplicationContext());
                        final AlertDialog alert = new AlertDialog.Builder(ProfileSettingActivity.this).create();
                        alert.setTitle("提示");
                        alert.setMessage("您已成功注销！现在将返回主页");
                        alert.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alert.dismiss();
                                finish();
                            }
                        }, 3000);
                    }
                });
                builder.setNegativeButton("否", null);
                builder.show();
            }
        });
    }
}
