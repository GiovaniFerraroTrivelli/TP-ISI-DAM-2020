package isi.dam.sendmeal.dataAccess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PlatoDao {
    static public List<Plato> pruebaListaPlatos() {
        List<Plato> listaPlatos = new ArrayList<Plato>();

        Plato p1 = new Plato();
        p1.setTitulo("Empanada");
        p1.setDescripcion("Una empanada de pollo");
        p1.setPrecio(30);
        p1.setCalorias(200);
        listaPlatos.add(p1);

        Plato p2 = new Plato();
        p2.setTitulo("Milanesa");
        p2.setDescripcion("Una milanesa de pescado");
        p2.setPrecio(90);
        p2.setCalorias(150);
        listaPlatos.add(p2);

        Plato p3 = new Plato();
        p3.setTitulo("Ensalada");
        p3.setDescripcion("Ensalada de tomate y lechuga");
        p3.setPrecio(89);
        p3.setCalorias(30);
        listaPlatos.add(p3);

        Plato p4 = new Plato();
        p4.setTitulo("Fideos");
        p4.setDescripcion("Fideos tirabuzón");
        p4.setPrecio(90);
        p4.setCalorias(150);
        listaPlatos.add(p4);

        return listaPlatos;
    }
}
