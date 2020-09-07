package isi.dam.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtPassword1;
    EditText txtPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnRegister = (Button)findViewById(R.id.Registrar);
        btnRegister.setOnClickListener(this);

        txtPassword1 = (EditText)findViewById(R.id.Password);
        txtPassword2 = (EditText)findViewById(R.id.PasswordRepeat);

        final EditText txtCCV = (EditText)findViewById(R.id.CardCCV);

        EditText txtCardNumber = (EditText)findViewById(R.id.CardNumber);
        txtCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                txtCCV.setEnabled(editable.toString().length() > 0);
            }
        });

        final TextView txtCreditoInicial = (TextView)findViewById(R.id.TextCreditoInicial);
        final SeekBar skCreditoInicial = (SeekBar)findViewById(R.id.CreditoInicial);

        final Switch swtCargaFinal = (Switch)findViewById(R.id.RealizarCargaInicial);
        swtCargaFinal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    txtCreditoInicial.setVisibility(View.VISIBLE);
                    skCreditoInicial.setVisibility(View.VISIBLE);
                } else {
                    txtCreditoInicial.setVisibility(View.GONE);
                    skCreditoInicial.setVisibility(View.GONE);
                }
            }
        });

        CheckBox chkAceptarTerminos = (CheckBox)findViewById(R.id.AceptarTerminos);
        chkAceptarTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setEnabled(((CheckBox) view).isChecked());
            }
        });
    }

    public void onClick(View v) {
        // Validar contraseñas
        if(!txtPassword1.getText().toString().equals(txtPassword2.getText().toString())) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}