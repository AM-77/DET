package com.lonix.det.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"imageName","machineName"} , ignoreUnknown=true)
public class Machine extends MachineCategory{

    private String mapName;
    private List<Column> columns;

    public Machine(){
        super();
    }

    public Machine(String mapName, List<Column> columns ){
        this.mapName = mapName;
        this.columns = columns;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}