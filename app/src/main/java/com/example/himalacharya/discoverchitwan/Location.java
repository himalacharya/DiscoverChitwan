package com.example.himalacharya.discoverchitwan;

/**
 * Created by Himal Acharya on 2016-12-22.
 */

public class Location {
    public String name;
    public String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Location(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
