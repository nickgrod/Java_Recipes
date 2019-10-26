package sample.RecipeClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nickg on 3/1/2017.
 */
public class Cupboard {

    private final Map<Ingredient, Integer> contents;

    public Cupboard(){
        this.contents = new HashMap<>();
    }

    public int addIngredient(Ingredient ingredient, int amount) {
        if (ingredient != null && amount > 0) {

            int addIt = contents.getOrDefault(ingredient, 0);//find it on the list if it is already there

            contents.put(ingredient, addIt + amount);//if it is not there, add it

            return addIt;

        }
        return 0;
    }
    public void printContents(){
        System.out.println("\nIn our cupboard: ");
        for(Ingredient ingred : contents.keySet()) {// "For ingredients in the key list of our list variable..."
            String s = ingred.getName();
            int j = contents.get(ingred);
            if(j > 1){
                s = s + "s";
            }
            System.out.println(contents.get(ingred) + " " + s);//print the key (amount) for each and then the ingredient

        }
    }

    public Recipe toRecipe(){

        Recipe allIngredients = new Recipe("what's in our cupboard");
        for(Ingredient ingredient : contents.keySet()){
            allIngredients.addIngredient(ingredient, contents.get(ingredient));
        }
        return allIngredients;
    }
}
