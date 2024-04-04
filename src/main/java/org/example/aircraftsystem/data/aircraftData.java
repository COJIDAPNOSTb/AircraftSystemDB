package org.example.aircraftsystem.data;

public class aircraftData {

    private String aircraft_id;
    private String aircraft_type;
    private String flight_hours;
    private String last_date_repair;
    private String flight_id;


    public aircraftData(String aircraft_id, String aircraft_type, String flight_hours, String last_date_repair, String flight_id)
    {
        this.aircraft_id=aircraft_id;
        this.aircraft_type=aircraft_type;
        this.flight_hours=flight_hours;
        this.last_date_repair=last_date_repair;
        this.flight_id=flight_id;
    }

    public String getAircraft_type() {
        return aircraft_type;
    }

    public String getAircraft_id() {
        return aircraft_id;
    }

    public String getFlight_hours() {
        return flight_hours;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public String getLast_date_repair() {
        return last_date_repair;
    }
}
