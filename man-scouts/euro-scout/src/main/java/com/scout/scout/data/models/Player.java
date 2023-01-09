package com.scout.scout.data.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;



    @Entity
    public class Player {
        @Id
//        @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        private String Name;

        private Integer Age;

        private String Position;

        private String Region;

        private Integer Minutes;

        private String Nationality;

        private Integer Appearances;

        private Integer Goals;

        private Integer Assists;

        private Integer Conceded;

        private Float Assist_per;

        private Float Goals_per;

        private Float Conceded_per;


        public Player(){}


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
            this.Conceded_per = Goals_per;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "Name=" + Name +
                    ", Age='" + Age + '\'' +
                    ", Position='" + Position + '\'' +
                    ", Region='" + Region + '\'' +
                    ", Minutes='" + Minutes + '\'' +
                    ", Nationality=" + Nationality +
                    ", Appearances='" + Appearances + '\'' +
                    ", Goals='" + Goals + '\'' +
                    ", Assists='" + Assists + '\'' +
                    ", Conceded='" + Conceded + '\'' +
                    ", Assist_per='" + Assist_per + '\'' +
                    ", Goal_per='" + Goals_per + '\'' +
                    ", Conceded_per='" + Conceded_per + '\'' +

                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player players = (Player) o;
            return  Objects.equals(Name, players.Name) && Objects.equals(Age, players.Age) &&
                    Objects.equals(Position, players.Position) && Objects.equals(Region, players.Region) &&
                    Objects.equals(Minutes, players.Minutes) && Objects.equals(Nationality, players.Nationality) &&
                    Objects.equals(Appearances, players.Appearances) && Objects.equals(Goals, players.Goals) &&
                    Objects.equals(Assists, players.Assists) && Objects.equals(Conceded, players.Conceded) &&
                    Objects.equals(Assist_per, players.Assist_per) && Objects.equals(Goals_per, players.Goals_per)
                    && Objects.equals(Conceded_per, players.Conceded_per)
                    ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Name, Age, Position, Region, Minutes,Nationality,Appearances,Goals,
                    Assists,Conceded,Assist_per,Goals_per,Conceded_per);
        }
    }

