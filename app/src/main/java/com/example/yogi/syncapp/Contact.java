package com.example.yogi.syncapp;

/**
 * Created by YOGI on 10-11-2017.
 */

public class Contact {
    private String Name;
    private int Sync_Status;

    Contact(String name,int sync_Status){
        this.setName(name);
        this.setSync_Status(sync_Status);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSync_Status() {
        return Sync_Status;
    }

    public void setSync_Status(int sync_Status) {
        Sync_Status = sync_Status;
    }
}
