package isi.dam.sendmeal;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    /**
     * Verifica si el correo electrónico cumple con la expresión regular dada.
     * @param correo dirección de correo eletrónico
     * @return true (si la dirección de correo es válida)
     */
    public static boolean validarCorreoElectronico(String correo) {
        Pattern EMAIL_VALIDO = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = EMAIL_VALIDO.matcher(correo);
        return matcher.find();
    }

    /**
     * Verifica si el vencimiento de una tarjeta de crédito es superior a 3 los próximos tres meses
     * @param mes  mes de vencimiento de la tarjeta de crédito
     * @param anio año de vencimiento de la tarjeta de crédito
     * @return true (si la dirección de correo es válida)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean validarVencimientoTarjeta(Integer mes, Integer anio){
        boolean fechaValida = false;
        LocalDate fechaActual = LocalDate.now();
        //.withDayOfMonth(1)) si las valdaciones no son correctas

        if(mes >= 1 && mes <= 12 && anio >= fechaActual.getYear()) {
            LocalDate fechaVencimiento = LocalDate.of(2000+anio, mes, 1);
            fechaValida = (ChronoUnit.MONTHS.between(fechaActual, fechaVencimiento) >= 3);
        }

        return fechaValida;
    }
}
