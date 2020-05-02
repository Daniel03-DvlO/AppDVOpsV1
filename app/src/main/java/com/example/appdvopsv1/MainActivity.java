package com.example.appdvopsv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "mistratamientosdb";

    EditText usuario, password;
    Button entrar;
    TextInputLayout txtVusuario, txtVpassword;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        usuario = findViewById(R.id.UsuarioTxt);
        password = findViewById(R.id.Contraseñatxt);
        entrar = findViewById(R.id.btnIngresar);
        txtVusuario = findViewById(R.id.txtVusuario);
        txtVpassword = findViewById(R.id.txtVpassword);

        //crear base
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTablaTratamientos();
        insertarDatosPrueba();


        ClickLogin();
        
    }

    private void ClickLogin() {

        /**
         * Login
         */
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuario.getText().toString().trim().isEmpty()) {

                    Snackbar snackbar = Snackbar.make(view, "Ingrese los datos",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
                    snackbar.show();
                    txtVusuario.setError("Ingresar Usuario");
                }
                if (password.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Ingrese los datos",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
                    snackbar.show();
                    txtVpassword.setError("Ingresar Contraseña");
                }

                if (usuario.getText().toString().equals("devil_ops") && password.getText().toString().equals("1234")){
                    Snackbar snackbar = Snackbar.make(view, "Ingresando...",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                    abrirUsuarios();
                }
                else {
                    Snackbar snackbar = Snackbar.make(view, "Usuario o contraseña invalidos",
                            Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
                    snackbar.show();
                }
            }

        });

        /**
         * Limpieza de Error de Usuario
         */
        usuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtVusuario.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /**
         * Limpieza de Error de Password
         */
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtVpassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void abrirUsuarios(){
        startActivity(new Intent(MainActivity.this, Usuarios.class));

    }

    private void createTablaTratamientos() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS tratamientos (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT tratamientos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    nombreCorto varchar(200) NOT NULL,\n" +
                        "    fechaInicio datetime NOT NULL,\n" +
                        "    horaInicio varchar(200) NOT NULL,\n" +
                        "    dias INTEGER NOT NULL,\n" +
                        "    nombreMedicamento varchar(200) NOT NULL,\n" +
                        "    vecesDia INTEGER NOT NULL,\n" +
                        "    cantidadActual INTEGER NOT NULL\n" +
                        ");"
        );
    }

    private void insertarDatosPrueba(){
        String insertSQL = "INSERT INTO tratamientos \n" +
                "(nombreCorto, fechaInicio, horaInicio, dias, nombreMedicamento, vecesDia, cantidadActual)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?);";

        mDatabase.execSQL(insertSQL, new String[]{"nombreCorto" + new Random().nextInt(100), "2020-05-02 12:00:00", "16:00", "4", "nombreMedicamento1", "1", "99"});

    }
}
