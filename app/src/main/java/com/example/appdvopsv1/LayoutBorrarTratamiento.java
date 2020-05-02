package com.example.appdvopsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LayoutBorrarTratamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_borrar_tratamiento);
        borrarAceptar();
        borrarCancelar();
    }
    private void borrarAceptar(){
        Button aceptar = (Button) findViewById(R.id.btnSi);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LayoutBorrarTratamiento.this, tratamientoMiperfil.class));
            }
        });
    }
    private void borrarCancelar(){
        Button cancelar = (Button) findViewById(R.id.btnNo);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LayoutBorrarTratamiento.this, tratamientoMiperfil.class));
            }
        });
    }
 }
