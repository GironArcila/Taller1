package com.example.pc_win_10.taller;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.pc_win_10.taller.Connection.Conexion;

import java.util.ArrayList;

public class StudentsActivity extends AppCompatActivity implements infoStudents.OnFragmentInteractionListener {

    Conexion conexion;
    SQLiteDatabase db;
    private ListView listaEstudiantes;
    boolean estado;
    private Button habilitar;
    //private View indexView;
    FormEstudiantes obj;
    infoStudents obj2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT);
        //Con este dato gestiona las funciones del boton
        String Permiso = getIntent().getStringExtra("Permission");
        habilitar = (Button) findViewById(R.id.RegEstu);
        System.out.println(Permiso);

       if(Permiso.compareTo("Administrador")==0) {
           habilitar.setVisibility(View.VISIBLE);
           System.out.println("sisas");
       }
       else{
           habilitar.setVisibility(View.INVISIBLE);
           System.out.println("nonas");
       }


        //}
        consultarEstudiantes();
        //Con este dato gestiona las funciones del boton
        //String Permiso = getIntent().getStringExtra("Permission");
        
    }

    public void consultarEstudiantes()
    {
       try {
           conexion = new Conexion(this, "Usuario", null, 1);
           System.out.println("Creando conexion...");
           System.out.println(conexion.getDatabaseName());
           db = conexion.getReadableDatabase();
           Cursor c = db.rawQuery("SELECT * FROM estudiantes", null);
           System.out.println("Consultando...");
           ArrayList<String> estudiantes = new ArrayList<String>();
           if (c.moveToFirst()) {
               do {
                   estudiantes.add(c.getString(1));
               } while (c.moveToNext());
           }
           c.close();

           ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                   this,
                   android.R.layout.simple_list_item_1,
                   estudiantes);

           listaEstudiantes.setAdapter(arrayAdapter);
       }catch(Exception e) {
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           AlertDialog dialog = builder.create();
           dialog.setMessage("No existen registros aun");
           dialog.show();
       }
    }

    public void RegistrarEstudiantes(View g){
        obj2 = new infoStudents();
        obj = new FormEstudiantes();
        android.support.v4.app.FragmentManager Manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = Manager.beginTransaction();
        //transaction.add(R.id.StudentsForm,obj2);
        transaction.add(R.id.StudentsForm,obj2);
        transaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
