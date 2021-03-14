package com.hyl.finallyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hyl.finallyapplication.Bean.Users;
import com.hyl.finallyapplication.utils.DatabaseHelper;

public class loginActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper = new DatabaseHelper
            (this, "database.db", null, 1);

    private EditText username,password;
    private Button login,regist;

    private CheckBox cbRemenberPassword;
    private SharedPreferences sharedPreferences;
    int cid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        regist=findViewById(R.id.regist);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                Users users=new Users(name,pass);
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                String sql="select * from users where username=? and password=?";
                Cursor cursor=sqLiteDatabase.rawQuery(sql, new String[]{name,pass});
                System.out.println(cursor.moveToFirst());
                if(cursor.moveToFirst()==false&&name!=null&pass!=null){
                    cursor.close();
                   sqLiteDatabase.execSQL
                            ("insert into Users(username, password, cid) " + "values(?, ?, ?)",
                                    new String[]{users.getUsername(),users.getPassword(),cid++ +""}
                            );
                   Toast.makeText(loginActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                String sql="select * from users where username=? and password=?";
                Cursor cursor=sqLiteDatabase.rawQuery(sql, new String[]{name,pass});
                if(cursor.moveToFirst()==true){
                    cursor.close();
                    Toast.makeText(loginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(loginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
