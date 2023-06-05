package com.example.database_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etname,etnumber;
    Button add,update,delete;
    RecyclerView recyclerView;
    FloatingActionButton fab;
   ArrayList<data_manage> datas=new ArrayList<>();
     Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recyler);
        LinearLayoutManager manager= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);


        mydbhelper mydbhelper= new mydbhelper(MainActivity.this);
         cursor=mydbhelper.showall();
        show(cursor);

        adapter adapter= new adapter(MainActivity.this,datas);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Dialog dialog= new Dialog(this);
            dialog.setContentView(R.layout.edit_activity);


            etname= dialog.findViewById(R.id.etname);
            etnumber= dialog.findViewById(R.id.etnumber);
            add= dialog.findViewById(R.id.add);



            add.setOnClickListener(k -> {
                String name,number;
                name=etname.getText().toString();
                number=etnumber.getText().toString();
                mydbhelper.adddata(name,number);
                Cursor curs = mydbhelper.showall();
                datas.clear();
               show(curs);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();

            });
            dialog.show();
        });




    }
    public void show(Cursor cursor){
        while(cursor.moveToNext()){
            data_manage manage= new data_manage();
            manage.setId(cursor.getInt(0));
            manage.setName(cursor.getString(1));
            manage.setNumber(cursor.getString(2));
            datas.add(manage);


        }
    }


}