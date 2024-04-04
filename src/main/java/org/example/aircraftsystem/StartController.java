package org.example.aircraftsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.aircraftsystem.data.aircraftData;
import org.example.aircraftsystem.data.flightData;
import org.example.aircraftsystem.data.personalData;
import org.example.aircraftsystem.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StartController implements Initializable {
private double x;
private double y;
    @FXML
    private StackPane main_form;
    @FXML
    private AnchorPane personal_form;
    @FXML
    private AnchorPane aircraft_form;
    @FXML
    private AnchorPane flight_form;


    @FXML
    private Button personal_table_button;
    @FXML
    private Button aircraft_table_button;
    @FXML
    private Button flight_table_button;
    @FXML
    private Button query_button;


    @FXML
    private Button personal_redactor_button;
    @FXML
    private Button aircraft_redactor_button;
    @FXML
    private Button flight_redactor_button;



    @FXML
    private Button exit_button;
    @FXML
    private Button hide_button;

    @FXML
    private TableView<personalData>personal_table;
    @FXML
    private TableColumn<personalData,String>personal_id_col;
    @FXML
    private TableColumn<personalData,String> personal_name_col;
    @FXML
    private TableColumn<personalData,String>personal_job_col;
    @FXML
    private TableColumn<personalData,String>personal_rank_col;
    @FXML
    private TableColumn<personalData,String>personal_aircrafttype_col;
    @FXML
    private TableColumn<personalData,String>personal_hours_col;


    @FXML
    private TableView<aircraftData> aircraft_table;
    @FXML
    private TableColumn<aircraftData,String> aircraft_id_col;
    @FXML
    private TableColumn<aircraftData,String>aircraft_type_col;
    @FXML
    private TableColumn<aircraftData,String>aircraft_hour_col;
    @FXML
    private TableColumn<aircraftData,String>aircraft_date_col;
    @FXML
    private TableColumn<aircraftData,String>aircraft_flightid_col;



    @FXML
    private TableView<flightData>flight_table;
    @FXML
    private TableColumn<flightData,String> flight_flightid_col;
    @FXML
    private TableColumn<flightData,String>flight_lastpoint_col;
    @FXML
    private TableColumn<flightData,String>flight_startdate_col;
    @FXML
    private TableColumn<flightData,String>flight_flightdate_col;
    @FXML
    private TableColumn<flightData,String>flight_middlepoint_col;
    @FXML
    private TableColumn<flightData,String>flight_aircrafttype_col;


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;





    public ObservableList<personalData> personalListData()
    {
        ObservableList<personalData>listData = FXCollections.observableArrayList();
        String sql = "SELECT *FROM personal";
        connect = database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result = prepare.executeQuery();
            personalData personalD;
            while (result.next())
            {
                personalD = new personalData(result.getString("personal_id"),
                        result.getString("personal_name"),
                        result.getString("rank"),
                        result.getString("job"),
                        result.getString("aircraft_type"),
                        result.getString("hours")
                );
                listData.add(personalD);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }



    public ObservableList<aircraftData> aircraftListData()
    {
        ObservableList<aircraftData>listData = FXCollections.observableArrayList();
        String sql = "SELECT *FROM aircraft";
        connect = database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result = prepare.executeQuery();
            aircraftData aircraftD;
            while (result.next())
            {
                aircraftD = new aircraftData(result.getString("aircraft_id"),
                        result.getString("aircraft_type"),
                        result.getString("flight_hours"),
                        result.getString("last_date_repair"),
                        result.getString("flight_id")
                );
                listData.add(aircraftD);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }










    public ObservableList<flightData> flightListData()
    {
        ObservableList<flightData>listData = FXCollections.observableArrayList();
        String sql = "SELECT *FROM flight";
        connect = database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result = prepare.executeQuery();
           flightData flightD;
            while (result.next())
            {
                flightD = new flightData(result.getString("flight_id"),
                        result.getString("last_point"),
                        result.getString("start_date"),
                        result.getString("flight_date"),
                        result.getString("middle_point"),
                        result.getString("aircraft_type")
                );
                listData.add(flightD);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }






    private ObservableList<personalData> personalList;
    public void personalShowListData()
    {
        personalList = personalListData();
        personal_id_col.setCellValueFactory(new PropertyValueFactory<>("personal_id"));
        personal_name_col.setCellValueFactory(new PropertyValueFactory<>("personal_name"));
        personal_rank_col.setCellValueFactory(new PropertyValueFactory<>("rank"));
        personal_job_col.setCellValueFactory(new PropertyValueFactory<>("job"));
        personal_aircrafttype_col.setCellValueFactory(new PropertyValueFactory<>("aircraft_type"));
        personal_hours_col.setCellValueFactory(new PropertyValueFactory<>("hours"));
        personal_table.setItems(personalList);
    }


    private ObservableList<aircraftData> aircraftList;
    public void aircraftShowListData()
    {
        aircraftList= aircraftListData();
        aircraft_id_col.setCellValueFactory(new PropertyValueFactory<>("aircraft_id"));
        aircraft_type_col.setCellValueFactory(new PropertyValueFactory<>("aircraft_type"));
        aircraft_hour_col.setCellValueFactory(new PropertyValueFactory<>("flight_hours"));
        aircraft_date_col.setCellValueFactory(new PropertyValueFactory<>("last_date_repair"));
        aircraft_flightid_col.setCellValueFactory(new PropertyValueFactory<>("flight_id"));
        aircraft_table.setItems(aircraftList);
    }




    private ObservableList<flightData> flightList;
    public void flightShowListData()
    {
        flightList = flightListData();
        flight_flightid_col.setCellValueFactory(new PropertyValueFactory<>("flight_id"));
        flight_lastpoint_col.setCellValueFactory(new PropertyValueFactory<>("last_point"));
        flight_startdate_col.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        flight_flightdate_col.setCellValueFactory(new PropertyValueFactory<>("flight_date"));
        flight_middlepoint_col.setCellValueFactory(new PropertyValueFactory<>("middle_point"));
        flight_aircrafttype_col.setCellValueFactory(new PropertyValueFactory<>("aircraft_type"));
        flight_table.setItems(flightList);
    }





    public void switchForm(ActionEvent event)
    {
        if(event.getSource()==personal_table_button)
        {
            personal_form.setVisible(true);
            aircraft_form.setVisible(false);
            flight_form.setVisible(false);
            personalShowListData();
        }
        else if(event.getSource()==aircraft_table_button) {
            personal_form.setVisible(false);
            aircraft_form.setVisible(true);
            flight_form.setVisible(false);
            aircraftShowListData();

        }
        else if(event.getSource()==flight_table_button) {
            personal_form.setVisible(false);
            aircraft_form.setVisible(false);
            flight_form.setVisible(true);
            flightShowListData();
        }

    }


public void query_window() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("masterquery.fxml"));
    Stage stage = new Stage();
    Scene scene = new Scene(root);

    root.setOnMousePressed((MouseEvent event) ->{
        x = event.getSceneX();
        y = event.getSceneY();
    });

    root.setOnMouseDragged((MouseEvent event) ->{
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

        stage.setOpacity(.8);
    });

    root.setOnMouseReleased((MouseEvent event) ->{
        stage.setOpacity(1);
    });

    //stage.initStyle(StageStyle.TRANSPARENT);

stage.setTitle("Мастер запросов");
    stage.setScene(scene);
    stage.show();

}

    public void personal_redact() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("personal.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        //stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Мастер редактирования");
        stage.setScene(scene);
        stage.show();

    }

    public void aircraft_redact() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aircraft.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        //stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Мастер редактирования");
        stage.setScene(scene);
        stage.show();

    }

    public void flight_redact() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("flight.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });

        //stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Мастер редактирования");
        stage.setScene(scene);
        stage.show();

    }


    public void close()
    {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalShowListData();


    }

}