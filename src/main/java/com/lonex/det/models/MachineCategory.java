package com.lonex.det.models;

public class MachineCategory {

    private String machineName; 
    private String mapName; 
    private String imageName;

    public MachineCategory(){ super(); }
    public MachineCategory(String machineName, String mapName, String imageName) {
        this.machineName = machineName;
        this.mapName = mapName;
        this.imageName = imageName;
    }

    public String getMachineName() { return machineName; }
    public void setMachineName(String machineName) { this.machineName = machineName; }

    public String getMapName() { return mapName; }
    public void setMapName(String mapName) { this.mapName = mapName; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; } 

}