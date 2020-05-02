package com.example.appdvopsv1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class tratamientoMiperfil extends AppCompatActivity {

    List<Tratamiento> listaTratamientos;
    SQLiteDatabase mDatabase;
    ListView listViewTratamientos;
    TratamientosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento_miperfil);

        listViewTratamientos = (ListView) findViewById(R.id.lvTratamientos);
        listaTratamientos = new ArrayList<>();

        //Abrir Base
        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        //Mostrar Datos
        muestraTratamientosDesdeBase();

        nuevoTratamiento();
    }

    private void muestraTratamientosDesdeBase() {

        Cursor cursorTratamientos = mDatabase.rawQuery("SELECT * FROM tratamientos", null);


        if (cursorTratamientos.moveToFirst()) {

            do {

                listaTratamientos.add(new Tratamiento(
                        cursorTratamientos.getInt(0),
                        cursorTratamientos.getString(1),
                        cursorTratamientos.getString(2),
                        cursorTratamientos.getString(3),
                        cursorTratamientos.getInt(4),
                        cursorTratamientos.getString(5),
                        cursorTratamientos.getInt(6),
                        cursorTratamientos.getInt(7)
                ));
            } while (cursorTratamientos.moveToNext());
        }

        cursorTratamientos.close();


        adapter = new TratamientosAdapter(this, R.layout.layout_lista_tratamiento, listaTratamientos, mDatabase);

        listViewTratamientos.setAdapter(adapter);
    }

    private void nuevoTratamiento(){
        Button añadir = (Button) findViewById(R.id.btnAñadirTratamiento);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tratamientoMiperfil.this, layoutIngresarTratamiento.class));
            }
        });
    }

}
