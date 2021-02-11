package isi.dam.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import isi.dam.sendmeal.model.Plato;
import isi.dam.sendmeal.repositories.PlatoRepository;

public class AltaPlatoActivity extends AppCompatActivity implements PlatoRepository.OnResultCallback, View.OnClickListener {
    Button buttonGuardar;
    EditText textTituloPlato;
    EditText textDescripcionPlato;
    EditText textPrecioPlato;
    EditText textCaloriasPlato;
    PlatoRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);

        repository = new PlatoRepository(this.getApplication(), this);

        buttonGuardar = (Button) findViewById(R.id.guardar_plato);
        buttonGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        textTituloPlato = (EditText) findViewById(R.id.TituloPlato);
        textDescripcionPlato = (EditText) findViewById(R.id.DescripcionPlato);
        textPrecioPlato = (EditText) findViewById(R.id.PrecioPlato);
        textCaloriasPlato = (EditText) findViewById(R.id.CaloriasPlato);

        String titulo = String.valueOf(textTituloPlato.getText());
        String descripcion = String.valueOf(textDescripcionPlato.getText());
        String precio = String.valueOf(textPrecioPlato.getText());
        String calorias = String.valueOf(textCaloriasPlato.getText());

        if(!InputValidator.validarAltaPlato(new String[]{titulo, descripcion, precio, calorias})) {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }

        else {
            Plato plato = new Plato();
            plato.setTitulo(titulo);
            plato.setDescripcion(descripcion);
            plato.setPrecio(Double.parseDouble(precio));
            plato.setCalorias(Integer.parseInt(calorias));

            Log.d("plato", plato.toString());
            repository.insertar(plato);
        }
    }

    @Override
    public void onResult(List result) {
    }

    @Override
    public void onInsert() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AltaPlatoActivity.this, "¡Plato agregado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}