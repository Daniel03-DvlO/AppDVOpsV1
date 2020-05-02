package com.example.appdvopsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class declarar_toma_medicamentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_declarar_toma_medicamentos);
    }
    private void tomarSi(){
        Button aceptar = (Button) findViewById(R.id.btnSi);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(declarar_toma_medicamentos.this, tratamientoMiperfil.class));
            }
        });
    }
    private void borrarCancelar(){
        Button cancelar = (Button) findViewById(R.id.btnNo);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(declarar_toma_medicamentos.this, tratamientoMiperfil.class));
            }
        });
    }
}
