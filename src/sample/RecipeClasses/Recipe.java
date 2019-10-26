package sample.RecipeClasses;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nickg on 3/1/2017.
 */
public class Recipe {
    private final String name;
    private final Map<Ingredient, Integer> list; //A map which will use the ingredient and the amount of it
    private String description;
    private List<Instruction> instructions = new ArrayList<>();
    private int totalTime;
    private Image image;
    private String imageURL;

    public Recipe(String name) {
        this.name = name;
        this.list = new HashMap<>();
        this.description = "";
        this.image = new Image("images/chickenpotpie.jpg");
        this.imageURL = ("images/chickenpotpie.jpg");

    }



/* The addIngredient method is something I lifted from my course. It was meant to be used for a shopping cart
application. Basically, its an add method, so if the ingredient already exists itll add the amount you are trying to put
onto the list. If it isnt on the list then it will only add the amount you described. Ex if you add 1 chicken breast,
then later add another 2 chicken breasts, the print method will show you have 3 chicken breasts.
 */


    public int addIngredient(Ingredient ingredient, int amount) {
        if (ingredient != null && amount > 0) {//if the ingredient exists and we need more than 0

            int addIt = list.getOrDefault(ingredient, 0);//find it on the list if it is already there

            list.put(ingredient, addIt + amount);//if it is not there, add it

            return addIt;

        }
        return 0;
    }

    public String printIngredientsNoName(){

        String k = "Ingredients:\n\n";
        for(Ingredient ingred : list.keySet()) {// "For ingredients in the key list of our list variable..."
            String s = ingred.getName();
            int j = list.get(ingred);
            if(j != 1 && j != (-1)){
                s = s + "s";
            }
            k += list.get(ingred) + " " + s + "\n";

        }
        return k;
    }

    public String printIngredients(){
        String s = ("\n" + this.name + ":\n\n\n");
        for(Ingredient ingred : list.keySet()) {// "For ingredients in the key list of our list variable..."
            String t = ingred.getName();
            int j = list.get(ingred);
            if(j != 1 && j != (-1)){
                t = t + "s";
            }
            s = s + list.get(ingred).toString() + " " + t + "\n";//print the key (amount) for each and then the ingredient

        }
        return s;
    }

    public Map<Ingredient, Integer> getList() {
        return list;
    }

    public String getName(){
        return this.name;
    }

    // This is called a copy constructor. We use it to make a copy of another recipe by using the recipe we want to copy
    // as a parameter to copy its contents. This is a shallow copy and therefore copies references
    public Recipe(Recipe another){
        this.name = another.name;
        this.list = another.list;
        this.description = another.description;
        this.instructions = another.instructions;
        this.totalTime = another.totalTime;
        this.imageURL = another.imageURL;
    }

    public Recipe deepCopy(){
        Recipe newRecipe = new Recipe(this.getName());
        for(Ingredient ingred : this.getList().keySet()){
            newRecipe.addIngredient(ingred, this.getList().get(ingred));
        }
        return newRecipe;

    }

    public void removeRecipe(Ingredient ingredient){

        if(list.containsKey(ingredient)){
            list.remove(ingredient);
        }
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return this.name;
    }

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
//    public int recipeTotalTime(){
//        int total = 0;
//        for(int i = 0; i < instructions.size(); i ++){
//            total = total + instructions.get(i).getTime();
//        }
//        return total;
//    }

    public void setTotalTime(int time){
        this.totalTime = time;
    }

    public Integer getTotalTime(){
        return this.totalTime;
    }

    public String getTotalTimeString(){
        String totalTime1 = String.valueOf(this.totalTime);
        return totalTime1;

    }

    public void setImage(String url){
        this.image = new Image(url);
        setImageURL(url);
    }

    public String getImageURL(){
        return imageURL;

    }

    public void setImageURL(String url){
        this.imageURL = url;
    }
}


