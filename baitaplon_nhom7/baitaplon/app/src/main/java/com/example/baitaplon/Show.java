package com.example.baitaplon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Show extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mahinhchinh);
        Button button_out = (Button) findViewById(R.id.button_out_mhc);
        Button button_show = (Button) findViewById(R.id.login_show);

        button_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Login.class);
                startActivity(intent);
                finish();
            }
        });

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,TodoList.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
