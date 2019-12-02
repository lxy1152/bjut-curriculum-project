package com.finalwork.android.e_commerce.view.fragment.fragmentmain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.controller.HttpController;
import com.finalwork.android.e_commerce.controller.IpController;
import com.finalwork.android.e_commerce.controller.ResultController;
import com.finalwork.android.e_commerce.model.entity.User;
import com.finalwork.android.e_commerce.view.activity.FollowingActivity;
import com.finalwork.android.e_commerce.view.activity.PendingActivity;
import com.finalwork.android.e_commerce.view.activity.ProfileSettingActivity;
import com.finalwork.android.e_commerce.view.activity.LoginActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import static com.finalwork.android.e_commerce.controller.ResultController.DEFAULT;

public class FragmentProfile extends Fragment {
    private boolean isLogin = false;
    private boolean getFromSever = false;

    public void setLogin(boolean login) {
        this.isLogin = login;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        com.assistne.icondottextview.IconDotTextView setting = view.findViewById(R.id.btn_profile_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileSettingActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout itemlayout = (RelativeLayout)view.findViewById(R.id.profile_category1_itemfollow);
        RelativeLayout shoplayout = (RelativeLayout)view.findViewById(R.id.profile_category1_shopfollow);
        RelativeLayout paylayout = (RelativeLayout)view.findViewById(R.id.profile_category_readytopay);
        RelativeLayout receivelayout = (RelativeLayout)view.findViewById(R.id.profile_category_receving) ;
        RelativeLayout commentlayout = (RelativeLayout)view.findViewById(R.id.profile_category_comment);
        RelativeLayout returnlayout = (RelativeLayout)view.findViewById(R.id.profile_category_returngoods);

        itemlayout.setClickable(true);
        itemlayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean bitem = false;
                if(bitem) initFollowingtFragment(1);
                else initFollowingtFragment(-1);
            }
        });
        shoplayout.setClickable(true);
        shoplayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean bshop = false;
                if(bshop)initFollowingtFragment(2);
                else initFollowingtFragment(-2);
            }
        });
        paylayout.setClickable(true);
        paylayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean bpay = false;
                if(bpay)initPendingtFragment(3);
                else initPendingtFragment(-3);
            }
        });
        receivelayout.setClickable(true);
        receivelayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean breceive = false;
                if(breceive)initPendingtFragment(4);
                else initPendingtFragment(-4);
            }
        });
        commentlayout.setClickable(true);
        commentlayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean bcomment = false;
                if(bcomment)initPendingtFragment(5);
                else initPendingtFragment(-5);
            }
        });
        returnlayout.setClickable(true);
        returnlayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean breturn = false;
                if(breturn)initPendingtFragment(6);
                else initPendingtFragment(-6);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<String> info = UserController.getInstance().getInfo(getActivity().getApplicationContext());
        if (info.get(2).equals("login")) {
            NameValuePair nameValuePair = new BasicNameValuePair("loginName", info.get(0));
            NameValuePair pwdValuePair = new BasicNameValuePair("loginPassword", info.get(1));
            ArrayList<NameValuePair> pairs = new ArrayList<>();
            pairs.add(nameValuePair);
            pairs.add(pwdValuePair);
            HttpController.getInstance().requestThread(new IpController().getProfileAddress(), pairs);
            while (true) {
                String rec = ResultController.getInstance().getResult();
                if (!rec.equals(DEFAULT)) {
                    ResultController.getInstance().setUser(rec);
                    ResultController.getInstance().setResult(DEFAULT);
                    break;
                }
            }
            TextView tvUserName = getView().findViewById(R.id.text_profile_self_username);
            TextView tvUserScore = getView().findViewById(R.id.text_profile_self_score);
            tvUserName.setText(User.getInstance().getLoginName());
            tvUserScore.setText("会员积分" + String.valueOf(User.getInstance().getUserTotalScore()));
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ArrayList<String> info = UserController.getInstance().getInfo(getActivity().getApplicationContext());
            if (!info.get(2).equals("login")) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    private void initFollowingtFragment(int num){
        Intent intent = new Intent(getActivity(), FollowingActivity.class);
        intent.putExtra("id",num);
        startActivity(intent);
    }
    private void initPendingtFragment(int num){
        Intent intent = new Intent(getActivity(), PendingActivity.class);
        intent.putExtra("id",num);
        startActivity(intent);
    }
}
