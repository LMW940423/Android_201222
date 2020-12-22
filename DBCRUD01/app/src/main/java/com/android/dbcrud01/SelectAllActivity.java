package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectAllActivity extends AppCompatActivity {

    final static String TAG = "SelectAllActivity";
    String urlAddress = null;
    ArrayList<Student> students = null;
    StudentAdapter studentAdapter;
    ListView listView;
    String macIP;
    Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectall);

        listView = findViewById(R.id.selectA_ListV_student);

        Intent intent = getIntent();
        macIP = intent.getStringExtra("macIP");
        urlAddress = "http://" + macIP + ":8080/test/student_query_all.jsp";

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()");
        connectGetData();
    }

    private void connectGetData(){
        try {
            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlAddress);
            Object object = networkTask.execute().get();
            students = (ArrayList<Student>) object; // 암호화가 된다.

            studentAdapter = new StudentAdapter(SelectAllActivity.this, R.layout.student_layout, students);
            listView.setAdapter(studentAdapter);

            // 클릭 짧게
            listView.setOnItemClickListener(shortClickListener);

            // 클릭 길게
            listView.setOnItemLongClickListener(longClickListener);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    AdapterView.OnItemClickListener shortClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SelectAllActivity.this, UpdateActivity.class);

            intent.putExtra("sCode", students.get(position).getCode());
            intent.putExtra("sName", students.get(position).getName());
            intent.putExtra("sDept", students.get(position).getDept());
            intent.putExtra("sPhone", students.get(position).getPhone());
            intent.putExtra("macIP", macIP);
            Log.v(TAG, "sCode : " + students.get(position).getCode());
            Log.v(TAG, "sName : " + students.get(position).getName());
            Log.v(TAG, "sDept : " + students.get(position).getDept());
            Log.v(TAG, "sPhone : " + students.get(position).getPhone());

            startActivity(intent);
        }
    };
    AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SelectAllActivity.this, DeleteActivity.class);

            intent.putExtra("sCode", students.get(position).getCode());
            intent.putExtra("sName", students.get(position).getName());
            intent.putExtra("sDept", students.get(position).getDept());
            intent.putExtra("sPhone", students.get(position).getPhone());
            intent.putExtra("macIP", macIP);
            Log.v(TAG, "sCode : " + students.get(position).getCode());
            Log.v(TAG, "sName : " + students.get(position).getName());
            Log.v(TAG, "sDept : " + students.get(position).getDept());
            Log.v(TAG, "sPhone : " + students.get(position).getPhone());

            startActivity(intent);
            return false;
        }
    };

}