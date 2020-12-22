package com.android.dbcrud01;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    Context mContext = null;
    int layout = 0;
    ArrayList<Student> data = null;
    LayoutInflater inflater = null;

    public StudentAdapter(Context mContext, int layout, ArrayList<Student> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getCode(); // primary 키를 이용해야한다.
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
        }
        TextView tv_code = convertView.findViewById(R.id.student_TextV_code);
        TextView tv_name = convertView.findViewById(R.id.student_TextV_name);
        TextView tv_dept = convertView.findViewById(R.id.student_TextV_dept);
        TextView tv_phone = convertView.findViewById(R.id.student_TextV_phone);

        tv_code.setText("학번 : " + data.get(position).getCode());
        tv_name.setText("이름 : " + data.get(position).getName());
        tv_dept.setText("학과 : " + data.get(position).getDept());
        tv_phone.setText("연락처 : " + data.get(position).getPhone());

        if((position % 2) == 1){
            convertView.setBackgroundColor(0x500000CC);
        }else{
            convertView.setBackgroundColor(0x50CCFFFF);
        }

        return convertView;
    }
}
