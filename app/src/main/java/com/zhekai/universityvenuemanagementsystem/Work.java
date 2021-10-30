package com.zhekai.universityvenuemanagementsystem;


public class Work {

    private int reservation;
    private String phonenumber;
    private String venueID;
    private String date;
    private String startTime;
    private String endtime;
    private String username;

    public Work(int reservation, String phonenumber, String venueID, String date, String startTime, String endtime, String username) {
        this.reservation = reservation;
        this.phonenumber = phonenumber;
        this.venueID = venueID;
        this.date = date;
        this.startTime = startTime;
        this.endtime = endtime;
        this.username = username;
    }

    public Work() {

    }

    @Override
    public String toString() {

        return "\n\nReservation ID: " + reservation +
                "\n\nPhone Number: " + phonenumber +
                "\n\nVenue ID: " + venueID +
                "\n\nDate: " + date +
                "\n\nStart Time: " + startTime +
                "\n\nEnd Time: " + endtime +
                "\n\nReserved By " + username + "\n\n";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
