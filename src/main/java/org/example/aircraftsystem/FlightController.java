package org.example.aircraftsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FlightController implements Initializable {
    @FXML
private Button add_button;
@FXML
private Button delete_button;
@FXML
private TextField delete_field;
@FXML
private TextField id_field;

@FXML
private TextField middle_point_field;
@FXML
private ComboBox<?> aircraft_box;

@FXML
private TextField last_point_field;

@FXML
private TextField start_field;
    @FXML
    private TextField hours_field;

private Connection connect;
private Statement statement;
private PreparedStatement prepare;
private ResultSet result;

public void addData()
{
    String sql = "INSERT INTO public.flight(" + "flight_id, last_point, start_date, flight_date, middle_point,aircraft_type)" + "VALUES (?, ?, ?, ?, ? ,?);";
    connect = database.connectDb();
    try{
        Alert alert;
        if(id_field.getText().isEmpty()
                || last_point_field.getText().isEmpty() ||start_field.getText().isEmpty() ||
                hours_field.getText().isEmpty() ||
                middle_point_field.getText().isEmpty()||
                aircraft_box.getSelectionModel().getSelectedItem()==null)
        {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Заполнтие все поля!");
            alert.showAndWait();
        }
        else {

            String check="SELECT flight_id FROM flight WHERE flight_id = '"+id_field.getText()+"'";
            statement=connect.createStatement();
            result=statement.executeQuery(check);
            if(result.next())
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Рейс под кодовым номером "+id_field.getText()+ "уже сущетсвует!");
                alert.showAndWait();
            }
            else {


                prepare = connect.prepareStatement(sql);
                prepare.setString(1,id_field.getText());
                prepare.setString(2, last_point_field.getText());
                prepare.setString(3, start_field.getText());
                prepare.setString(4,hours_field.getText());
                prepare.setString(5, middle_point_field.getText());
                prepare.setString(6, (String) aircraft_box.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("Successfuly add");
                alert.showAndWait();


                Reset();

            }

        }}
    catch (Exception e)
    {
        e.printStackTrace();
    }
}


public void Reset()
{
    id_field.setText("");
    last_point_field.setText("");
    start_field.setText("");
    hours_field.setText("");
    middle_point_field.setText("");
    aircraft_box.getSelectionModel().clearSelection();
}
public void deleteData()
{
    String sql = "DELETE FROM flight WHERE flight_id = '"+delete_field.getText()+"'";
    connect = database.connectDb();
    try
    {
        Alert alert;
        if(delete_field.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Введите ID!");
            alert.showAndWait();
        }

        statement=connect.createStatement();
        statement.executeUpdate(sql);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Успешно!");
        alert.showAndWait();
        Reset();

    }

    catch (Exception e)
    {
        e.printStackTrace();
    }
}








private String[] aircraftList={"Boeing 787", "Boeing 777", "Boeing 747","Boeing 737" , "Airbus A380" , "Airbus A350" , "Airbus A320"};
public void addAircraftList()
{
    List<String> listA = new ArrayList<>();
    for(String data:aircraftList){
        listA.add(data);
    }
    ObservableList listData = FXCollections.observableArrayList(listA);
    aircraft_box.setItems(listData);

}

@Override
public void initialize(URL url, ResourceBundle resourceBundle) {


    addAircraftList();

}





}
