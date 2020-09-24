package isi.dam.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AltaPlatoActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);

        buttonGuardar = (Button) findViewById(R.id.guardar_plato);


    }

    @Override
    public void onClick(View view) {

    }
}