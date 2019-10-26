package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import sample.RecipeClasses.Recipe;

import java.io.File;

/**
 * Created by nickg on 4/11/2017.
 */
public class DialogController {
    @FXML
    private TextField newRecipeName1;

    @FXML
    private TextArea newRecipeInstructions1;

    @FXML
    private TextField newRecipeTime;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField imageName;

    @FXML
    private Button addAnImage;

    @FXML
    private ImageView addImage;



    public void processResults(){
        String recipeName = newRecipeName1.getText().trim();
        String instructions  = newRecipeInstructions1.getText().trim();
        int time = Integer.parseInt(newRecipeTime.getText().trim());
        Recipe newestRecipe = new Recipe(recipeName);
        newestRecipe.setDescription(instructions);
        newestRecipe.setTotalTime(time);
        newestRecipe.setImage("file:" + imageName.getText());
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

        File file = chooser.showOpenDialog(dialogPane.getScene().getWindow());
        if(file!= null){
            String url = file.getPath();
            imageName.setText(url);
            System.out.println(url);
            Image newImage = new Image("file:"+url);
            addImage.setImage(newImage);


        }else{
            System.out.println("Cancel was pressed");
        }

    }


}
