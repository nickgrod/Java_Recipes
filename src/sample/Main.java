package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainRecipeWindow.fxml"));
        primaryStage.setTitle("Recipe Search");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);

    }
//this Override is telling the program that each time it goes to exit, it needs to write all of the current
//recipes into our singleton RecipeData class
    @Override
    public void stop() throws Exception {
        try{
            RecipeData.getInstance().storeRecipes();
        }catch(IOException e){
            System.out.println(e.getMessage());

        }
    }
//this override is telling the program that each time it runs it needs to load the recipes into our
// Recipe Data singleton. It is pulling recipes from the text file our singleton created.

    @Override
    public void init() throws Exception {
        try{
            RecipeData.getInstance().loadRecipes();
        }catch(IOException e){
            System.out.println(e.getMessage());

        }
    }
}

