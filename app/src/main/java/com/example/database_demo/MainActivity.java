package com.example.database_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etname,etnumber;
    Button add,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname=findViewById(R.id.etname);
        etnumber=findViewById(R.id.etnumber);
        add=findViewById(R.id.add);
        update=findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydbhelper mydbhelper= new mydbhelper(MainActivity.this);
                String name,number;
                int id=2;
                name=etname.getText().toString();
                number=etnumber.getText().toString();
                mydbhelper.updatedata(id,name,number);
            }
        });
        add.setOnClickListener(v -> {
            mydbhelper mydbhelper= new mydbhelper(this);
            String name,number;
            int id=3;
            name=etname.getText().toString();
            number=etnumber.getText().toString();
            mydbhelper.adddata(name,number);
           // mydbhelper.deletdata(id);
          //  Cursor cursor=mydbhelper.showall();
//            while(cursor.moveToNext()){
//                System.out.println("id"+cursor.getInt(0));
//                System.out.println("name"+cursor.getString(1));
//                System.out.println("number"+cursor.getString(2));
//            }

        });
    }
}