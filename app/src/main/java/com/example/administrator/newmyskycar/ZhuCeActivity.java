package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.MyData.MySet;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuCeActivity extends AutoLayoutActivity {

    @BindView(R.id.mZhuCeReturn)
    ImageView mZhuCeReturn;
    @BindView(R.id.mZhuCeAfter)
    Button mZhuCeAfter;
    @BindView(R.id.mUserName)
    EditText mUserName;
    @BindView(R.id.mPassWord)
    EditText mPassWord;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.mNoPhoneText)
    EditText mNoPhoneText;

    private String Yonghuming="";
    private String Mima="";
    private String Qurenmima="";
    private String Shoujihao="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_zhuce);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mZhuCeReturn, R.id.mZhuCeAfter})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mZhuCeReturn:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mZhuCeAfter:
                Yonghuming=mUserName.getText().toString();
                Mima=mPassWord.getText().toString();
                Qurenmima=editText.getText().toString();
                Shoujihao=mNoPhoneText.getText().toString();
                boolean kong = false;
                if (Yonghuming.equals("")) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (Mima.equals("") || Qurenmima.equals("")) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (Shoujihao.equals("")) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    kong = true;
                }
                if (kong && Mima.equals(Qurenmima)) {
                    kong = true;
                } else if (kong && !Mima.equals(Qurenmima)) {
                    kong = false;
                    Toast.makeText(this, "两次密码输入不相同", Toast.LENGTH_SHORT).show();
                }
                if (kong) {
                    intent.setClass(this, ZhuCe2Activity.class);
                    //Zhuce02.getInstance().setxinxi(Yonghuming,Mima,Shoujihao);
                    MySet.AppUser_name = Yonghuming;
                    MySet.AppUser_password = Mima;
                    MySet.AppUser_phone = Shoujihao;
                    //转向添加页面
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
