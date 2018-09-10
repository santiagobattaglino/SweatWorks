package com.battaglino.santiago.sweatworks.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import com.battaglino.santiago.sweatworks.db.entities.Picture;

/**
 * Created by Santiago Battaglino.
 */
public class PictureConverter {

    @TypeConverter
    public static String fromPicture(Picture picture) {
        return picture == null ? null : picture.thumbnail;
    }

    @TypeConverter
    public static Picture toPicture(String thumbnail) {
        if (TextUtils.isEmpty(thumbnail)) {
            return null;
        }

        Picture picture = new Picture();
        picture.thumbnail = thumbnail;

        return picture;
    }
}
