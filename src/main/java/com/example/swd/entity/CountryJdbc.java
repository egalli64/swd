package com.example.swd.entity;

public class CountryJdbc {
    private String id;
    private String name;
    private Region region;

    public CountryJdbc() {
    }

    public CountryJdbc(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "CountryJdbc [id=" + id + ", name=" + name + ", region=" + region + "]";
    }
}
