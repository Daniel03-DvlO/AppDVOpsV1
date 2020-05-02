package com.example.appdvopsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class layoutIngresarTratamiento extends AppCompatActivity {

    Tratamiento tratamiento;
    SQLiteDatabase mDatabase;


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ingresar_tratamiento);
        Intent intent = getIntent();
        int id = intent.getIntExtra("idTratamiento", 0);
        //Abrir Base
        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        if(id > 0){
            //Mostrar Datos
            muestraTratamientoDesdeBase(id);
            if(tratamiento != null){
                TextView textViewNombre = (TextView) findViewById(R.id.etxtTratamiento);
                TextView textViewFecha = (TextView) findViewById(R.id.txtFecha);
                TextView textViewDias = (TextView) findViewById(R.id.txtDias);
                TextView textViewVeces = (TextView) findViewById(R.id.txtVeces);
                TextView textViewHoras = (TextView) findViewById(R.id.txtHora);
                TextView textViewNombreMedicamento = (TextView) findViewById(R.id.etxtNombreMedicamento);
                TextView textViewCantidadMedicamento = (TextView) findViewById(R.id.txtCantidadMedicamento);

                textViewNombre.setText(tratamiento.getNombreCorto());
                textViewFecha.setText(tratamiento.getFechaInicio());
                textViewDias.setText(String.valueOf(tratamiento.getDias()));
                textViewVeces.setText(String.valueOf(tratamiento.getVecesDias()));
                textViewHoras.setText(tratamiento.getHoraInicio());
                textViewNombreMedicamento.setText(tratamiento.getNombreMedicamento());
                textViewCantidadMedicamento.setText(String.valueOf(tratamiento.getCantidadActual()));
            }
        }

        GuardarTratamiento();
    }

    private void muestraTratamientoDesdeBase(int id) {

        Cursor cursorTratamientos = mDatabase.rawQuery("select * from tratamientos where ID = ?", new String[] {id+""});
        if (cursorTratamientos.moveToFirst()) {
            tratamiento = null;
            do {
                tratamiento = new Tratamiento(
                        cursorTratamientos.getInt(0),
                        cursorTratamientos.getString(1),
                        cursorTratamientos.getString(2),
                        cursorTratamientos.getString(3),
                        cursorTratamientos.getInt(4),
                        cursorTratamientos.getString(5),
                        cursorTratamientos.getInt(6),
                        cursorTratamientos.getInt(7)
                );
            } while (cursorTratamientos.moveToNext());
        }
        cursorTratamientos.close();
    }

    private void GuardarTratamiento() {
        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tratamiento = getTratamientoFromView();
                if(tratamiento.id == 0)
                    insertaTratamiento(tratamiento);
                else
                    updateTratamiento(tratamiento);
            }
        });
    }

    private void updateTratamiento(Tratamiento tratamiento) {
        if(tratamiento == null)
            return;

        String sql = "UPDATE tratamientos \n" +
                "SET nombreCorto = ?, \n" +
                "fechaInicio = ?, \n" +
                "dias = ?, \n" +
                "nombreMedicamento = ?, \n" +
                "vecesDia = ?, \n" +
                "cantidadActual = ? \n" +
                "WHERE id = ?;\n";

        if(tratamiento.nombreCorto != null && !tratamiento.nombreCorto.isEmpty()){
            mDatabase.execSQL(sql, new String[]{tratamiento.nombreCorto, tratamiento.fechaInicio, tratamiento.horaInicio, String.valueOf(tratamiento.dias), tratamiento.nombreMedicamento, String.valueOf(tratamiento.vecesDia), String.valueOf(tratamiento.cantidadActual), String.valueOf(tratamiento.getId())});
            Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertaTratamiento(Tratamiento tratamiento) {
        if(tratamiento == null)
            return;

        String insertSQL = "INSERT INTO tratamientos \n" +
                "(nombreCorto, fechaInicio, horaInicio, dias, nombreMedicamento, vecesDia, cantidadActual)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        if(tratamiento.nombreCorto != null && !tratamiento.nombreCorto.isEmpty()){
            mDatabase.execSQL(insertSQL, new String[]{tratamiento.nombreCorto, tratamiento.fechaInicio, tratamiento.horaInicio, String.valueOf(tratamiento.dias), tratamiento.nombreMedicamento, String.valueOf(tratamiento.vecesDia), String.valueOf(tratamiento.cantidadActual)});
            Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
        }

    }

    private Tratamiento getTratamientoFromView() {
        TextView textViewNombre = (TextView) findViewById(R.id.etxtTratamiento);
        TextView textViewFecha = (TextView) findViewById(R.id.txtFecha);
        TextView textViewDias = (TextView) findViewById(R.id.txtDias);
        TextView textViewVeces = (TextView) findViewById(R.id.txtVeces);
        TextView textViewHoras = (TextView) findViewById(R.id.txtHora);
        TextView textViewNombreMedicamento = (TextView) findViewById(R.id.etxtNombreMedicamento);
        TextView textViewCantidadMedicamento = (TextView) findViewById(R.id.txtCantidadMedicamento);

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.nombreCorto = textViewNombre.getText().toString();
        tratamiento.fechaInicio = textViewFecha.getText().toString();
        tratamiento.dias = Integer.parseInt(textViewDias.getText().toString());
        tratamiento.vecesDia = Integer.parseInt(textViewVeces.getText().toString());
        tratamiento.horaInicio = textViewNombreMedicamento.getText().toString();
        tratamiento.nombreMedicamento = textViewHoras.getText().toString();
        tratamiento.cantidadActual = Integer.parseInt(textViewCantidadMedicamento.getText().toString());

        return tratamiento;
    }
}