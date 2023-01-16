package main.tictactoe.model;

public class Person implements Comparable<Person>{
    public String name;
    public int win;
    public int lose;
    public int tied;

    public Person(String name) {
        this.name = name;
        this.win = 0;
        this.lose = 0;
        this.tied = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getTied() {
        return tied;
    }

    public void setTied(int tied) {
        this.tied = tied;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", win=" + win +
                ", lose=" + lose +
                ", tied=" + tied +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        if(this.win < person.getWin()) return 1;
        else if (this.win > person.getWin()) return -1;
        else return 0;
    }
}
