package com.battaglino.santiago.sweatworks.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.battaglino.santiago.sweatworks.BuildConfig;
import com.battaglino.santiago.sweatworks.db.converters.DateConverter;
import com.battaglino.santiago.sweatworks.db.converters.MiniSeriesConverter;
import com.battaglino.santiago.sweatworks.db.converters.NameConverter;
import com.battaglino.santiago.sweatworks.db.converters.PictureConverter;
import com.battaglino.santiago.sweatworks.db.dao.LigaDao;
import com.battaglino.santiago.sweatworks.db.dao.UserDao;
import com.battaglino.santiago.sweatworks.db.entities.Liga;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.global.Constants;

/**
 * Created by Santiago Battaglino.
 * This class is used to create the database and get an instance of it.
 */
@Database(entities = {
        Liga.class
}, version = 1)
@TypeConverters({MiniSeriesConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    /*
     *  Data Access Objects (DAO) to manipulate our db table.
     *  We have to create an abstract method for every DAO class that we create.
     *  inMemoryDatabaseBuilder or databaseBuilder
     */
    public static AppDatabase getDatabaseBuilder(Context context) {
        if (INSTANCE == null) {
            if (BuildConfig.DEBUG) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DB_NAME)
                        .allowMainThreadQueries()
                        .build();
            } else {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DB_NAME)
                        .build();
            }
        }
        return INSTANCE;
    }

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            if (BuildConfig.DEBUG) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                        .allowMainThreadQueries()
                        .build();
            } else {
                INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                        .build();
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract LigaDao ligaModel();

    // If you need to update your database version, and add entities or new columns,
    // you gonna have to implement a Migration operation in order to avoid crashes or users losing data

    /*public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE product "
                    + " ADD COLUMN street TEXT, number TEXT, neighborhood TEXT");
        }
    };*/
}
