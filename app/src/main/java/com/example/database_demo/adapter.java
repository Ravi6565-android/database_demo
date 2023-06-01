package com.example.database_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {

    Context context;
    ArrayList nameArray;
    ArrayList numberArray;
    public adapter(Context context, ArrayList nameArray, ArrayList numberArray) {
        this.context=context;
        this.nameArray= nameArray;
        this.numberArray=numberArray;
    }

    @NonNull
    @Override
    public adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_conctact_layout,parent,false);
        viewholder viewholder=new viewholder(view);


        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.viewholder holder, int position) {

        holder.number.setText( numberArray.get(position).toString());
        System.out.println("name aray======="+nameArray.get(position));

        holder.name.setText(nameArray.get(position).toString());
        System.out.println("number aray======="+numberArray.get(position));

    }

    @Override
    public int getItemCount() {
        return nameArray.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name,number;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.re_name);
            number=itemView.findViewById(R.id.re_number);
        }
    }
}
