package com.example.sign_up_page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name_id,email_id,password_id;


    public MyAdapter(Context context, ArrayList name_id, ArrayList email_id, ArrayList password_id) {
        this.context = context;
        this.name_id = name_id;
        this.email_id = email_id;
        this.password_id = password_id;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.password_id.setText(String.valueOf(password_id.get(position)));



    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id,email_id,password_id;
        ImageView image_dlt,image_edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_id = itemView.findViewById(R.id.textname);
            email_id = itemView.findViewById(R.id.textemail);
            password_id = itemView.findViewById(R.id.textpassword);

        }
    }
}
