package com.example.cristianherrera.parkingsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AdminSQLiteOpenHelper admin;
    ArrayList<String> listaInformacion;
    ArrayList<Campos> listaUsuarios;
    ListView listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        listaPersonas = findViewById(R.id.listViewPersonas);

        ConsultarListaPersonas();

        ArrayAdapter adaptador =  new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaInformacion);
        listaPersonas.setAdapter(adaptador);
        listaPersonas.setOnItemClickListener(this);
    }

    private void ConsultarListaPersonas() {
        SQLiteDatabase db = admin.getReadableDatabase();
        Campos usuarios=null;

        listaUsuarios = new ArrayList<Campos>();

        Cursor cursos = db.rawQuery("SELECT * FROM parqueadero", null);
        while (cursos.moveToNext()){
            usuarios=new Campos();
            usuarios.setPlaca(cursos.getInt(0));
            usuarios.setVehiculo(cursos.getString(1));
            usuarios.setHoraEntrada(cursos.getString(2));
            usuarios.setHoraSalida(cursos.getString(3));

            listaUsuarios.add(usuarios);
            MostrarLista();
        }
    }

    private void MostrarLista() {
        listaInformacion = new ArrayList<String>();

        for(int i=0;i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getPlaca()+" - "+listaUsuarios.get(i).getVehiculo());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String informacion = "Placa: "+listaUsuarios.get(position).getPlaca()+"\n";
        informacion+="Vehiculo: "+listaUsuarios.get(position).getVehiculo()+"\n";
        informacion+="Hora de Entrada: "+listaUsuarios.get(position).getHoraEntrada()+"\n";
        informacion+="Hora de Salida: "+listaUsuarios.get(position).getHoraSalida()+"\n";

        Toast.makeText(this, ""+informacion, Toast.LENGTH_LONG).show();
    }

}
