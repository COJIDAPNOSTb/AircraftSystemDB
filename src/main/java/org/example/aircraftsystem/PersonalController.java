package org.example.aircraftsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersonalController implements Initializable {
    @FXML
    private Button personal_add_button;
    @FXML
    private Button personal_delete_button;
    @FXML
    private TextField personal_delete_field;
    @FXML
    private TextField personal_id_field;
    @FXML
    private TextField personal_name_field;
    @FXML
    private TextField personal_hours_field;
    @FXML
    private ComboBox<?>personal_rank_box;
    @FXML
    private ComboBox<?>personal_aircraft_box;
    @FXML
    private ComboBox<?>personal_job_box;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public void addData()
    {
        String sql = "INSERT INTO public.personal(" + "personal_id, personal_name, rank, job, aircraft_type, hours)" + "VALUES (?, ?, ?, ?, ?, ?);";
        connect = database.connectDb();
        try{
            Alert alert;
            if(personal_id_field.getText().isEmpty()
                    || personal_name_field.getText().isEmpty() ||
                    personal_hours_field.getText().isEmpty() ||
                    personal_job_box.getSelectionModel().getSelectedItem() == null||
                    personal_rank_box.getSelectionModel().getSelectedItem() == null ||
                    personal_aircraft_box.getSelectionModel().getSelectedItem()==null)
            {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Заполнтие все поля!");
                alert.showAndWait();
            }
            else {

                String check="SELECT personal_id FROM personal WHERE personal_id = '"+personal_id_field.getText()+"'";
                statement=connect.createStatement();
                result=statement.executeQuery(check);
                if(result.next())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Человек под кодовым номером "+personal_id_field.getText()+ "уже сущетсвует!");
                    alert.showAndWait();
                }
                else {


                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, personal_id_field.getText());
                    prepare.setString(2, personal_name_field.getText());
                    prepare.setString(3,(String) personal_rank_box.getSelectionModel().getSelectedItem());
                    prepare.setString(4,(String) personal_job_box.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) personal_aircraft_box.getSelectionModel().getSelectedItem());
                    prepare.setString(6, personal_hours_field.getText());
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
        personal_id_field.setText("");
        personal_name_field.setText("");
        personal_hours_field.setText("");

        personal_rank_box.getSelectionModel().clearSelection();
        personal_job_box.getSelectionModel().clearSelection();
        personal_aircraft_box.getSelectionModel().clearSelection();



    }
    public void deleteData()
    {
        String sql = "DELETE FROM personal WHERE personal_id = '"+personal_delete_field.getText()+"'";
        connect = database.connectDb();
        try
        {
            Alert alert;
            if(personal_delete_field.getText().isEmpty())
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







    private String[] rankList={ "Курсант",
            "Старший курсант",
            "Летчик-инструктор",
            "Командир экипажа",
            "Капитан",
            "Старший капитан"};
    public void addRankList()
    {
        List<String> listR = new ArrayList<>();
        for(String data:rankList){
            listR.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listR);
        personal_rank_box.setItems(listData);
    }

    private String[] jobList={"Командир борта",
            "Второй пилот",
            "Старший инструктор-летчик",
            "Летчик-испытатель",
            "Старший инструктор-летчик",
            "Летчик-инструктор",
            "Пилот-фрилансер",
            "Летчик-разведчик",
            "Командир экипажа",
            "Пилот-инструктор",
            "Пилот-вторник",
            "Пилот-последователь",
            "Пилот-исследователь"};
    public void addJobList()
    {
        List<String>listJ = new ArrayList<>();
        for(String data:jobList){
            listJ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listJ);
        personal_job_box.setItems(listData);
    }


    private String[] aircraftList={"Boeing 787", "Boeing 777", "Boeing 747","Boeing 737" , "Airbus A380" , "Airbus A350" , "Airbus A320"};
    public void addAircraftList()
    {
        List<String> listA = new ArrayList<>();
        for(String data:aircraftList){
            listA.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listA);
        personal_aircraft_box.setItems(listData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        addAircraftList();
        addJobList();
        addRankList();
    }

}
