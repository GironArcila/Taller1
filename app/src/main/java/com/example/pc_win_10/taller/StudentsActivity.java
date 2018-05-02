package com.example.pc_win_10.taller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.pc_win_10.taller.Connection.Conexion;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity implements infoStudents.OnFragmentInteractionListener {

    Conexion conexion;
    SQLiteDatabase db;
    private ListView listaEstudiantes;
    infoStudents obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT);
        //consultarEstudiantes();
        //Con este dato gestiona las funciones del boton
        String Permiso = getIntent().getStringExtra("Permission");

        obj = new infoStudents();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.StudentsForm,obj);
        transaction.commit();
        
    }

    public void consultarEstudiantes()
    {
        Cursor c = db.rawQuery("SELECT * FROM estudiante", null);
        ArrayList<String> estudiantes= new ArrayList<String>();
        if(c.moveToFirst()){
            do {
                estudiantes.add(c.getString(1));
            }while (c.moveToNext());
        }
        c.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                estudiantes);

        listaEstudiantes.setAdapter(arrayAdapter);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
