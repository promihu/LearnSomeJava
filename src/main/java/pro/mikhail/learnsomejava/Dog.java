package pro.mikhail.learnsomejava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mikhail_Prosuntsov on 8/24/2016.
 */

public class Dog {

    private String name;
    private Date dateOfBirth;
    private int height;
    private int weight;

    public Dog (){

    }

    Dog(String name, String dateOfBirth, int height, int weight){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = new Date();

        try  {

            date = dateFormat.parse(dateOfBirth);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.setName(name);
        this.setDateOfBirth(date);
        this.setHeight(height);
        this.setWeight(weight);
    }

    public String getName() {
        return name;
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
}
