package com.example.mda;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(ContactEntity contact);

    @Insert
    void insertAll(List<ContactEntity> contacts);

    @Query("SELECT * FROM contacts ORDER BY name ASC")
    List<ContactEntity> getAllContacts();

    @Query("SELECT * FROM contacts WHERE is_favorite = 1 ORDER BY name ASC")
    List<ContactEntity> getFavoriteContacts();

    @Update
    void updateContact(ContactEntity contact);

    @Query("UPDATE contacts SET is_favorite = :isFavorite WHERE id = :contactId")
    void updateFavoriteStatus(int contactId, boolean isFavorite);

    @Query("SELECT * FROM contacts WHERE name LIKE '%' || :search || '%' OR phone LIKE '%' || :search || '%' ORDER BY name ASC")
    List<ContactEntity> searchContacts(String search);
}
