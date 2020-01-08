package com.lonix.det.models;

public class Type {

    private String machine_name; 
    private String map_name; 
    private String image_name;

    public Type(String machine_name, String map_name, String image_name) {
        this.machine_name = machine_name;
        this.map_name = map_name;
        this.image_name = image_name;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    } 

}