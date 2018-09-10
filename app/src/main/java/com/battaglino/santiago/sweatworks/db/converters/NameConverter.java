package com.battaglino.santiago.sweatworks.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import com.battaglino.santiago.sweatworks.db.entities.Name;

import java.util.Locale;

/**
 * Created by Santiago Battaglino.
 */
public class NameConverter {

    @TypeConverter
    public static String fromName(Name name) {
        return name == null ? null : String.format(Locale.getDefault(), "%s %s %s", name.first, name.last, name.title);
    }

    @TypeConverter
    public static Name toName(String fullName) {
        if (TextUtils.isEmpty(fullName)) {
            return null;
        }

        String[] parts = fullName.split(" ");
        Name name = new Name();
        name.first = parts[0];
        name.last = parts[1];
        name.title = parts[2];

        return name;
    }
}
