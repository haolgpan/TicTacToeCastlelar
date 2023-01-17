package main.tictactoe.model;

/**
 *Model class for players
 */
public class Person{
    private final String name;
    private int win;
    private int lose;
    private int tied;

    /**
     *Default constructor with initial data of wins, lose and tied.
     * @param name Player's name.
     */
    public Person(String name) {
        this.name = name;
        this.win = 0;
        this.lose = 0;
        this.tied = 0;
    }

    public String getName() {
        return name;
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
}
