package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtIP;
    Button insertBtn = null;
    Button updateBtn = null;
    Button deleteBtn = null;
    Button selectAllBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 누가 20.12.21 몇시 몇분 뭘 수정해는지 ~~~
        addListener();



        //



        //



        //
    }

    private void addListener(){
        edtIP = findViewById(R.id.main_Edit_ip);
        insertBtn = findViewById(R.id.main_Btn_Insert);
        updateBtn = findViewById(R.id.main_Btn_Update);
        deleteBtn = findViewById(R.id.main_Btn_Delete);
        selectAllBtn = findViewById(R.id.main_Btn_SelectA);

        insertBtn.setOnClickListener(onClickListener);
        updateBtn.setOnClickListener(onClickListener);
        deleteBtn.setOnClickListener(onClickListener);
        selectAllBtn.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tempIP = edtIP.getText().toString(); // getText만 하면 숫자인지 문자인지 애매해진다.
            Intent intent = null;

            switch (v.getId()){
                case R.id.main_Btn_Insert:
                    intent = new Intent(MainActivity.this, InsertActivity.class);
                    intent.putExtra("macIP", tempIP);
                    startActivity(intent);
                    break;

                case R.id.main_Btn_Update:
                    Toast.makeText(MainActivity.this, "검색 후 짧게 클릭하시면 수정화면으로 이동합니다.", Toast.LENGTH_LONG).show();
                    intent = new Intent(MainActivity.this, SelectAllActivity.class);
                    intent.putExtra("macIP", tempIP);
                    startActivity(intent);
                    break;

                case R.id.main_Btn_Delete:
                    Toast.makeText(MainActivity.this, "검색 후 길게 클릭하시면 삭제화면으로 이동합니다.", Toast.LENGTH_LONG).show();
                    intent = new Intent(MainActivity.this, SelectAllActivity.class);
                    intent.putExtra("macIP", tempIP);
                    startActivity(intent);
                    break;

                case R.id.main_Btn_SelectA:
                    intent = new Intent(MainActivity.this, SelectAllActivity.class);
                    intent.putExtra("macIP", tempIP);
                    startActivity(intent);
                    break;

            }
        }
    };
}