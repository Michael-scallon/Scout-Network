package com.scout.scout.data.payloads.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class PlayerRequest {


        @NotBlank
        @NotNull
        private String Name;
        @NotBlank
        @NotNull
        private Integer Age;
        @NotBlank
        @NotNull
        private String Position;
        @NotBlank
        @NotNull
        private String Region;
        @NotBlank
        @NotNull
        private Integer Minutes;
        @NotBlank
        @NotNull
        private String Nationality;
        @NotBlank
        @NotNull
        private Integer Appearances;
        @NotBlank
        @NotNull
        private Integer Goals;
        @NotBlank
        @NotNull
        private Integer Assists;
        @NotBlank
        @NotNull
        private Integer Conceded;
        @NotBlank
        @NotNull
        private Float Assist_per;
        @NotBlank
        @NotNull
        private Float Goals_per;
        @NotBlank
        @NotNull
        private Float Conceded_per;

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getAge() {
        return Age;
    }
    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public String getPosition() {
        return Position;
    }
    public void setPosition(String Position) {
        this.Position = Position;
    }
    public String getRegion() {
        return Region;
    }
    public void setRegion(String Region) {
        this.Region = Region;
    }

    public Integer getMinutes() {
        return Minutes;
    }
    public void setMinutes(Integer Minutes) {
        this.Minutes = Minutes;
    }

    public String getNationality() {
        return Nationality;
    }
    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public Integer getAppearances() {
        return Appearances;
    }
    public void setAppearances(Integer Appearances) {
        this.Appearances = Appearances;
    }

    public Integer getGoals() {
        return Goals;
    }
    public void setGoals(Integer Goals) {
        this.Goals = Goals;
    }

    public Integer getAssists() {
        return Assists;
    }
    public void setAssists(Integer Assists) {
        this.Assists = Assists;
    }

    public Integer getConceded() {
        return Conceded;
    }
    public void setConceded(Integer Conceded) {
        this.Conceded = Conceded;
    }

    public Float getAssist_per() {
        return Assist_per;
    }
    public void setAssist_per(Float Assist_per) {
        this.Assist_per = Assist_per;
    }

    public Float getGoals_per() {
        return Goals_per;
    }
    public void setGoals_per(Float Goals_per) {
        this.Goals_per = Goals_per;
    }

    public Float getConceded_per() {
        return Conceded_per;
    }
    public void setConceded_per(Float Conceded_per) {
        this.Conceded_per = Conceded_per;
    }



    }


