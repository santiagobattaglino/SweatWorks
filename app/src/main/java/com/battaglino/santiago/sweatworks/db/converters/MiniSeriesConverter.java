package com.battaglino.santiago.sweatworks.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import com.battaglino.santiago.sweatworks.db.entities.MiniSeries;

import java.util.Locale;

/**
 * Created by Santiago Battaglino.
 */
public class MiniSeriesConverter {

    @TypeConverter
    public static String fromMiniSeries(MiniSeries miniSerie) {
        return miniSerie == null ? null : String.format(Locale.getDefault(), "%d %s %d %d",
                miniSerie.losses, miniSerie.progress, miniSerie.target, miniSerie.wins);
    }

    @TypeConverter
    public static MiniSeries toMiniSeries(String miniserie) {
        if (TextUtils.isEmpty(miniserie)) {
            return null;
        }

        String[] parts = miniserie.split(" ");
        MiniSeries miniSeries = new MiniSeries();
        miniSeries.losses = Integer.parseInt(parts[0]);
        miniSeries.progress = parts[1];
        miniSeries.target = Integer.parseInt(parts[2]);
        miniSeries.wins = Integer.parseInt(parts[3]);

        return miniSeries;
    }
}
