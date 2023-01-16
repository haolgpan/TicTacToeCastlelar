package main.tictactoe.view;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.tictactoe.model.Person;



public class StatisticsController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, Integer> winCol;
    @FXML
    private TableColumn<Person, Integer> loseCol;
    @FXML
    private TableColumn<Person, Integer> tiedCol;

    public void init(ObservableList<Person> personData){
        nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
        winCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Win"));
        loseCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Lose"));
        tiedCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Tied"));
        personTable.setItems(personData);
    }
}
