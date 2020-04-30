package com.lonex.det.models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Column {

    private String title = "";
    private List<Feature> features = new CopyOnWriteArrayList<Feature>();

    public Column(){
        super();
    }

    public Column(String title, List<Feature> features ){
        this.title = title;
        this.features = features;
    }
    
    public void addFeature(Feature f) {
    	this.features.add(f);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}