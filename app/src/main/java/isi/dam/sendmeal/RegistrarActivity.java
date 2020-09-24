package isi.dam.sendmeal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText password;
    EditText confirmarPassword;
    TextView alertaPasword;
    Spinner spinnerMesVencimiento, spinnerAnioVencimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnRegister = (Button) findViewById(R.id.Registrar);
        btnRegister.setOnClickListener(this);

        password = (EditText) findViewById(R.id.Password);
        confirmarPassword = (EditText) findViewById(R.id.PasswordRepeat);

        final EditText txtCCV = (EditText) findViewById(R.id.CardCCV);

        EditText txtCardNumber = (EditText) findViewById(R.id.CardNumber);
        txtCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtCCV.setEnabled(editable.toString().length() > 0);
            }
        });

        final TextView txtCreditoInicial = (TextView) findViewById(R.id.TextCreditoInicial);
        final SeekBar skCreditoInicial = (SeekBar) findViewById(R.id.CreditoInicial);

        final Switch swtCargaFinal = (Switch) findViewById(R.id.RealizarCargaInicial);
        swtCargaFinal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    txtCreditoInicial.setVisibility(View.VISIBLE);
                    skCreditoInicial.setVisibility(View.VISIBLE);
                } else {
                    txtCreditoInicial.setVisibility(View.GONE);
                    skCreditoInicial.setVisibility(View.GONE);
                }
            }
        });

        CheckBox chkAceptarTerminos = (CheckBox) findViewById(R.id.AceptarTerminos);
        chkAceptarTerminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setEnabled(((CheckBox) view).isChecked());
            }
        });

        /**
         * Para validar las contraseñas:
         * Se ejecuta cuando se cambia el focus del EditText de confirmar contraseña.
         * Si las contraseñas no coninciden, hace visible un TextView que muestra un
         * mensaje de error. Si se corrige, el TextView desaparece.
         */
        confirmarPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    alertaPasword = (TextView) findViewById(R.id.alertaPassword);
                    if (!password.getText().toString().equals(confirmarPassword.getText().toString())) {
                        alertaPasword.setVisibility(View.VISIBLE);
                    } else {
                        alertaPasword.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    public void onClick(View v) {

        // Validar correo elecrónico
        String email = ((EditText) findViewById(R.id.Email)).getText().toString();
        if (!InputValidator.validarCorreoElectronico(email) && !TextUtils.isEmpty(email)) {
            Toast.makeText(this, "La dirección de correo ingresada no es válida", Toast.LENGTH_SHORT).show();
        }

        // Fecha Vencimiento
        spinnerMesVencimiento = (Spinner) findViewById(R.id.MesVencimiento);
        spinnerAnioVencimiento = (Spinner) findViewById(R.id.AnioVencimiento);

        Integer mesVencimiento = Integer.parseInt(spinnerMesVencimiento.getSelectedItem().toString());
        Integer anioVencimiento = Integer.parseInt(spinnerAnioVencimiento.getSelectedItem().toString());

        // Tipo tarjeta
        RadioGroup radioTipoTarjeta = (RadioGroup) findViewById(R.id.CardType);
        int idTipoTarjeta = radioTipoTarjeta.getCheckedRadioButtonId();

        String tipoTarjeta = "";

        if ((RadioButton) findViewById(idTipoTarjeta) != null) {
            tipoTarjeta = ((RadioButton) findViewById(idTipoTarjeta)).getText().toString();
        }

        if ("Crédito".equals(tipoTarjeta)) {
            if (!InputValidator.fechaVencimientoValida(mesVencimiento, anioVencimiento)) {
                Toast.makeText(this, "La tarjeta de crédito debe tener un vencimiento posterior a 3 (tres) meses.", Toast.LENGTH_SHORT).show();
            }
        }

        // Slider de carga inicial
        Switch switchCargaInicial = (Switch) findViewById(R.id.RealizarCargaInicial);

        if(switchCargaInicial.isChecked()){
            // TODO: La seekbar debería mostrar un valor
            int creditoInicial = ((SeekBar) findViewById(R.id.CreditoInicial)).getProgress();
            if(creditoInicial == 0) {
                Toast.makeText(this, "Seleccioná un monto inicial", Toast.LENGTH_SHORT).show();
            }
        }
    }

}