package com.example.vutuan.studentinformation;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtIdentityCard, edtMoreInformation;
    private RadioGroup rgCertificate;
    private Button btnSend;
    private CheckBox rbGame, rbFilm, rbBook;

    private String name, identityCard, moreInformation, certificate, favorite ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName= (EditText) findViewById(R.id.edtName);
        edtIdentityCard= (EditText) findViewById(R.id.edtIdentityCard);
        edtMoreInformation= (EditText) findViewById(R.id.edtMoreInformation);

        rgCertificate= (RadioGroup) findViewById(R.id.rgCertificate);
        rbBook= (CheckBox) findViewById(R.id.rbBook);
        rbFilm= (CheckBox) findViewById(R.id.rbFilm);
        rbGame= (CheckBox) findViewById(R.id.rbGame);

        btnSend= (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);

        rgCertificate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbCollege:
                        certificate = "Cao đẳng";
                        break;
                    case R.id.rbUniversity:
                        certificate = "Đại học";
                        break;
                    case R.id.rbIntermediate:
                        certificate = "Trung cấp";
                        break;
                }
            }
        });

        rbBook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (favorite==""){
                        favorite+="Đọc sách";
                    }else {
                        favorite+=" - Đọc sách";
                    }

                }
            }
        });
        rbGame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (favorite==""){
                        favorite+="Chơi game";
                    }else {
                        favorite+=" - Chơi game";
                    }
                }
            }
        });
        rbFilm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (favorite==""){
                        favorite+="Xem phim";
                    }else {
                        favorite+=" - Xem phim";
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnSend){



            moreInformation=edtMoreInformation.getText().toString();
            if (checkInformation(v)==true){
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Thông tin cá nhân");
                builder.setMessage(name+"\r\n"+identityCard+"\r\n"+certificate+"\r\n"+ favorite+"\r\n"+
                                    "------------------------------------------------------\r\n"+
                                    "Thông tin bổ sung: \r\n"+ moreInformation+"\r\n"+
                                    "------------------------------------------------------");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        }
    }

    private boolean checkInformation(View v) {
        name = edtName.getText().toString();
        if (name.length() < 3) {
            Toast.makeText(MainActivity.this, "Họ tên phải chứa ít nhất 3 ký tự! ", Toast.LENGTH_LONG).show();
            return false;
        } else {
            identityCard = edtIdentityCard.getText().toString();
            if (identityCard.length() != 9) {
                Toast.makeText(MainActivity.this, "CMND phải đủ 9 chữ số! ", Toast.LENGTH_LONG).show();
                return false;
            } else {
                return true;
            }
        }
    }
}
