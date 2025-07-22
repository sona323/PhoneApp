package com.example.mda;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface CallLogDao {

    @Query("SELECT * FROM call_logs ORDER BY call_time DESC")
    List<CallLogEntity> getAllCallLogs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCallLog(CallLogEntity callLog);
}
