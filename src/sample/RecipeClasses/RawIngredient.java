package sample.RecipeClasses;

/**
 * Created by nickg on 3/10/2017.
 */
public class RawIngredient extends Ingredient {

    public RawIngredient(){
        this.name = "";
        this.portionType = "";
    }

    public RawIngredient(String name, String portionType){
        this.name = name;
        this.portionType = portionType;
    }

}
