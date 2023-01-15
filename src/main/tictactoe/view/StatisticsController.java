package main.tictactoe.view;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.tictactoe.MainApp;
import main.tictactoe.model.Person;

import java.util.ArrayList;

public class StatisticsController {
    @FXML
    TableView<Person> personTable;
    @FXML
    TableColumn<Person, String> nameCol;
    @FXML
    TableColumn<Person, Integer> winCol;
    @FXML
    TableColumn<Person, Integer> loseCol;
    @FXML
    TableColumn<Person, Integer> tiedCol;

    public void init(ObservableList<Person> personData){
        nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
        winCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Win"));
        loseCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Lose"));
        tiedCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Tied"));
        personTable.setItems(personData);
    }
}
