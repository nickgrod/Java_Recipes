package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.RecipeClasses.Recipe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nickg on 4/11/2017.
 */
public class RecipeData {
    private static RecipeData instance = new RecipeData();
    private static String filename = "RecipeData.txt";

    private ObservableList<Recipe> recipes;

    public static RecipeData getInstance() {

        return instance;
    }

    private RecipeData() {

    }

    public ObservableList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ObservableList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe newrecipe) {
        recipes.add(newrecipe);

    }

    public void loadRecipes() throws IOException {
        recipes = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String recipeName = itemPieces[0];
                String recipeDescription = itemPieces[1];
                String recipeTotalTime = itemPieces[2];
                String recipeImageURL = itemPieces[3];

                Recipe recipe = new Recipe(recipeName);
                recipe.setDescription(recipeDescription);
                recipe.setTotalTime(Integer.parseInt(recipeTotalTime));
                recipe.setImage(recipeImageURL);
                recipes.add(recipe);

            }

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeRecipes() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Recipe> iter = recipes.iterator();
            while (iter.hasNext()) {
                Recipe recipe = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s",
                        recipe.getName(),
                        recipe.getDescription(),
                        recipe.getTotalTimeString(),
                        recipe.getImageURL()));
                bw.newLine();

            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void deleteRecipe(Recipe item) {
        recipes.remove(item);
    }

    public Recipe findRecipe(String name){
        if(name != null){
            Iterator<Recipe> iter = recipes.iterator();
            while(iter.hasNext()){
                Recipe thisOne = iter.next();
                if(thisOne.getName().equalsIgnoreCase(name)){
                    return thisOne;
                }
            }
        }
        return null;
    }
}