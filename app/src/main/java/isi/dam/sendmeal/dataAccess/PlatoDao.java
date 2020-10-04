package isi.dam.sendmeal.dataAccess;

import java.util.ArrayList;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PlatoDao {
    static public List<Plato> listaPlatos = new ArrayList<Plato>();

    static public Boolean addToListaPlatos(Plato plato) {
        return listaPlatos.add(plato);
    }

    static public List<Plato> getListaPlatos() {
        return listaPlatos;
    }
}
