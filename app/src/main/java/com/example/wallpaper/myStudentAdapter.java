package com.example.wallpaper;

import android.content.Context;
import android.content.Intent;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class myStudentAdapter extends FirebaseRecyclerAdapter<Student, myStudentAdapter.myStudentviewHolder> {
    Context context;

    public myStudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final myStudentAdapter.myStudentviewHolder holder, int position, @NonNull final Student model) {
        holder.name.setText(model.getFname() + " " + model.getLname());
        holder.ph.setText("ph: "+model.getPhone() );
        holder.addr.setText(model.getAddress());
        holder.email.setText(model.getEmail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.cardView);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.view_btn_rec:
                                Intent intent = new Intent(context,ShowProfileActivity.class);
                                context.startActivity(intent);
                                Toast.makeText(context, "view btn clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.update_btn_rec:
                                Intent intent1=new Intent(context,ShowProfileActivity.class);
                                context.startActivity(intent1);
                                Toast.makeText(context, "update btn clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete_btn_rec:
                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("student").child(model.getId());
                                reference.removeValue();
                                Toast.makeText(context, "delete btn clicked", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });



    }

    @NonNull
    @Override
    public myStudentAdapter.myStudentviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user, parent, false);

        return new myStudentAdapter.myStudentviewHolder(view);
    }


    class myStudentviewHolder extends RecyclerView.ViewHolder {
        TextView name, ph, addr, email;
        CardView cardView;

        public myStudentviewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_user);
            ph = itemView.findViewById(R.id.phone_user);
            addr = itemView.findViewById(R.id.address_user);
            email = itemView.findViewById(R.id.rollno_user);
            cardView = itemView.findViewById(R.id.card_user);

        }
    }
}
