/**
 * Introduction to Spring JDBC support
 * 
 * https://github.com/egalli64/swd/
 */
package com.example.swd.m1.entity;

/**
 * Entity for Spring JDBC API
 */
public class Region {
    private Integer id;
    private String name;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }
}
