package com.example.pc_win_10.taller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc_win_10.taller.Connection.Conexion;

public class Login extends AppCompatActivity implements Registrarse.OnFragmentInteractionListener {

        EditText username;
        EditText password;
        Registrarse obj;
        Conexion conexion;
        private Cursor fila;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            username = (EditText)findViewById(R.id.username);
            password = (EditText)findViewById(R.id.password);
            conexion=   new Conexion(this,"Usuario",null,1);


        }

        public void iniciar(View h){
            SQLiteDatabase db = conexion.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT name,password FROM usuarios WHERE name='"+username.getText().toString().trim()
                    +"' AND password='"+MD5.getMD5(password.getText().toString().trim())+"';",null);
            System.out.println("SELECT name,password FROM usuarios WHERE name='"+username.getText().toString().trim()
                    +"' AND password='"+MD5.getMD5(password.getText().toString().trim())+"';");
            if(c.moveToFirst()){
                Toast.makeText(this,"pasar activity",Toast.LENGTH_SHORT).show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
// Add the buttons
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        // User clicked OK button
                    }


                });


                AlertDialog dialog = builder.create();
                dialog.setMessage("Usuario NO registrado :(");
                dialog.show();
            }

        }

        public void regist(View g){
            obj = new Registrarse();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.frameLogin,obj);
            transaction.commit();

        }

        @Override
        public void onFragmentInteraction(Uri uri) {

        }
    }