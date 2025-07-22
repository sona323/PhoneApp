package com.example.mda;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    List<ContactEntity> getAllContacts();

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void insertContact(ContactEntity contact);

    @Update
    void updateContact(ContactEntity contact);

    @Delete
    void deleteContact(ContactEntity contact);
}
