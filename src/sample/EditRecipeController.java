package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.RecipeClasses.Recipe;

import java.io.File;
import java.io.IOException;

/**
 * Created by nickg on 5/20/2017.
 */
public class EditRecipeController{

    @FXML
    private TextField newRecipeNameEdit, newRecipeTimeEdit, imageNameEdit;
    @FXML
    private ImageView addImage;
    @FXML
    private TextArea newRecipeInstructionsEdit;
    @FXML
    private DialogPane editDialogPane;

    private Recipe myRecipe = new Recipe(Controller.recipeChosen);

    public void initialize(){

        myRecipe = new Recipe(Controller.recipeChosen);
        setAll();
    }

    @FXML
    public void setAll(){


        try {
            newRecipeNameEdit.setText(myRecipe.getName());
            newRecipeTimeEdit.setText(myRecipe.getTotalTimeString());
            imageNameEdit.setText(myRecipe.getImageURL());
            setImage();
            newRecipeInstructionsEdit.setText(myRecipe.getDescription());
        }catch(Exception e){
            if(myRecipe == null){
            System.out.println("Exception caught");
        }else {
                System.out.println("It wasn't null...");
                e.printStackTrace();
            }
            }
    }



    @FXML
    public void processResults(){
        String recipeName = newRecipeNameEdit.getText().trim();
        String instructions  = newRecipeInstructionsEdit.getText().trim();
        int time = Integer.parseInt(newRecipeTimeEdit.getText().trim());
        String url = imageNameEdit.getText();

        Recipe newestRecipe = new Recipe(recipeName);
        newestRecipe.setDescription(instructions);
        newestRecipe.setTotalTime(time);
        newestRecipe.setImageURL(url);
        newestRecipe.setImage(imageNameEdit.getText());
        RecipeData.getInstance().deleteRecipe(Controller.recipeChosen);
        RecipeData.getInstance().addRecipe(newestRecipe);


    }
    @FXML
    public void chooseImage() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Pick an image for this recipe");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );

        File file = chooser.showOpenDialog(editDialogPane.getScene().getWindow());
        if(file!= null){
            String url = file.getPath();
            imageNameEdit.setText(url);
            System.out.println(url);
            Image newImage = new Image("file:"+url);
            addImage.setImage(newImage);


        }else{
            System.out.println("Cancel was pressed");

        }

    }

    public void setImage(){
        String imageURL = myRecipe.getImageURL();
        System.out.println(imageURL);
        if(imageURL != null){
            Image newImage = new Image(imageURL);
            addImage.setImage(newImage);
        }
    }


    @FXML
    public void setName(String name){

        newRecipeNameEdit.setText(name);
    }

    @FXML
    public void checkForDisable() {
        boolean flag;

        if (newRecipeInstructionsEdit.getText() == null ||
                newRecipeNameEdit.getText() == null ||
                newRecipeTimeEdit.getText() == null);
        {

        }

    }
}
