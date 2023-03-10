package main.tictactoe.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.tictactoe.model.Person;
import java.util.ArrayList;

/**
 *The controller for the winner layout. The winne layout provides the basic
 * application layout containing a window and display it when a player wins
 * or it's a draw.
 */
public class WinnerController {

    @FXML
    private TextField playerXName;
    @FXML
    private TextField playerOName;
    @FXML
    private Button submit;
    @FXML
    private ImageView winner;
    private boolean foundX = false;
    private boolean foundO = false;

    public Button getSubmit() {
        return submit;
    }

    /**
     *Turns off the textfield for player O when human
     * versus cpu is selected.
     */
    public void turnOffTextO(){
        playerOName.setDisable(true);
    }

    /**
     *Display winner image when the winner stage is also displayed.
     */
    public void showImage(){
        winner.setImage(new Image("file:resources/winner.jpg"));
    }

    /**
     * Method to add a player to an arraylist of players(Person),
     * set data of the played games and record it for statistics.
     * @param persons Arraylist of players.
     * @param winner Winner combination(XXX, OOO, DRAW).
     * @param cpu Check if a human plays or a cpu.
     */
    public void addStats(ArrayList<Person> persons, String winner, boolean cpu){
        for (Person person : persons) {
            if (person.getName().equals(playerXName.getText())) {
                foundX = true;
            }
            if (person.getName().equals(playerOName.getText())) {
                foundO = true;
            }
        }
        if(!foundX) persons.add(new Person(playerXName.getText()));
        if(!foundO && !playerOName.isDisabled()) persons.add(new Person(playerOName.getText()));
        for(Person p : persons) {
            switch (winner) {
                case "XXX":
                    if (p.getName().equals(playerXName.getText())) {
                        p.setWin(p.getWin() + 1);
                        if (cpu) persons.get(0).setLose(persons.get(0).getLose() + 1);
                        else for (Person person : persons) {
                            if (person.getName().equals(playerOName.getText()))
                                person.setLose(person.getLose() + 1);
                        }
                    }
                    break;
                case "OOO":
                    if (p.getName().equals(playerXName.getText())) {
                        p.setLose(p.getLose() + 1);
                        if (cpu) persons.get(0).setWin(persons.get(0).getWin() + 1);
                        else for (Person pO : persons)
                            if (pO.getName().equals(playerOName.getText()))
                                pO.setWin(pO.getWin() + 1);
                    }
                    break;
                case "DRAW":
                    if (p.getName().equals(playerXName.getText())) {
                        p.setTied(p.getTied() + 1);
                        if (cpu) persons.get(0).setTied(persons.get(0).getTied() + 1);
                        else for (Person pO : persons)
                            if (pO.getName().equals(playerOName.getText()))
                                pO.setTied(p.getTied());
                    }
                    break;
            }
        }
    }
}
