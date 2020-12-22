package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    final static String TAG = "InsertActivity";
    String urlAddress = null;
    String sCode, sName, sDept, sPhone;
    EditText edit_Code, edit_Name, edit_Dept, edit_Phone;
    Button btnInsert;
    String macIP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Intent intent = getIntent();
        macIP = intent.getStringExtra("macIP");
        urlAddress = "http://" + macIP + ":8080/test/studentInsert.jsp?";

        edit_Code = findViewById(R.id.insert_Edit_Code);
        edit_Name = findViewById(R.id.insert_Edit_Name);
        edit_Dept = findViewById(R.id.insert_Edit_Dept);
        edit_Phone = findViewById(R.id.insert_Edit_Phone);

        // 입력시 자릿수 제한 (or xml에서 제한하기)
        edit_Code.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
        edit_Name.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
        edit_Dept.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
        edit_Phone.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});

        btnInsert = findViewById(R.id.insert_Btn_OK);
        btnInsert.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sCode = edit_Code.getText().toString();
            sName = edit_Name.getText().toString();
            sDept = edit_Dept.getText().toString();
            sPhone = edit_Phone.getText().toString();

            urlAddress = urlAddress + "code=" + sCode +"&name=" + sName + "&dept=" + sDept + "&phone=" + sPhone; // get방식으로 넘어간다!
            connectInsertData();
            Toast.makeText(InsertActivity.this,sCode + "가 입력되었습니다." , Toast.LENGTH_SHORT).show();
        }
    };

    private void connectInsertData(){
        try {
            CUDNetworkTask insertNetworkTask = new CUDNetworkTask(InsertActivity.this, urlAddress);
            insertNetworkTask.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}