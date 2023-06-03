package com.example.database_demo;

import android.app.AlertDialog;
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
    ArrayList<data_manage> datas;
    public adapter(Context context, ArrayList datas) {
        this.context=context;
        this.datas=datas;
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

        holder.number.setText( datas.get(position).number);

        holder.name.setText(datas.get(position).name);

        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("DELETE");
                builder.setMessage("Are you sure you want to delete this contact");
                builder.setNegativeButton("no",(dialog, which) -> {
                    builder.setCancelable(true);

                });
                builder.setPositiveButton("DELETE", (dialog, which) -> {
                    mydbhelper mydbhelper= new mydbhelper(context);
                    mydbhelper.deletdata(datas.get(position).id);

                    notifyDataSetChanged();
                });
                builder.show();




                return true;
            }

        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
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
