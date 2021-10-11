package com.example.a04_creaciondevistaporcodigo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a04_creaciondevistaporcodigo.databinding.ActivityEditBinding;
import com.example.a04_creaciondevistaporcodigo.databinding.ActivityMainBinding;
import com.example.a04_creaciondevistaporcodigo.modelos.Alumno;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inicializaValores();

        binding.btnBorrarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("POSICION", getIntent().getExtras().getInt("POSICION"));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        binding.btnGuardarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombreEditAlumno.getText().toString();
                String apellidos = binding.txtApellidosEditAlumno.getText().toString();
                int indiceSpinner = binding.spCiclosEditAlumno.getSelectedItemPosition();
                int idRadioButton = binding.rgGrupoEditAlumno.getCheckedRadioButtonId();

                if (!nombre.isEmpty() && !apellidos.isEmpty() && indiceSpinner != 0 && idRadioButton != -1){
                    String ciclo = (String) binding.spCiclosEditAlumno.getSelectedItem();
                    RadioButton rb = findViewById(idRadioButton);
                    char grupo = rb.getText().toString().charAt(0);
                    Alumno alumno = new Alumno(nombre,apellidos,ciclo,grupo);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    bundle.putInt("POSICION", getIntent().getExtras().getInt("POSICION"));

                    Intent intent = new Intent();
                    intent.putExtras(bundle);

                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(EditActivity.this, "ERROR!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializaValores() {
        Alumno alumno = getIntent().getExtras().getParcelable("ALUMNO");
        binding.txtNombreEditAlumno.setText(alumno.getNombre());;
        binding.txtApellidosEditAlumno.setText(alumno.getApellidos());

        switch (alumno.getCiclo()){
            case "DAM":
                binding.spCiclosEditAlumno.setSelection(1);
                break;
            case "DAW":
                binding.spCiclosEditAlumno.setSelection(2);
                break;
            case "SMR":
                binding.spCiclosEditAlumno.setSelection(3);
                break;
            case "3D":
                binding.spCiclosEditAlumno.setSelection(4);
                break;

        }

        switch (alumno.getGrupo()){
            case 'A':
                binding.rbAEditAlumno.setChecked(true);
                break;
            case 'B':
                binding.rbBEditAlumno.setChecked(true);
                break;
            case 'C':
                binding.rbCEditAlumno.setChecked(true);
                break;
        }
    }
}