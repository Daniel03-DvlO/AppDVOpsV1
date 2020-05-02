package com.example.appdvopsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        activityMiPerfil();
        activityHijo();
        activityMama();
        activityEsposa();
    }
    private void activityMiPerfil(){
        Button miPerfil = (Button) findViewById(R.id.btnPerfil);
        miPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Usuarios.this, usuarioMiPerfil.class));
            }
        });
    }
    private void activityHijo(){
        Button hijo = (Button) findViewById(R.id.btnHijo);
        hijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Usuarios.this, usuarioHijo.class));
            }
        });
    }
    private void activityMama(){
        Button mama = (Button) findViewById(R.id.btnMama);
        mama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Usuarios.this, usuarioMama.class));
            }
        });
    }
    private void activityEsposa(){
        Button esposa = (Button) findViewById(R.id.btnEsposa);
        esposa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Usuarios.this, usuarioEsposa.class));
            }
        });
    }
}
