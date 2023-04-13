package com.example.baitaplon;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Doimk extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doimk);
        Intent intent = this.getIntent();
        String emailChange = intent.getStringExtra("email");
        String user = intent.getStringExtra("user");

        Button doimk = (Button) findViewById(R.id.doimk);
        EditText mk = (EditText) findViewById(R.id.edit_matkhau);
        EditText mkcf = (EditText) findViewById(R.id.edit_matkhaumoi);
        Button out = (Button) findViewById(R.id.button_out_dmk);

        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mk.getText().toString()).equals(mkcf.getText().toString()))
                {
                    String sql = "UPDATE User " +
                            "SET password = "+mk.getText().toString()
                            + " WHERE email = '"+emailChange+"'"
                            + " and username = '"+user+"'";
                    SqlLiteRepositries sqLiteDatabase = new SqlLiteRepositries(Doimk.this, "register.sqlite", null, 1);
                    sqLiteDatabase.QueryData(sql);
                    Toast.makeText(Doimk.this, "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Doimk.this, "kiem tra lai", Toast.LENGTH_SHORT).show();

                }
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, Login.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}
