package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.MyData.MySet;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.MyData.SqlMydata;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuCe2Activity extends AutoLayoutActivity {

    @BindView(R.id.mZhuCe2Return)
    ImageView mZhuCe2Return;
    @BindView(R.id.mZhuCe)
    Button mZhuCe;
    @BindView(R.id.mUserName)
    EditText mUserName;
    @BindView(R.id.mPassWord)
    EditText mPassWord;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.mNoPhoneText)
    EditText mNoPhoneText;
    @BindView(R.id.xieyi)
    CheckBox xieyi;
    private SqlMydata db;
    private SqlFrienddata db2;
    private String Zhenshixingming = "";
    private String Shenfenzhenghao = "";
    private String Suozaishengshi = "";
    private String Bangongdizhi = "";
    private Sql dao = Sql.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_zhuce2);
        db = new SqlMydata(this);
        db2 = new SqlFrienddata(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mZhuCe2Return, R.id.mZhuCe})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mZhuCe2Return:
                intent.setClass(this, ZhuCeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mZhuCe:
                Zhenshixingming = mUserName.getText().toString();
                Shenfenzhenghao = mPassWord.getText().toString();
                Suozaishengshi = editText.getText().toString();
                Bangongdizhi = mNoPhoneText.getText().toString();
                boolean kong = false;
                if (Zhenshixingming.equals(""))
                    Toast.makeText(this, "真实姓名不能为空", Toast.LENGTH_SHORT).show();
                else if (Shenfenzhenghao.equals(""))
                    Toast.makeText(this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
                else if (Suozaishengshi.equals(""))
                    Toast.makeText(this, "所在省市不能为空", Toast.LENGTH_SHORT).show();
                else if (Bangongdizhi.equals(""))
                    Toast.makeText(this, "办公地址不能为空", Toast.LENGTH_SHORT).show();
                else
                    kong = true;
                if (kong && !xieyi.isChecked()) {
                    Toast.makeText(this, "请先确认同意协议", Toast.LENGTH_SHORT).show();
                    kong = false;
                }
                if (kong) {
                    MySet.AppUser_id = Shenfenzhenghao;
                    MySet.AppUser_realname = Zhenshixingming;
                    MySet.AppUser_city = Suozaishengshi;
                    MySet.AppUser_address = Bangongdizhi;
                    db.setServerAddress(MySet.AppUser_name, MySet.AppUser_password, MySet.AppUser_id, MySet.AppUser_phone, MySet.AppUser_realname
                            , MySet.AppUser_city, MySet.AppUser_address);
                    db.close();
                    db2.setServerAddress("fumingzhen","18601918892","11");
                    db2.close();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
                    intent.setClass(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
