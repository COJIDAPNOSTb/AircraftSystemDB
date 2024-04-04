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

public class AircraftController implements Initializable {
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private TextField delete_field;
    @FXML
    private TextField id_field;

    @FXML
    private TextField hours_field;
    @FXML
    private ComboBox<?> aircraft_box;

    @FXML
    private TextField date_field;
    @FXML
    private TextField flight_field;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public void addData()
    {
        String sql = "INSERT INTO public.aircraft(" + "aircraft_id, aircraft_type, flight_hours, last_date_repair, flight_id)" + "VALUES (?, ?, ?, ?, ? );";
        connect = database.connectDb();
        try{
            Alert alert;
            if(id_field.getText().isEmpty()
                    || date_field.getText().isEmpty() ||
                    hours_field.getText().isEmpty() ||
                    flight_field.getText().isEmpty()||
                    aircraft_box.getSelectionModel().getSelectedItem()==null)
            {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Заполнтие все поля!");
                alert.showAndWait();
            }
            else {

                String check="SELECT aircraft_id FROM aircraft WHERE aircraft_id = '"+id_field.getText()+"'";
                statement=connect.createStatement();
                result=statement.executeQuery(check);
                if(result.next())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Самолет под кодовым номером "+id_field.getText()+ "уже сущетсвует!");
                    alert.showAndWait();
                }
                else {


                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1,id_field.getText());
                    prepare.setString(2,(String) aircraft_box.getSelectionModel().getSelectedItem());
                    prepare.setString(3,hours_field.getText());
                    prepare.setString(4,date_field.getText());
                    prepare.setString(5, flight_field.getText());
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
        flight_field.setText("");
        hours_field.setText("");
        date_field.setText("");
        aircraft_box.getSelectionModel().clearSelection();
    }
    public void deleteData()
    {
        String sql = "DELETE FROM aircraft WHERE aircraft_id = '"+delete_field.getText()+"'";
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
