package com.example.cristianherrera.parkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usuario;
        final EditText contraseña;
        Button entrar;

        usuario = findViewById(R.id.editUser);
        contraseña = findViewById(R.id.editPass);
        entrar = findViewById(R.id.btnIrLogin);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usu = usuario.getText().toString();
                String contra = contraseña.getText().toString();

                if (usu.equals("cristian") && contra.equals("1234")){
                    Intent i = new Intent(MainActivity.this, AdministrarActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
