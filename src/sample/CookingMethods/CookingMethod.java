package sample.CookingMethods;

/**
 * Created by nickg on 3/13/2017.
 */
public class CookingMethod {

    protected boolean requiresTemp;
    protected String name;
    protected String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
