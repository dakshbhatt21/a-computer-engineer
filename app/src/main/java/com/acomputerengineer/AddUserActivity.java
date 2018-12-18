package com.acomputerengineer;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acomputerengineer.Interfaces.UserDao;
import com.acomputerengineer.Models.User;
import com.acomputerengineer.Utils.UserDatabase;

import java.lang.ref.WeakReference;

public class AddUserActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etEmail, etAddress;
    private Button btnAdd;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);

        btnAdd = findViewById(R.id.btn_add);

        if (getIntent().hasExtra("id")) {
            id = getIntent().getIntExtra("id", 0);
            btnAdd.setText("Update");
            new GetUser(AddUserActivity.this, id).execute();
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                if (getIntent().hasExtra("id")) {
                    if (0 != firstName.length() && 0 != lastName.length()) {
                        User u = new User();
                        u.setId(id);
                        u.setFirstName(firstName);
                        u.setLastName(lastName);
                        u.setPhone(phone);
                        u.setEmail(email);
                        u.setAddress(address);

                        new UpdateUser(AddUserActivity.this, u).execute();
                    } else {
                        Toast.makeText(AddUserActivity.this, "Please enter first name and last name.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (0 != firstName.length() && 0 != lastName.length()) {
                        User u = new User();
                        u.setFirstName(firstName);
                        u.setLastName(lastName);
                        u.setPhone(phone);
                        u.setEmail(email);
                        u.setAddress(address);

                        new AddUser(AddUserActivity.this, u).execute();
                    } else {
                        Toast.makeText(AddUserActivity.this, "Please enter first name and last name.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    static class AddUser extends AsyncTask<Void, Void, Void> {
        private User u;
        private WeakReference<Context> c;

        public AddUser(Context c, User u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());
            ud.userDao().insert(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "User added successfully!", Toast.LENGTH_SHORT).show();
            ((Activity) c.get()).finish();
        }
    }

    static class UpdateUser extends AsyncTask<Void, Void, Void> {
        private User u;
        private WeakReference<Context> c;

        public UpdateUser(Context c, User u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());
            ud.userDao().update(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "User updated successfully!", Toast.LENGTH_SHORT).show();
            ((Activity) c.get()).finish();
        }
    }

    static class GetUser extends AsyncTask<Void, Void, User> {
        private WeakReference<Context> c;
        private int id;

        public GetUser(Context c, int id) {
            this.c = new WeakReference<>(c);
            this.id = id;
        }

        @Override
        protected User doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());
            User u = ud.userDao().getUser(id);
            return u;
        }

        @Override
        protected void onPostExecute(User u) {
            super.onPostExecute(u);

            EditText etFirstName = ((Activity) c.get()).findViewById(R.id.et_first_name);
            EditText etLastName = ((Activity) c.get()).findViewById(R.id.et_last_name);
            EditText etPhone = ((Activity) c.get()).findViewById(R.id.et_phone);
            EditText etEmail = ((Activity) c.get()).findViewById(R.id.et_email);
            EditText etAddress = ((Activity) c.get()).findViewById(R.id.et_address);

            etFirstName.setText(u.getFirstName());
            etLastName.setText(u.getLastName());
            etPhone.setText(u.getPhone());
            etEmail.setText(u.getEmail());
            etAddress.setText(u.getAddress());
        }
    }
}
