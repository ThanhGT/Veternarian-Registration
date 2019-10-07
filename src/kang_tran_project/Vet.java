package kang_tran_project;

import java.util.ArrayList;

/*
 * Models the vet class
 * Authors: Ray & Thanh
 */

/**
 * class that models the list of veterinarian
 * @author Ray Kang
 */
public class Vet {

    private String name;    // vet name
    ArrayList<Appointment> appointment = new ArrayList<Appointment>();  // array list

    /**
     * 1-arg constructor
     * @param name name of vet
     */
    public Vet(String name) {
        this.name = name;
    }

    /**
     * access name of vet
     * @return name of vet 
     */
    public String getName() {
        return name;
    }

    /**
     * set vets name
     * @param name name of vet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * array list of appointments booked
     * @return booked appointments
     */
    public ArrayList<Appointment> getAppointment() {
        return appointment;
    }

}
