package sample.RecipeClasses;

import sample.CookingMethods.CookingMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickg on 3/10/2017.
 */
public class PreparedIngredient extends Ingredient {

    protected List<Instruction> instructions = new ArrayList<>();


    public void setInstructions(String description, int time){
        Instruction instruction = new Instruction(description,time);
        instructions.add(instruction);
    }

    public String getInstructions(){
        String s = "";
        for(int i = 0; i < instructions.size(); i  ++){
            s = s + (i + 1) + ". " + instructions.get(i).getDescription() + "\n\n";
        }
        return s;
    }
    public int recipeTotalTime(){
        int total = 0;
        for(int i = 0; i < instructions.size(); i ++){
            total = total + instructions.get(i).getTime();
        }
        return total;
    }



}
