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

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class QuenMk extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmk_main);


        Button layma = (Button) findViewById(R.id.lapma);
        EditText email = (EditText) findViewById(R.id.edit_email);
        EditText taikhoan = (EditText) findViewById(R.id.edit_taikhoan);
        Button out = (Button) findViewById(R.id.button_out_qmk);


        layma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String taikhoanText = taikhoan.getText().toString();

                if (checkUserWithEmail("User",taikhoanText,emailText))
                {
                    final String emailSend = "hoanghaidange@gmail.com";
                    final String passwordSend = "dgpgdywhpgzzmxvu";
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");
                    props.put("mail.smtp.ssl.enable", "true");

                    Authenticator authenticator = new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(emailSend,passwordSend);
                        }
                    };
                    Session session = Session.getInstance(props, authenticator);
                    try {
                        Random maRandom = new Random();
                        int randomNUM = maRandom.nextInt(10000);
                        String maRandomToString = String.format("%04d", randomNUM);

                        MimeMessage message = new MimeMessage(session);
                        message.addRecipient(Message.RecipientType.TO,new InternetAddress((String) emailText + "@gmail.com"));
                        message.setSubject("Subject: Android app email");
                        message.setText("ma gui: "+maRandomToString);
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Transport.send(message);
                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        thread.start();
                        Intent intent = new Intent(context,MaXacMinh.class);
                        intent.putExtra("maxacminh",maRandomToString);
                        intent.putExtra("email",emailText);
                        intent.putExtra("user",taikhoanText);
                        startActivity(intent);
                        finish();

                    } catch (AddressException e) {
                        throw new RuntimeException(e);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(QuenMk.this, "Kiem tra lai email hoac tai khoan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

        private Boolean checkUserWithEmail(String table, String username, String email) {
            Boolean result = false;
            String querrySelectData = "select * from "+table+" where username='"+username+"' and email='"+email+"'";
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
