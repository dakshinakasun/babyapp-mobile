package com.example.babyapp.Pages;

public class WeeklyPosts {

    private String title,SiTitle, highlight, SiHighlight, description, SiDescription, image;

    public WeeklyPosts(String title, String SiTitle, String highlight, String SiHighlight, String description, String SiDescription, String image) {
        this.title = title;
        this.SiTitle = SiTitle;
        this.highlight = highlight;
        this.SiHighlight = SiHighlight;
        this.description = description;
        this.SiDescription = SiDescription;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiTitle() {
        return SiTitle;
    }

    public void setSiTitle(String siTitle) {
        SiTitle = siTitle;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getSiHighlight() {
        return SiHighlight;
    }

    public void setSiHighlight(String siHighlight) {
        SiHighlight = siHighlight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiDescription() {
        return SiDescription;
    }

    public void setSiDescription(String siDescription) {
        SiDescription = siDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
