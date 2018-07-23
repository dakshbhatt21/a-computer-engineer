package com.acomputerengineer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.acomputerengineer.Utils.DatabaseHelper;

import java.util.ArrayList;

public class SqliteCRUDActivity extends AppCompatActivity {

    TextView tv;

    DatabaseHelper dh;
    DatabaseHelper.User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_crud);

        tv = findViewById(R.id.tv);

        dh = new DatabaseHelper(SqliteCRUDActivity.this);
        u = dh.new User();

        u.setFirstName("John");
        u.setLastName("Doe");
        u.setAge(25);

        //create user
        long id = dh.createUser(u);
        tv.setText("User created, id: " + id);
        tv.append("\nname: " + u.getFirstName() + " " + u.getLastName());
        tv.append("\nage: " + u.getAge());

        u.setId(id);
        //update user
        u.setAge(30);
        dh.updateUser(u);
        tv.append("\n\nUser updated, id: " + u.getId());
        tv.append("\nname: " + u.getFirstName() + " " + u.getLastName());
        tv.append("\nage: " + u.getAge());

        //read all users
        ArrayList<DatabaseHelper.User> alUser = dh.readUsers();
        tv.append("\n\nRead all users, size: " + alUser.size());


        //read single user
        DatabaseHelper.User u1 = dh.readUser(u.getId());
        tv.append("\n\nRead single user, id: " + u1.getId());
        tv.append("\nname: " + u1.getFirstName() + " " + u1.getLastName());
        tv.append("\nage: " + u1.getAge());

        //delete user
        dh.deleteUser(u);
        tv.append("\n\nDelete user, id: " + u.getId());

        alUser = dh.readUsers();
        tv.append("\n\nRead all users, size: " + alUser.size());
    }
}
