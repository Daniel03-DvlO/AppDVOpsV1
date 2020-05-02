package com.example.appdvopsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class usuarioMiPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_mi_perfil);
        tratamientoMiPerfil();
        medicamentosMiPerfill();
        doctoresMiPerfil();
    }
    private void tratamientoMiPerfil(){
        Button tratamiento = (Button) findViewById(R.id.btnTratamiento);
        tratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(usuarioMiPerfil.this, tratamientoMiperfil.class));
            }
        });
    }
    private void medicamentosMiPerfill(){
        Button medicamentos = (Button) findViewById(R.id.btnMedicamentos);
        medicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(usuarioMiPerfil.this, medicamentosMiperfil.class));
            }
        });
    }
    private void doctoresMiPerfil(){
        Button doctores = (Button) findViewById(R.id.btnDoctores);
        doctores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(usuarioMiPerfil.this, doctoresMiperfil.class));
            }
        });
    }
}
