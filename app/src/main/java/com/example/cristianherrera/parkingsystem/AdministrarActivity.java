package com.example.cristianherrera.parkingsystem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministrarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Insertar, Consultar, Eliminar, Lista, Canvas;
    private EditText placa, hora_e, hora_s, vehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);

        placa = findViewById(R.id.etPlaca);
        vehiculo = findViewById(R.id.etVehiculo);
        hora_e = findViewById(R.id.etHora);
        hora_s = findViewById(R.id.etSalida);

        Insertar = findViewById(R.id.btnInsertar);
        Insertar.setOnClickListener(this);

        Consultar = findViewById(R.id.btnConsultar);
        Consultar.setOnClickListener(this);

        Eliminar = findViewById(R.id.btnEliminar);
        Eliminar.setOnClickListener(this);

        Lista = findViewById(R.id.btnLista);
        Lista.setOnClickListener(this);

        Canvas = findViewById(R.id.btnCanvas);
        Canvas.setOnClickListener(this);

    }

    //Swich de los Botones
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnInsertar:
                Insertar(); //Llamo la funcion
                break;

            case R.id.btnConsultar:
                Consultar(); //Llamo la funcion
                break;

            case R.id.btnEliminar:
                Eliminar(); //Llamo la funcion
                break;

            case R.id.btnLista:
                Intent lista = new Intent(AdministrarActivity.this, ListaActivity.class);
                startActivity(lista);
                break;

            case R.id.btnCanvas:
                Intent canvas = new Intent(AdministrarActivity.this, CanvasActivity.class);
                startActivity(canvas);
                break;
        }
    }

    //Funcion del boton Insertar
    private void Insertar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String placaDato = placa.getText().toString();
        String vehicle = vehiculo.getText().toString();
        String entrada = hora_e.getText().toString();
        String salida = hora_s.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("placa", placaDato);
        registro.put("vehiculo", vehicle);
        registro.put("hora_e", entrada);
        registro.put("hora_s", salida);

        Long insertarLLavePrimaria = bd.insert("parqueadero", "placa", registro);
        Toast.makeText(this, "Su ha Guardado "+insertarLLavePrimaria, Toast.LENGTH_SHORT).show();
        Limpiar();
        bd.close();

    }

    //Funcion del boton Consultar
    private void Consultar(){
        String placaDato = placa.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        String[] datoParaConsultar = {placaDato}; //El dato con el que voy a buscar
        String[] datosMostrar = {"vehiculo", "hora_e", "hora_s"}; //Los campos que va a mostrar

        Cursor fila = bd.query("parqueadero", datosMostrar, "placa"+"=?", datoParaConsultar, null, null, null);

        if (fila.moveToFirst()) {
            vehiculo.setText(fila.getString(0));
            hora_e.setText(fila.getString(1));
            hora_s.setText(fila.getString(2));
        } else {
            Toast.makeText(this, "No existe "+placaDato, Toast.LENGTH_SHORT).show();
            Limpiar();
            bd.close();
        }
    }

    //Funcion del boton Eliminar
    private void Eliminar(){
        String placaDato = placa.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String[] datoEliminar = {placaDato};
        bd.delete("parqueadero","placa"+"=?", datoEliminar);

        Toast.makeText(getApplicationContext(),"Se Elimino: "+ placaDato, Toast.LENGTH_SHORT).show();
        Limpiar();
        bd.close();
    }

    //Funcion para limpiar datos esta la use dentro de las funciones
    private void Limpiar() {
        placa.setText("");
        vehiculo.setText("");
        hora_e.setText("");
        hora_s.setText("");
    }

}
