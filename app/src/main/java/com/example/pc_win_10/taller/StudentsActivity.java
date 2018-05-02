package com.example.pc_win_10.taller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pc_win_10.taller.Connection.Conexion;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity {

    Conexion conexion;
    SQLiteDatabase db;
    private ListView listaEstudiantes;
    private Button habilitar;
    //private View indexView;
    boolean prueba = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT);

        habilitar = (Button) findViewById(R.id.RegEstu);

        if(prueba)
        {
            habilitar.setEnabled(false);
            habilitar.setVisibility(0);
        }
        //consultarEstudiantes();
    }

    public void consultarEstudiantes()
    {
        conexion =   new Conexion(this,"Usuario",null,1);
        System.out.println("Creando conexion...");
        System.out.println(conexion.getDatabaseName());
        db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM estudiantes",null);
        System.out.println("Consultando...");
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


}
