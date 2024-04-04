package org.example.aircraftsystem.data;

public class flightData {
    private String flight_id;
    private String last_point;
    private String start_date;
    private String flight_date;
    private String middle_point;
    private String aircraft_type;


    public flightData(String flight_id, String last_point, String start_date, String flight_date, String middle_point, String aircraft_type)
    {
    this.flight_id = flight_id;
    this.last_point = last_point;
    this.start_date = start_date;
    this. flight_date =flight_date;
    this. middle_point = middle_point;
    this.aircraft_type = aircraft_type;
    }



    public String getFlight_id() {
        return flight_id;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public String getAircraft_type() {
        return aircraft_type;
    }

    public String getLast_point() {
        return last_point;
    }

    public String getMiddle_point() {
        return middle_point;
    }

    public String getStart_date() {
        return start_date;
    }

}
