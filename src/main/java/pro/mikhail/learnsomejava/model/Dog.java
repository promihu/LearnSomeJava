package pro.mikhail.learnsomejava.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */

public class Dog {

    private static int globalID = 0;
    private int id;
    private String name;
    private Date dateOfBirth;
    private int height;
    private int weight;

    public Dog (){

    }

    public Dog(String name, String dateOfBirth, int height, int weight){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = new Date();

        try  {

            date = dateFormat.parse(dateOfBirth);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.setId(globalID);
        globalID++;
        this.setName(name);
        this.setDateOfBirth(date);
        this.setHeight(height);
        this.setWeight(weight);
    }

    public Dog(String name, int weight){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.setId(globalID);
        globalID++;
        this.setName(name);
        this.setWeight(weight);
    }

    public String getName() {
        return name;
    }

    public boolean equals(Dog dog1){
        if(!((dog1.getName()).equals(this.getName())))
            return false;
        else if (!(dog1.getHeight() ==  this.getHeight()))
            return false;
        else if (!(dog1.getWeight() ==  this.getWeight()))
            return false;
        else if ((dog1.getDateOfBirth()).compareTo(this.getDateOfBirth()) != 0)
            return false;

        return true;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private void setId(int id) {
        this.id = id;
    }
}
