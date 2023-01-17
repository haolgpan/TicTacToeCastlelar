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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        winCol.setCellValueFactory(new PropertyValueFactory<>("Win"));
        loseCol.setCellValueFactory(new PropertyValueFactory<>("Lose"));
        tiedCol.setCellValueFactory(new PropertyValueFactory<>("Tied"));
        personTable.setItems(personData);
    }
}
