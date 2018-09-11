package com.battaglino.santiago.sweatworks.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import com.battaglino.santiago.sweatworks.db.entities.Picture;

import java.util.Locale;

/**
 * Created by Santiago Battaglino.
 */
public class PictureConverter {

    @TypeConverter
    public static String fromPicture(Picture picture) {
        return picture == null ? null : String.format(Locale.getDefault(), "%s %s %s", picture.large, picture.medium, picture.thumbnail);
    }

    @TypeConverter
    public static Picture toPicture(String thumbnail) {
        if (TextUtils.isEmpty(thumbnail)) {
            return null;
        }

        String[] parts = thumbnail.split(" ");
        Picture picture = new Picture();
        picture.large = parts[0];
        picture.medium = parts[1];
        picture.thumbnail = parts[2];

        return picture;
    }
}
