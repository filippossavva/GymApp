package com.example.gymapp;

public class Gyms {

    String Image,Title;
    Long boxing,classes,personal,tabata,weights,yoga;

    public Long getBoxing() {
        return boxing;
    }

    public void setBoxing(Long boxing) {
        this.boxing = boxing;
    }

    public Long getYoga() {
        return yoga;
    }

    public void setYoga(Long yoga) {
        this.yoga = yoga;
    }

    public Long getTabata() {
        return tabata;
    }

    public void setTabata(Long tabata) {
        this.tabata = tabata;
    }

    public Long getClasses() {
        return classes;
    }

    public void setClasses(Long classes) {
        this.classes = classes;
    }

    public Long getWeights() {
        return weights;
    }

    public void setWeights(Long weights) {
        this.weights = weights;
    }

    public Long getPersonal() {
        return personal;
    }

    public void setPersonal(Long personal) {
        this.personal = personal;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
