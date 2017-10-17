package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AutoLayoutActivity {

    @BindView(R.id.mLogin)
    Button mLogin;
    @BindView(R.id.mHintZhuce)
    TextView mHintZhuce;
    @BindView(R.id.mUserName)
    EditText mUserName;
    @BindView(R.id.mPassWord)
    EditText mPassWord;
    private Sql dao = Sql.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.mLogin, R.id.mHintZhuce})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mLogin:
                Integer result = dao.checkLogin(mUserName.getText().toString(), mPassWord.getText().toString());
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    intent.setClass(this, MainShouyeActivity.class);
                    //转向添加页面
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mHintZhuce:
                intent.setClass(this, ZhuCeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
