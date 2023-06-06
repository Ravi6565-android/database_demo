package com.example.database_demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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
                    datas.remove(position);
                  notifyDataSetChanged();

                });
                builder.show();




                return true;
            }

        });
holder.more.setOnClickListener(new View.OnClickListener() {
     @Override
    public void onClick(View view) {
            PopupMenu popupMenu= new PopupMenu(context,holder.more);
            popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId()==R.id.m_delete){
                        mydbhelper mydbhelper= new mydbhelper(context);
                        mydbhelper.deletdata(datas.get(position).id);
                        datas.remove(position);
                        notifyDataSetChanged();
               }
                    if(item.getItemId()==R.id.m_update){
                        mydbhelper mydbhelper= new mydbhelper(context);
                        Dialog dialog= new Dialog(context);
                        dialog.setContentView(R.layout.edit_activity);
                        EditText name,number;
                        Button add;
                        name=dialog.findViewById(R.id.etname);
                        number=dialog.findViewById(R.id.etnumber);
                        add=dialog.findViewById(R.id.add);
                        add.setOnClickListener(v -> {
                            String sname,snumber;
                            sname=name.getText().toString();
                            snumber=number.getText().toString();
                            data_manage manage= new data_manage();
                            manage.setId(position);
                            manage.setName(sname);
                            manage.setNumber(snumber);
                            mydbhelper.updatedata(datas.get(position).id,sname,snumber);
                            datas.remove(position) ;
                            datas.add(position,manage);

                            notifyDataSetChanged();
                            dialog.dismiss();
                        });

                        dialog.show();
                    }

                    return true;
                }
            });
            popupMenu.show();
    }
});
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name,number;
        ImageButton more;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.re_name);
            number=itemView.findViewById(R.id.re_number);
            more=itemView.findViewById(R.id.more);
        }
    }
}
