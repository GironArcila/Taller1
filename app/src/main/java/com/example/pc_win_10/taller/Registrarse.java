package com.example.pc_win_10.taller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_win_10.taller.Connection.Conexion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registrarse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registrarse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registrarse extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private Conexion conexion;
    private SQLiteDatabase bd;
    private View indexView;
    private EditText cedula,name,mail,pass;
    private Spinner spinner;
    private Button reg;

    public Registrarse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registrarse.
     */
    // TODO: Rename and change types and number of parameters
    public static Registrarse newInstance(String param1, String param2) {
        Registrarse fragment = new Registrarse();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

        //****************************
        conexion=new Conexion(getContext(),"Usuario",null,1);
        bd = conexion.getWritableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        indexView = inflater.inflate(R.layout.fragment_registrarse, container, false);
        //Toma de elementos para interaccion
        cedula = (EditText) indexView.findViewById(R.id.Identificacion);
        name = (EditText) indexView.findViewById(R.id.name);
        mail = (EditText) indexView.findViewById(R.id.email);
        pass = (EditText) indexView.findViewById(R.id.password);
        spinner = (Spinner) indexView.findViewById(R.id.tipo_Usuario);


        reg= (Button)indexView.findViewById(R.id.registrar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tipo_usuario, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cedula.getText().toString().trim().equals("")||
                        name.getText().toString().trim().equals("")||
                        mail.getText().toString().trim().equals("")||
                        pass.getText().toString().trim().equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Todos los campos son obligatorios.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });

                    // Create the AlertDialog object and return it
                    builder.create();
                    builder.show();
                }
                else {
                    try {
                        String query = "insert into usuarios (cedula,name,email,password,tipo) values ('" + cedula.getText().toString().trim() + "','" +
                                name.getText().toString().trim() + "','" +
                                mail.getText().toString().trim() + "','" +
                                MD5.getMD5(pass.getText().toString().trim()) + "','" +
                                spinner.getSelectedItem().toString().trim() + "');";
                        bd.execSQL(query);


                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Add the buttons
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent ir = new Intent(getActivity(), Login.class);
                                ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(ir);

                                // User clicked OK button
                            }


                        });


                        AlertDialog dialog = builder.create();
                        dialog.setMessage("Usuario registrado satisfactoriamente :)");
                        dialog.show();
                    }catch (Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        AlertDialog dialog = builder.create();
                        dialog.setMessage("Ya existe un usuario con esta cedula");
                        dialog.show();
                        cedula.setText("");
                        mail.setText("");
                        pass.setText("");
                        name.setText("");

                    }
                }


            }
        });


        return indexView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void registro(View v){
        /*if(conexion!=null){

            String query="insert into usuarios (name,password,tipo) values ("+name.getText().toString()+","+
                                                                            pass.getText().toString()+","+
                                                                            type.getText().toString()+");";
            bd.execSQL(query);
            Toast.makeText(getContext(),"inseto"+name.getText().toString(),Toast.LENGTH_SHORT).show();
        }*/
        System.out.println(name.getText().toString());
    }


}
