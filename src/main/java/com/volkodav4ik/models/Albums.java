package com.volkodav4ik.models;

public class Albums {

    private int usedId;
    private int id;
    private String title;

    public Albums() {
    }

    @Override
    public String toString() {
        return "Albums{" +
                "usedId=" + usedId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
