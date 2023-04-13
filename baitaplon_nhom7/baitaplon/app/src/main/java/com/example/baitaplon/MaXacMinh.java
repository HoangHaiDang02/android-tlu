package com.example.baitaplon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.CompletableFuture;

public class MaXacMinh extends AppCompatActivity {
    Context context =this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xacminh);
        Intent intent = this.getIntent();
        String check = intent.getStringExtra("maxacminh");
        String emailChange = intent.getStringExtra("email");
        String user = intent.getStringExtra("user");

        Button doimk = (Button) findViewById(R.id.doimk);
        EditText maXacMinh = (EditText) findViewById(R.id.edit_ma);
        Button out = (Button) findViewById(R.id.button_out_xm);

        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maCheck = maXacMinh.getText().toString();

                if (check.equals(maCheck))
                {
                    Intent intent1 = new Intent(context,Doimk.class);
                    intent1.putExtra("email",emailChange);
                    intent1.putExtra("user",user);
                    startActivity(intent1);
                } else {
                    Toast.makeText(context, maCheck + "" + check, Toast.LENGTH_SHORT).show();
                }
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context,Login.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}
