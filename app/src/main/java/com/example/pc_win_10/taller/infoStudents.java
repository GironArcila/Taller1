package com.example.pc_win_10.taller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pc_win_10.taller.Connection.Conexion;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infoStudents.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infoStudents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoStudents extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View indexView;

    private OnFragmentInteractionListener mListener;
    private Conexion conexion;
    private SQLiteDatabase db;
    private EditText codigo,nombre,email;
    private Button registrar;

    public infoStudents() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoStudents.
     */
    // TODO: Rename and change types and number of parameters
    public static infoStudents newInstance(String param1, String param2) {
        infoStudents fragment = new infoStudents();
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
        conexion=new Conexion(getContext(),"Usuario",null,1);
         db= conexion.getWritableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        indexView = inflater.inflate(R.layout.fragment_info_students, container, false);

        codigo = (EditText)indexView.findViewById(R.id.Codigo);
        nombre = (EditText)indexView.findViewById(R.id.Nombre);
        email = (EditText)indexView.findViewById(R.id.Email);
        registrar = (Button)indexView.findViewById(R.id.RegistrarStudent);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(codigo.getText().toString().trim().equals("")||
                        nombre.getText().toString().trim().equals("")||
                        email.getText().toString().trim().equals(""))
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
                }else{


                    try {
                        String query = "insert into estudiantes (cedula,name,email) values ('" + codigo.getText().toString().trim() + "','" +
                                nombre.getText().toString().trim() + "','" +
                                email.getText().toString().trim() + "','";

                        db.execSQL(query);


                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Add the buttons
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                // User clicked OK button
                            }


                        });


                        AlertDialog dialog = builder.create();
                        dialog.setMessage("Estudiante registrado satisfactoriamente :)");
                        dialog.show();
                    } catch (Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        AlertDialog dialog = builder.create();
                        dialog.setMessage("Ya existe un estudiante con este codigo");
                        dialog.show();
                        codigo.setText("");
                        email.setText("");
                        nombre.setText("");


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
}
