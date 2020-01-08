package com.lonix.det.models;

import java.util.List;
import com.lonix.det.models.Column;

public class Machine {

    private String machineName;
    private List<Column> columns;

    public Machine(){
        super();
    }

    public Machine(String machineName, List<Column> columns ){
        this.machineName = machineName;
        this.columns = columns;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}