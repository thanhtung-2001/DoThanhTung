package com.example.thanhtng_dawd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "des")
    public String des;

    @ColumnInfo(name = "salary")
    public String salary;

    public Employee() {
    }
}
