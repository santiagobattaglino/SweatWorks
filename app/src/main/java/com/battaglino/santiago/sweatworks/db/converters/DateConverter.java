package com.battaglino.santiago.sweatworks.db.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Santiago Battaglino.
 */
public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}