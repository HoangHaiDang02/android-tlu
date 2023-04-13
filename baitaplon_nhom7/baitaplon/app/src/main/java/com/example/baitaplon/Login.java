package com.example.baitaplon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    Context context;

    Register register;

    @Override
    protected void onCreate(Bundle saveIntanceState) {
        super.onCreate(saveIntanceState);
        setContentView(R.layout.login_main);
        context = this;
        Button buttonRegister = (Button) findViewById(R.id.button_register);
        Button buttonOutApp = (Button) findViewById(R.id.button_out);
        Button buttonLogin = (Button) findViewById(R.id.login);

        EditText username = (EditText) findViewById(R.id.edit_taikhoan);
        EditText password = (EditText) findViewById(R.id.edit_matkhau);

        TextView clickTextQuenMk = (TextView) findViewById(R.id.quenmk);

        clickTextQuenMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuenMk.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Register.class);
                startActivity(intent);
                finish();
            }
        });

        buttonOutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (checkUser("User", user,pass) == true)
                {
                    Intent intent = new Intent(context,Show.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(context, "user not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean checkUser(String table, String username, String password) {
        Boolean result = false;
        String querrySelectData = "select * from "+table+" where username='"+username+"' and password='"+password+"'";
        SqlLiteRepositries sql = new SqlLiteRepositries(this, "register.sqlite", null, 1);
        Boolean resultDB = sql.QueryDataTableCheckSelectUser(querrySelectData);
        if (resultDB)
        {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}