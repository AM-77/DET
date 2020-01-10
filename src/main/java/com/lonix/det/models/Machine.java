package com.lonix.det.models;

import java.util.HashMap;
import java.util.List;

public class Machine {

    private String machineName;
    private HashMap<String,List<Feature>> columns;

    public Machine(){
        super();
    }

    public Machine(String machineName, HashMap<String,List<Feature>> columns ){
        this.machineName = machineName;
        this.columns = columns;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public HashMap<String , List<Feature>> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
    	for(Column col : columns)
    	{
    		this.columns.put( col.getTitle() , col.getFeatures() );
    	}
       
    }

}