package com.lonix.det.models;

import java.util.List;
import com.lonix.det.models.Feature;

public class Column {

    private String title;
    private List<Feature> features;

    public Column(){
        super();
    }

    public Column(String title, List<Feature> features ){
        this.title = title;
        this.features = features;
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