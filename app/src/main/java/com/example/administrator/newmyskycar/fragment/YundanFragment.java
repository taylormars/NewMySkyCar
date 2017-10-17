package com.example.administrator.newmyskycar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.newmyskycar.R;

/**
 * Created by Administrator on 2017-10-10 0010.
 */

public class YundanFragment extends Fragment implements View.OnClickListener{

    private Button yizhongbiao;
    private Button yunshuzhong;
    private Button yiwancheng;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linear_yundan, container, false);
        yizhongbiao=(Button)view.findViewById(R.id.button4);
        yunshuzhong=(Button)view.findViewById(R.id.button3);
        yiwancheng=(Button)view.findViewById(R.id.button2);
        yizhongbiao.setOnClickListener(this);
        yunshuzhong.setOnClickListener(this);
        yiwancheng.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button4){
            yizhongbiao.setBackgroundResource(R.mipmap.button_break_hei);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break);
            yiwancheng.setBackgroundResource(R.mipmap.button_break);
        }
        else if(view.getId()==R.id.button3){
            yizhongbiao.setBackgroundResource(R.mipmap.button_break);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break_hei);
            yiwancheng.setBackgroundResource(R.mipmap.button_break);
        }
        else if(view.getId()==R.id.button2){
            yizhongbiao.setBackgroundResource(R.mipmap.button_break);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break);
            yiwancheng.setBackgroundResource(R.mipmap.button_break_hei);
        }
    }
}
