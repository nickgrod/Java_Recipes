package sample.RecipeClasses;

/**
 * Created by nickg on 3/1/2017.
 */
public abstract class Ingredient {

    protected String name;
    protected String portionType;

    public Ingredient(){
        this.name = "";
        this.portionType = "";
    }

    public Ingredient(String name, String portionType){
        this.name = name;
        this.portionType = portionType;
    }


    // always have to override hashcode when using a hashmap
    @Override
    public int hashCode() {
        return name.hashCode() + 22;//using the unique hash of the name and the number 22 to get the same hashcode
        //any time we put in the same ingredient
    }


    //always override equals when making your own objects for a collection
    @Override
    public boolean equals(Object obj) {// receiving a generic object as parameter
        Ingredient ob = (Ingredient) obj;//creating a new Ingredient object by casting obj

        if (this == ob){
            return true;
        }
        if(ob == null){//if this object doesnt exist, its false
            return false;
        }
        else if(!(ob.getName().equalsIgnoreCase(this.name)) || ob.hashCode() != this.hashCode()){//if this object doesnt have the same
            //name or hashcode, its false
            return false;
        }
        else{
            return true;
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {

        return this.name;
    }

    public String getPortionType() {
        return portionType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPortionType(String portionType) {
        this.portionType = portionType;
    }
}
