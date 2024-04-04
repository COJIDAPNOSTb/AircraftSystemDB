package org.example.aircraftsystem;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.aircraftsystem.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class QueryController implements Initializable {

    @FXML
    private Button query_repairresult_button;
    @FXML
    private Button query_airresult_field;
    @FXML
    private Button query_pointresult_button;
    @FXML
    private Button qury_finalresult_button;


    @FXML
    private TextField query_repair_field;
    @FXML
    private TextField query_aircraft_field;
    @FXML
    private TextField query_point_field;


    @FXML
    private ComboBox <?> query_final_box;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;




    public void addQueryRepair() {
        String sql = "SELECT *FROM aircraft WHERE EXTRACT(YEAR FROM AGE(NOW(), TO_DATE(last_date_repair, 'YYYY-MM-DD'))) > ?;";
        connect= database.connectDb();
        try {
            if (query_repair_field.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Введите значение!");
                alert.showAndWait();
            } else {
                statement=connect.createStatement();
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, (Integer.parseInt(query_repair_field.getText())));
                ResultSet rs = prepare.executeQuery();

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();


                TableView<Map<String, Object>> tableView = new TableView<>();

                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    TableColumn<Map<String, Object>, Object> column = new TableColumn<>(metaData.getColumnName(columnIndex));
                    final int colIndex = columnIndex;
                    column.setCellValueFactory(cellData -> {
                        try {
                            return new SimpleObjectProperty<>(cellData.getValue().get(metaData.getColumnName(colIndex)));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    tableView.getColumns().add(column);
                }


                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();


                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        row.put(metaData.getColumnName(columnIndex), rs.getObject(columnIndex));
                    }
                    data.add(row);
                }

                tableView.setItems(data);
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Запрещает изменение размеров столбцов
                tableView.setEditable(false);
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(tableView), 600, 400));
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public void addQueryAircraft() {
        String sql = "SELECT *FROM aircraft WHERE aircraft_type = (SELECT aircraft_type FROM flight WHERE flight_id = ?);";
        connect= database.connectDb();
        try {
            if (query_aircraft_field.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Введите рейс!");
                alert.showAndWait();
            } else {
                statement=connect.createStatement();
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, (String) query_aircraft_field.getText());
                ResultSet rs = prepare.executeQuery();

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();


                TableView<Map<String, Object>> tableView = new TableView<>();

                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    TableColumn<Map<String, Object>, Object> column = new TableColumn<>(metaData.getColumnName(columnIndex));
                    final int colIndex = columnIndex;
                    column.setCellValueFactory(cellData -> {
                        try {
                            return new SimpleObjectProperty<>(cellData.getValue().get(metaData.getColumnName(colIndex)));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    tableView.getColumns().add(column);
                }


                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();


                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        row.put(metaData.getColumnName(columnIndex), rs.getObject(columnIndex));
                    }
                    data.add(row);
                }

                tableView.setItems(data);
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Запрещает изменение размеров столбцов
                tableView.setEditable(false);
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(tableView), 600, 400));
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







    public void addQueryFlight() {
        String sql = "SELECT *FROM flight WHERE last_point = ?;";
        connect= database.connectDb();
        try {
            if (query_point_field.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Напишите регион!");
                alert.showAndWait();
            } else {
                statement=connect.createStatement();
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, (String) query_point_field.getText());
                ResultSet rs = prepare.executeQuery();

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();


                TableView<Map<String, Object>> tableView = new TableView<>();

                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    TableColumn<Map<String, Object>, Object> column = new TableColumn<>(metaData.getColumnName(columnIndex));
                    final int colIndex = columnIndex;
                    column.setCellValueFactory(cellData -> {
                        try {
                            return new SimpleObjectProperty<>(cellData.getValue().get(metaData.getColumnName(colIndex)));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    tableView.getColumns().add(column);
                }


                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();


                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        row.put(metaData.getColumnName(columnIndex), rs.getObject(columnIndex));
                    }
                    data.add(row);
                }

                tableView.setItems(data);
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Запрещает изменение размеров столбцов
                tableView.setEditable(false);
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(tableView), 600, 400));
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }










    public void addQueryResult() {
        String sql = "SELECT * FROM public.personal WHERE aircraft_type = ?";
       connect= database.connectDb();
        try {
            if (query_final_box.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Выберите тип самолета!");
                alert.showAndWait();
            } else {
                statement=connect.createStatement();
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, (String) query_final_box.getSelectionModel().getSelectedItem());
                ResultSet rs = prepare.executeQuery();

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();


                TableView<Map<String, Object>> tableView = new TableView<>();

                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    TableColumn<Map<String, Object>, Object> column = new TableColumn<>(metaData.getColumnName(columnIndex));
                    final int colIndex = columnIndex;
                    column.setCellValueFactory(cellData -> {
                        try {
                            return new SimpleObjectProperty<>(cellData.getValue().get(metaData.getColumnName(colIndex)));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    tableView.getColumns().add(column);
                }


                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();


                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        row.put(metaData.getColumnName(columnIndex), rs.getObject(columnIndex));
                    }
                    data.add(row);
                }
                
                tableView.setItems(data);
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Запрещает изменение размеров столбцов
                tableView.setEditable(false);
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(tableView), 600, 400));
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        query_final_box.setItems(listData);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

addAircraftList();
    }

}
