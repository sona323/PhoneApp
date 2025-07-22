package com.example.mda;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class ContactEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "is_favorite")
    public boolean isFavorite;

    // ✅ Default constructor required by Room
    public ContactEntity() {}

    // ✅ Custom constructor for convenience
    public ContactEntity(String name, String phone, boolean isFavorite) {
        this.name = name;
        this.phone = phone;
        this.isFavorite = isFavorite;
    }
}
