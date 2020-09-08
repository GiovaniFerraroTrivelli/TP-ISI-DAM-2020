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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText password;
    EditText confirmarPassword;
    TextView alertaPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnRegister = (Button)findViewById(R.id.Registrar);
        btnRegister.setOnClickListener(this);

        password = (EditText)findViewById(R.id.Password);
        confirmarPassword = (EditText)findViewById(R.id.PasswordRepeat);

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
                    if(!password.getText().toString().equals(confirmarPassword.getText().toString())) {
                        alertaPasword.setVisibility(View.VISIBLE);
                    }
                    else {
                        alertaPasword.setVisibility(View.INVISIBLE);
                    }

                }
            }
        });
    }


    public void onClick(View v) {

        // Validar correo elecrónico
        String email = ((EditText) findViewById(R.id.Email)).getText().toString();
        if(!this.validarCorreoElectronico(email)){
            Toast.makeText(this, "La dirección de correo ingresada no es válida", Toast.LENGTH_SHORT).show();
        }


        // Claves
        /*
        if(!password.getText().toString().equals(confirmarPassword.getText().toString())) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
         */


        // Numero de tarjeta y ccv
        //String numeroTarjeta = ((EditText) findViewById(R.id.CardNumber)).getText().toString();
        //String cardCCV = ((EditText) findViewById(R.id.CardCCV)).getText().toString();

        // Fecha Vencimiento
        //String anioVencimiento = ((EditText) findViewById(R.id.AnioVencimiento)).getText().toString();
        //String mesVencimiento = ((EditText) findViewById(R.id.MesVencimiento)).getText().toString();

        // Tipo tarjeta
        //int idTipoTarjeta = ((RadioGroup) findViewById(R.id.CardType)).getCheckedRadioButtonId();
        //String tipoTarjeta = ((RadioButton) findViewById(idTipoTarjeta)).getText().toString();

        // Slider de carga inicial
        //Switch switchCargaInicial = (Switch) findViewById(R.id.RealizarCargaInicial);

        /*
        if(switchCargaInicial.isChecked()){
            // TODO: La seekbar debería mostrar un valor
            int creditoInicial = ((SeekBar) findViewById(R.id.CreditoInicial)).getProgress();
            validar = (creditoInicial > 0);
        }
         */


    }

    /**
     * Verifica si el correo electrónico cumple con la expresión regular dada.
     * @param correo: dirección de correo eletrónico
     * @return true (si la dirección de correo es válida)
     */
    private boolean validarCorreoElectronico(String correo) {
        /*
        int indexArroba = correo.indexOf("@");
        int countLetras = 0;
        if(indexArroba >= 0){
            int i = indexArroba;
            while(countLetras < 3 && i < correo.length()) {
                if(Character.isLetter(correo.charAt(i))) {
                    countLetras++;
                }
            }
        }
        return indexArroba >=0 && countLetras >= 3;
         */

        Pattern EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = EMAIL_VALIDO.matcher(correo);
        return matcher.find();
    }
}