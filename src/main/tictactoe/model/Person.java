package main.tictactoe.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    public StringProperty name;
    public IntegerProperty win;
    public IntegerProperty lose;
    public IntegerProperty tied;

    public Person(String name, int win, int lose, int tied) {
        this.name = new SimpleStringProperty(name);
        this.win = new SimpleIntegerProperty(win);
        this.lose = new SimpleIntegerProperty(lose);
        this.tied = new SimpleIntegerProperty(tied);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return name;
    }

    public int getWin() {
        return win.get();
    }

    public void setWin(int win) {
        this.win.set(win);
    }

    public IntegerProperty winProperty() {
        return win;
    }

    public int geLose() {
        return lose.get();
    }

    public void setLose(int lose) {
        this.lose.set(lose);
    }

    public IntegerProperty loseProperty() {
        return lose;
    }

    public int getTied() {
        return tied.get();
    }

    public void setTied(int tied) {
        this.tied.set(tied);
    }

    public IntegerProperty tiedProperty() {
        return tied;
    }
}
