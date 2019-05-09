package com.acomputerengineer.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.acomputerengineer.AddUserActivity;
import com.acomputerengineer.Models.User;
import com.acomputerengineer.R;
import com.acomputerengineer.Utils.UserDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.GridItemViewHolder> {

    private List<User> userList;

    private Context c;

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvEmail, tvAddress;
        Button btnEdit, btnDelete;

        public GridItemViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvPhone = view.findViewById(R.id.tv_phone);
            tvEmail = view.findViewById(R.id.tv_email);
            tvAddress = view.findViewById(R.id.tv_address);
            btnEdit = view.findViewById(R.id.btn_edit);
            btnDelete = view.findViewById(R.id.btn_delete);
        }
    }

    public UserAdapter(Context c, List userList) {
        this.c = c;
        this.userList = userList;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        final User user = userList.get(position);

        holder.tvName.setText(user.getFirstName());
        holder.tvName.append(" ");
        holder.tvName.append(user.getLastName());

        holder.tvPhone.setText(user.getPhone());
        holder.tvEmail.setText(user.getEmail());
        holder.tvAddress.setText(user.getAddress());

        holder.btnEdit.setTag(position);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());

                Intent i = new Intent(c, AddUserActivity.class);
                i.putExtra("id", userList.get(pos).getId());
                c.startActivity(i);
            }
        });

        holder.btnDelete.setTag(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = Integer.parseInt(v.getTag().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("Are you sure you want to delete this user?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               new DeleteUser(c, userList.remove(pos)).execute();
                               notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class DeleteUser extends AsyncTask<Void, Void, Void> {
        private User u;
        private WeakReference<Context> c;

        public DeleteUser(Context c, User u) {
            this.c = new WeakReference<>(c);
            this.u = u;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());
            ud.userDao().delete(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(c.get(), "User deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
