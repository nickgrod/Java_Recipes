package sample.RecipeClasses;

import sample.CookingMethods.CookingMethod;

/**
 * Created by nickg on 3/10/2017.
 */
public class Instruction {

    private String description;
    private int time;
    private CookingMethod cookingMethod;

    public Instruction(String description, int time){
        this.description = description;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setCookingMethod(CookingMethod cookingMethod){

        this.cookingMethod = cookingMethod;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
