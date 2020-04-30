package com.lonex.det.models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"machineName"} , ignoreUnknown=true)
public class Machine extends MachineCategory{

	/* private String machineName; 
    private String mapName; 
    private String imageName; */
    private String mapName;
    private List<Column> columns = new CopyOnWriteArrayList<Column>();

    public Machine(){ super(); }
    public Machine(String mapName, List<Column> columns ){
        this.mapName = mapName;
        this.columns = columns;
    }

    public String getMapName() { return mapName; }
    public void setMapName(String mapName) { this.mapName = mapName; }

    public List<Column> getColumns() { return columns; }
    public void setColumns(List<Column> columns) { this.columns = columns; }
    
    public void addColumn(Column c) { this.columns.add(c); }

}