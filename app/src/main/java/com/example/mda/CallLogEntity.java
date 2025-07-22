package com.example.mda;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "call_logs")
public class CallLogEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "call_type")  // incoming, outgoing, missed
    public String callType;

    @ColumnInfo(name = "call_time")
    public String callTime;

    // ✅ Default constructor (required by Room)
    public CallLogEntity() {}

    // ✅ Custom constructor for inserting data
    public CallLogEntity(String name, String phone, String callType, String callTime) {
        this.name = name;
        this.phone = phone;
        this.callType = callType;
        this.callTime = callTime;
    }
}
