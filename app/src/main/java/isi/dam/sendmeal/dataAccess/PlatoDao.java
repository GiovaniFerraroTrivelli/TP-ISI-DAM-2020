package isi.dam.sendmeal.dataAccess;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

@Dao
public interface PlatoDao {
    static public List<Plato> listaPlatos = new ArrayList<Plato>();

    /*static public Boolean addToListaPlatos(Plato plato) {
        return listaPlatos.add(plato);
    }

    static public List<Plato> getListaPlatos() {
        return listaPlatos;
    }*/

    @Insert
    void insertar(Plato plato);

    @Delete
    void borrar(Plato plato);

    @Update
    void actualizar(Plato plato);

    @Query("SELECT * FROM plato WHERE id = :id LIMIT 1")
    Plato buscar(String id);

    @Query("SELECT * FROM plato")
    public List<Plato> buscarTodos();
}
