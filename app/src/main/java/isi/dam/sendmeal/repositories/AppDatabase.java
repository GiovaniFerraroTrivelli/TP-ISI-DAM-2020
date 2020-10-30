package isi.dam.sendmeal.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import isi.dam.sendmeal.dataAccess.PlatoDao;
import isi.dam.sendmeal.model.Plato;

@Database(entities = {Plato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE = null;
    public abstract PlatoDao platoDao();

    static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app-database")
                    .build();
        }
        return INSTANCE;
    }
}