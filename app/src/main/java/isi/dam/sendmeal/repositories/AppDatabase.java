package isi.dam.sendmeal.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import isi.dam.sendmeal.Converters;
import isi.dam.sendmeal.dataAccess.PedidoDao;
import isi.dam.sendmeal.dataAccess.PlatoDao;
import isi.dam.sendmeal.model.Pedido;
import isi.dam.sendmeal.model.Plato;

@Database(entities = {Plato.class, Pedido.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE = null;

    public abstract PlatoDao platoDao();
    public abstract PedidoDao pedidoDao();

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(5);

    static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app-database")
                    .build();
        }
        return INSTANCE;
    }
}