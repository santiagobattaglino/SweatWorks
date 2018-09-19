package com.battaglino.santiago.sweatworks.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

/**
 * Created by Santiago Battaglino.
 */
@Parcel
@Entity(tableName = "users", indices = @Index(value = {"uid"}))
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("uid")
    @Expose
    public int uid;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("name")
    @Expose
    public Name name;

    //@SerializedName("location")
    //public Location location;

    @SerializedName("email")
    @Expose
    public String email;

    //@SerializedName("login")
    //@Expose
    //public Login login;

    //@SerializedName("dob")
    //@Expose
    //public Dob dob;

    //@SerializedName("registered")
    //@Expose
    //public Registered registered;

    @SerializedName("phone")
    @Expose
    public String phone;

    @SerializedName("cell")
    @Expose
    public String cell;

    //@SerializedName("id")
    //@Expose
    //public Id id;

    @SerializedName("picture")
    @Expose
    public Picture picture;

    @SerializedName("nat")
    @Expose
    public String nat;

    @SerializedName("isFavorite")
    @Expose
    public boolean isFavorite;

    @ParcelConstructor
    public User() {

    }

    public static List<String> getDataSource(List<User> users) {
        final List<String> items = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                items.add(user.getFullName());
            }
        }
        return items;
    }

    public String getFullName() {
        return String.format(Locale.getDefault(), "%s %s %s", name.title, name.first, name.last);
    }
}
