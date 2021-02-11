package isi.dam.sendmeal.dataAccess;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import isi.dam.sendmeal.model.Pedido;

@Dao
public interface PedidoDao {
    @Insert
    void insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedido WHERE id = :id LIMIT 1")
    Pedido buscar(String id);

    @Query("SELECT * FROM pedido")
    public List<Pedido> buscarTodos();
}
