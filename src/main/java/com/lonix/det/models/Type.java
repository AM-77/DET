package com.lonix.det.models;

public class Type {

    private String machineName; 
    private String mapName; 
    private String imageName;

    public Type(){
        super();
    }

    public Type(String machineName, String mapName, String imageName) {
        this.machineName = machineName;
        this.mapName = mapName;
        this.imageName = imageName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    } 

}