package kang_tran_project;

/*
 * Models the pet class
 * Authors: Ray & Thanh
 */

/**
 * class that holds the database for all pets
 * @author Thanh Tran
 */

public class Pet {

    // data fields
    private String name;    // declare string name
    private String type;    // declare string pet type
    private String breed;  // declare breen
    private int age;    // declare age
    private double weight;  // declare weight
    private boolean neutered;   // declare neutered
    private String other;   // declare other

    /**
     * Constructor
     * @param name name of pet
     * @param type type of pet
     * @param breed breed of pet
     * @param age age of pet
     * @param weight weight of pet
     * @param neutered check if pet is neutered
     * @param other extra info about pet
     */
    
    
    public Pet(String name, String type, String breed, int age, double weight, boolean neutered, String other) {
        this.name = name;  
        this.type = type;    
        this.breed = breed; 
        this.age = age;
        this.weight = weight;
        this.neutered = neutered;
        this.other = other;
    }

    /**
     * access name
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * access pet type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * access breed
     * @return breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * access age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * access weight
     * @return weight 
     */
    
    public double getWeight() {
        return weight;
    }

    /** 
     * access neutered
     * @return neutered
     */
    public boolean isNeutered() {
        return neutered;
    }

    /**
     * access other info
     * @return other
     */
    public String getOther() {
        return other;
    }

    /**
     * set Name
     * @param name pet name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set pet type
     * @param type pet type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * set breed
     * @param breed breed type
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * set Age
     * @param age age of pet
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * set Weight
     * @param weight weight of pet
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * set neutered
     * @param neutered  determines if the pet is neutered or not
     */
    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    /**
     * set Other
     * @param other extra information about the pet 
     */
    
    public void setOther(String other) {
        this.other = other;
    }

}
