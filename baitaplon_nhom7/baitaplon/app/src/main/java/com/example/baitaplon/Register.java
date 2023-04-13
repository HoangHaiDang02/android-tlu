package com.example.baitaplon;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    public SqlLiteRepositries sqLiteDatabase = new SqlLiteRepositries(this, "register.sqlite", null, 1);
    @Override
    protected void onCreate(Bundle saveIntanceState) {
        super.onCreate(saveIntanceState);
        setContentView(R.layout.register_main);

        //create table register
        sqLiteDatabase.QueryData("CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(200), password varchar(200), email varchar(255))");

        Button buttonRegister = (Button) findViewById(R.id.button_register);

        Button buttonOutRegister = (Button) findViewById(R.id.button_back_register);

        EditText userName_ed = (EditText) findViewById(R.id.edit_taikhoan);

        EditText passWord_ed = (EditText) findViewById(R.id.edit_matkhau);

        EditText passWordConfirm_ed = (EditText) findViewById(R.id.edit_matkhau_lai);
        
        EditText email_register = (EditText) findViewById(R.id.edit_email);



        buttonOutRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = userName_ed.getText().toString();
                String pass = passWord_ed.getText().toString();
                String passConfirm = passWordConfirm_ed.getText().toString();
                String email = email_register.getText().toString();
                if ((pass.equals(passConfirm))) {
                    if(checkUser("User", user_name) == false) {
                        insertDataInDB("User", user_name, pass, email);
                        Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Register.this, "Tai khoan da ton tai", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Register.this, "RegisterFalse", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void insertDataInDB(String table, String value1, String value2, String value3)
    {
        String queryInsertDB = "insert into "+table+"(username,password,email) values('"+value1+"','"+value2+"','"+value3+"')";
        sqLiteDatabase.QueryData(queryInsertDB);
    }

    private Boolean checkUser(String table, String username) {
        Boolean result = false;
        String querrySelectData = "select * from "+table+" where username='"+username+"'";
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
