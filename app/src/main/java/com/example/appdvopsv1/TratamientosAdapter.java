package com.example.appdvopsv1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class TratamientosAdapter extends ArrayAdapter<Tratamiento> {

    Context mCtx;
    int listaLayoutRes;
    List<Tratamiento> listaTratamientos;
    SQLiteDatabase mDatabase;

    public TratamientosAdapter(Context mCtx, int listaLayoutRes, List<Tratamiento> listaTratamientos, SQLiteDatabase mDatabase) {
        super(mCtx, listaLayoutRes, listaTratamientos);

        this.mCtx = mCtx;
        this.listaLayoutRes = listaLayoutRes;
        this.listaTratamientos = listaTratamientos;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listaLayoutRes, null);

        final Tratamiento tratamiento = listaTratamientos.get(position);

        TextView textViewNombreTratamiento = view.findViewById(R.id.textViewNombreTratamiento);
        TextView textViewFechaInicio = view.findViewById(R.id.textViewFechaInicio);
        TextView textViewCantidad = view.findViewById(R.id.textViewCantidad);

        textViewNombreTratamiento.setText(tratamiento.getNombreCorto());
        textViewFechaInicio.setText(tratamiento.getFechaInicio());
        textViewCantidad.setText(String.valueOf(tratamiento.getCantidadActual()));

        Button btnEliminar = view.findViewById(R.id.btnEliminarTratamiento);
        Button btnEditar = view.findViewById(R.id.btnEditarTratamiento);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), layoutIngresarTratamiento.class);
                intent.putExtra("idTratamiento", tratamiento.getId());
                mCtx.startActivity(intent);
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Seguro?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM tratamientos WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{tratamiento.getId()});
                        reloadTratamientosFromDatabase();
                        Toast.makeText(mCtx, "Eliminado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void reloadTratamientosFromDatabase() {
        Cursor cursorTratamientos = mDatabase.rawQuery("SELECT * FROM tratamientos", null);
        if (cursorTratamientos.moveToFirst()) {
            listaTratamientos.clear();
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
        notifyDataSetChanged();
    }

}
