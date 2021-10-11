package com.example.a04_creaciondevistaporcodigo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a04_creaciondevistaporcodigo.R;
import com.example.a04_creaciondevistaporcodigo.databinding.ActivityAddAlumnoBinding;
import com.example.a04_creaciondevistaporcodigo.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnGuardarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombreAddAlumno.getText().toString();
                String apellidos = binding.txtApellidosAddAlumno.getText().toString();
                int indiceSpinner = binding.spCiclosAddAlumno.getSelectedItemPosition();
                int idRadioButton = binding.rgGrupoAddAlumno.getCheckedRadioButtonId();

                if (!nombre.isEmpty() && !apellidos.isEmpty() && indiceSpinner != 0 && idRadioButton != -1){
                    String ciclo = (String) binding.spCiclosAddAlumno.getSelectedItem();
                    RadioButton rb = findViewById(idRadioButton);
                    char grupo = rb.getText().toString().charAt(0);
                    Alumno alumno = new Alumno(nombre,apellidos,ciclo,grupo);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(AddAlumnoActivity.this, "ERROR!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}