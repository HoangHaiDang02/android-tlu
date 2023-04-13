package com.example.baitaplon;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class TodoList extends AppCompatActivity {
    ListView listCV;
    ArrayList<CongViec> arrayCV;
    CongViecAdapter adapter;
    public SqlLiteRepositries sqLiteDatabase = new SqlLiteRepositries(this, "register.sqlite", null, 1);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_main);
        listCV = (ListView) findViewById(R.id.listView);
        arrayCV = new ArrayList<>();
        adapter = new CongViecAdapter(this,R.layout.todo_list,arrayCV);
        listCV.setAdapter(adapter);
        String sql = "CREATE TABLE IF NOT EXISTS User_Todo (Id_todo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TenCV nvarchar(255),Id_User integer ," +
                "FOREIGN KEY (Id_User) REFERENCES User(Id))";
        sqLiteDatabase.QueryData(sql);

//        insert test
//        sqLiteDatabase.QueryData("insert into User_Todo values (null,'an com',3)");

//        Cursor dataCV = sqLiteDatabase.GetData("Select Id_todo,TenCV  from User_Todo");
//        while (dataCV.moveToNext())
//        {
//            int id = dataCV.getInt(0);
//            String ten = dataCV.getString(1);
//            arrayCV.add(new CongViec(id,ten));
//        }
        arrayCV.add(new CongViec(1,"testing"));
        adapter.notifyDataSetChanged();
    }
}
