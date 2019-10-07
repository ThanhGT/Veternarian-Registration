package kang_tran_project;

import java.util.Comparator;
import java.util.Objects;

/*
 * Models appointments
 * Authors: Ray & Thanh
 */

/**
 * Comparable class for appointments
 * @author Ray Kang
 */

public class Appointment implements Comparable<Appointment> {

    //data fields
    private String date;    // datafield for date
    private String time;    // datafield for time
    private String pet;     // datafield for name of pet

    /**
     * 
     * @param date date of appointment
     * @param time time of appointment
     * @param pet pets name
     */
    public Appointment(String date, String time, String pet) {
        this.date = date.trim();        
        this.time = time.trim();
        this.pet = pet.trim();
    }
    
    public Appointment(String lineIn) {
        lineIn = lineIn.replace(" @", ",");
        lineIn = lineIn.replace(" with ", ",");
        String[] parts = lineIn.split(",");
        date = parts[0].trim();
        time = parts[1].trim();
        pet = parts[2].trim();
    }

    /**
     * access date
     * @return date
     */
    
    public String getDate() {
        return date;
    }

    /**
     * access Time
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * access pet
     * @return pet name
     */
    public String getPet() {
        return pet;
    }

    /**
     * mutator for date
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * mutator for time
     * @param time 
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * mutator for pet
     * @param pet 
     */
    public void setPet(String pet) {
        this.pet = pet;
    }

    /**
     * Override toString method for appointment class to print out proper info
     * @return String
     */
    @Override
    public String toString() {
        return date + " @" + time + " with " + pet;
    }

    /**
     * comparing current appointment with the parameter and return the difference
     * @param appointment
     * @return difference
     */
    @Override
    public int compareTo(Appointment appointment) {
        int difference = date.compareTo(appointment.getDate());
        if (difference == 0) {
            difference -= time.compareTo(appointment.getTime());
        }
        return difference;
    }

    /**
     * Compare this to an object and see if they are equivalent
     * @param o
     * @return result
     */
    @Override
    public boolean equals(Object o) {
        Appointment appointment = null;
        if(o instanceof Appointment){
            appointment = (Appointment)o;
        }else{
            return false;
        }
        if (appointment.getTime().equals(this.time) && appointment.getDate().equals(this.date)) {
            return true;
        }
        return false;
    }

}
