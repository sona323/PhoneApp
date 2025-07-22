package com.example.mda;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {ContactEntity.class, CallLogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();
    public abstract CallLogDao callLogDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_db")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        AppDatabase database = getInstance(context);

                                        // Insert 30 dummy Contacts (Every 5th marked favorite)
                                        for (int i = 1; i <= 30; i++) {
                                            database.contactDao().insertContact(new ContactEntity(
                                                    "Contact " + i,
                                                    "987654321" + i,
                                                    i % 5 == 0 // Every 5th contact is favorite
                                            ));
                                        }

                                        // Insert 30 dummy Call Logs
                                        for (int i = 1; i <= 30; i++) {
                                            database.callLogDao().insertCallLog(new CallLogEntity(
                                                    "Contact " + i,               // name
                                                    "987654321" + i,              // phone
                                                    (i % 3 == 0) ? "MISSED" : (i % 2 == 0) ? "OUTGOING" : "INCOMING", // callType
                                                    "10:0" + i + " AM"            // callTime
                                            ));
                                        }
                                    });
                                }
                            })
                            .allowMainThreadQueries()  // For demo only!
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
