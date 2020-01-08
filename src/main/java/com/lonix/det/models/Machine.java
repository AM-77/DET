package com.lonix.det.models;

import java.util.List;
import com.lonix.det.models.Column;

public class Machine {

    private String machine_name;
    private List<Column> columns;

    public Machine(String machine_name, List<Column> columns ){
        this.machine_name = machine_name;
        this.columns = columns;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}