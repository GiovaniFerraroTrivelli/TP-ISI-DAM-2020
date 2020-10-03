package isi.dam.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class PedidoActivity extends AppCompatActivity {

    private Spinner spinnerCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        // Solo admite una ciudad
        spinnerCiudades = (Spinner) findViewById(R.id.Spinner_ciudades);
        spinnerCiudades.setEnabled(false);
    }
}