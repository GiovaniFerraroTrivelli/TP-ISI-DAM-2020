package isi.dam.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.registrar:
                Intent registarIntent =  new Intent(this, RegistrarActivity.class);
                startActivity(registarIntent);
                return true;

            case R.id.alta_plato:
                Intent altaPlatoIntent =  new Intent(this, AltaPlatoActivity.class);
                startActivity(altaPlatoIntent);
                return true;

            case R.id.lista_items:
                Log.d("onOptionsItemSelected", "lista_items");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}