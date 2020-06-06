package com.example.wallpaper;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class myadapter extends RecyclerView.Adapter<myadapter.myViewHolder> {
    private Student[] students;
    private Context context;

    public myadapter(Context context, Student[] students) {
        this.students = students;
        this.context = context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        final Student item = students[position];
        holder.tvname.setText(students[position].getFname() + " " + students[position].getLname());
        holder.tvrollno.setText("Roll No: " + students[position].getRollno());
        holder.tvphone.setText("Ph: " + students[position].getPhone());
        Log.d("mmm", "onBindViewHolder: " + students[position].getPhone());
        holder.tvaddress.setText("add: " + students[position].getAddress());
        Log.d("mmm", "onBindViewHolder: " + students[position].getAddress());

        holder.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.cv);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.update_btn_rec:
                                Toast.makeText(context, "update btn clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete_btn_rec:
                                Toast.makeText(context, "delete btn clicked", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.length;
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView tvname, tvrollno, tvphone, tvaddress;
        CardView cv;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvname = itemView.findViewById(R.id.name_user);
            this.tvrollno = itemView.findViewById(R.id.rollno_user);
            this.tvphone = itemView.findViewById(R.id.phone_user);
            this.tvaddress = itemView.findViewById(R.id.address_user);
            this.cv = itemView.findViewById(R.id.card_user);

        }
    }
}
