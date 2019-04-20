package com.triviaapp.model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
    private String answer1;
    private String answer2;
    private Date date;
    private String name;

    public Game(){}

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
