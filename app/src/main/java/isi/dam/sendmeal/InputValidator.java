package isi.dam.sendmeal;

import android.os.Build;
import android.util.Log;



import java.util.Calendar;
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
     * Verifica si el vencimiento de una tarjeta de crédito es superior a 3 los próximos tres meses.
     * Se asume que los parámetros de entrada fueron validados.
     * @param mes  mes de vencimiento de la tarjeta de crédito
     * @param anio año de vencimiento de la tarjeta de crédito
     * @return true (si la dirección de correo es válida)
     */

    public static boolean fechaVencimientoValida(Integer mes, Integer anio){
        // Genera un Calendar con el mes y año dados
        Calendar fechaVencimiento = Calendar.getInstance();
        fechaVencimiento.set(1999+anio, mes, 1);

        // Calcula la fecha actual y le suma 3 meses.
        Calendar fechaLimite = Calendar.getInstance();
        fechaLimite.add(Calendar.MONTH, 3);

        return fechaVencimiento.compareTo(fechaLimite) >= 0;
    }
}
