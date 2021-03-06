package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    final static String TAG = "DeleteActivity";
    String urlAddress = null;
    String sCode, sName, sDept, sPhone;
    EditText edit_Code, edit_Name, edit_Dept, edit_Phone;
    Button btnDelete;
    Button btnBack;
    String macIP = null;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        intent = getIntent();
        macIP = intent.getStringExtra("macIP");
        urlAddress = "http://" + macIP + ":8080/test/studentDelete.jsp?";

        String getCode = intent.getStringExtra("sCode");
        String getName = intent.getStringExtra("sName");
        String getDept = intent.getStringExtra("sDept");
        String getPhone = intent.getStringExtra("sPhone");

        edit_Code = findViewById(R.id.delete_Edit_Code);
        edit_Name = findViewById(R.id.delete_Edit_Name);
        edit_Dept = findViewById(R.id.delete_Edit_Dept);
        edit_Phone = findViewById(R.id.delete_Edit_Phone);

        edit_Code.setText(getCode);
        edit_Name.setText(getName);
        edit_Dept.setText(getDept);
        edit_Phone.setText(getPhone);

        // 입력시 자릿수 제한 (or xml에서 제한하기)
        edit_Code.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
        edit_Name.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
        edit_Dept.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
        edit_Phone.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});

        btnDelete = findViewById(R.id.delete_Btn_OK);
        btnDelete.setOnClickListener(onClickListener);

        btnBack = findViewById(R.id.delete_Btn_Back);
        btnBack.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.delete_Btn_OK:
                    sCode = edit_Code.getText().toString();
                    sName = edit_Name.getText().toString();
                    sDept = edit_Dept.getText().toString();
                    sPhone = edit_Phone.getText().toString();

                    urlAddress = urlAddress + "code=" + sCode +"&name=" + sName + "&dept=" + sDept + "&phone=" + sPhone; // get방식으로 넘어간다!
                    Log.v(TAG, "urlAddress : " + urlAddress);
                    connectUpdateData();

                    Toast.makeText(DeleteActivity.this,sCode + "가 삭제되었습니다." , Toast.LENGTH_SHORT).show();

                    intent = new Intent(DeleteActivity.this, SelectAllActivity.class);
                    intent.putExtra("macIP", macIP);
                    startActivity(intent);
                    break;
                case R.id.delete_Btn_Back:
                    finish();
            }


        }
    };
    private void connectUpdateData(){
        try {
            CUDNetworkTask updateNetworkTask = new CUDNetworkTask(DeleteActivity.this, urlAddress);
            updateNetworkTask.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}