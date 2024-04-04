package org.example.aircraftsystem.data;

public class personalData {

    private String personal_id;
    private String personal_name;
    private String rank;
    private String job;
    private String aircraft_type;
    private String hours;


    public personalData(String personal_id, String personal_name, String rank,String job, String aircraft_type, String hours)
    {
        this.personal_id = personal_id;
        this.personal_name = personal_name;
        this.rank=rank;
        this.job=job;
        this.aircraft_type=aircraft_type;
        this.hours=hours;
    }

    public String getAircraft_type() {
        return aircraft_type;
    }

    public String getHours() {
        return hours;
    }

    public String getJob() {
        return job;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public String getPersonal_name() {
        return personal_name;
    }

    public String getRank() {
        return rank;
    }

}
