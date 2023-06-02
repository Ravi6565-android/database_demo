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
    ArrayList numberArray= new ArrayList();
    ArrayList nameArray= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recyler);
        LinearLayoutManager manager= new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        mydbhelper mydbhelper= new mydbhelper(MainActivity.this);
        Cursor cursor=mydbhelper.showall();
        while(cursor.moveToNext()){
            nameArray.add(cursor.getString(1));
            numberArray.add(cursor.getString(2));
        }
        adapter adapter= new adapter(MainActivity.this,nameArray,numberArray);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Dialog dialog= new Dialog(this);
            dialog.setContentView(R.layout.edit_activity);


            etname= dialog.findViewById(R.id.etname);
            etnumber= dialog.findViewById(R.id.etnumber);
            add= dialog.findViewById(R.id.add);
            update= dialog.findViewById(R.id.update);
            delete= dialog.findViewById(R.id.delete);



            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name,number;
                    name=etname.getText().toString();
                    number=etnumber.getText().toString();
                    mydbhelper.updatedata(1,name,number);
                    dialog.dismiss();
                }
            });




            add.setOnClickListener(k -> {
                String name,number;
                name=etname.getText().toString();
                number=etnumber.getText().toString();
                mydbhelper.adddata(name,number);
                Cursor curs = mydbhelper.showall();
                while(curs.moveToNext()){
                    nameArray.add(curs.getString(1));
                    numberArray.add(curs.getString(2));
                }
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
                // mydbhelper.deletdata(id);
                //  Cursor cursor=mydbhelper.showall();
//            while(cursor.moveToNext()){
//                System.out.println("id"+cursor.getInt(0));
//                System.out.println("name"+cursor.getString(1));
//                System.out.println("number"+cursor.getString(2));
//            }

            });
            dialog.show();
        });




    }


}