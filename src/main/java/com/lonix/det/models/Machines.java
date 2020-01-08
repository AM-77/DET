package com.lonix.det.models;

import java.util.List;
import com.lonix.det.models.Machine;;

public class Machines {

    private List<Machine> machines;

    public Machines(){
        super();
    }

    public Machines(List<Machine> machines ){
        this.machines = machines;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
    
}