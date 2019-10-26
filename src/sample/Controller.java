package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.RecipeClasses.Recipe;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    @FXML
    private Label answer;
    @FXML
    private TextField ourText;
    @FXML
    private ImageView centerImage;
    @FXML
    private Label recipe;
    @FXML
    protected ListView<Recipe> leftListView;
    @FXML
    private TextArea mainTextArea;
    @FXML
    private Label recipeName;
    @FXML
    private Button AddCupboardButton;
    @FXML
    private Button RecipeButton;
    @FXML
    private Button PrintRecipeButton;
    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private ContextMenu listContext;
    @FXML
    private Label recipeTime;
    @FXML
    private ToggleButton showAllToggle;
    @FXML
    private Label searchTitle;
    @FXML
    protected static ButtonType buttonOK;
    @FXML
    private Button editRecipe;
    @FXML
    private TextArea ingredientsText;
    @FXML
    private Button deleteRecipe;

    private List<Recipe> recipeList;
    private FilteredList<Recipe> filteredList;
    private Predicate<Recipe> allRecipes;

    private Predicate<Recipe> chickenRecipe;
    public static Recipe recipeChosen;

    public void initialize() {

        mainTextArea.setEditable(false);
        ingredientsText.setEditable(false);
        listContext = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Recipe deleteRec = leftListView.getSelectionModel().getSelectedItem();
                deleteItem();
            }
        });
        listContext.getItems().addAll(delete);


        allRecipes = new Predicate<Recipe>() {
            @Override
            public boolean test(Recipe recipe) {
                return true;
            }
        };

        chickenRecipe = new Predicate<Recipe>() {
            @Override
            public boolean test(Recipe recipe) {
                return (recipe.getName().equalsIgnoreCase("Chicken Pot Pie"));
            }
        };

        filteredList = new FilteredList<Recipe>(RecipeData.getInstance().getRecipes(), allRecipes);

        SortedList<Recipe> sortedList = new SortedList<Recipe>(filteredList,
                new Comparator<Recipe>() {
                    @Override
                    public int compare(Recipe o1, Recipe o2) {
                        return (o1.getTotalTime()).compareTo(o2.getTotalTime());
                    }
                });


//        leftListView.setItems(RecipeData.getInstance().getRecipes());

        leftListView.setItems(sortedList);
        leftListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        leftListView.setCellFactory(new Callback<ListView<Recipe>, ListCell<Recipe>>() {
            @Override
            public ListCell<Recipe> call(ListView<Recipe> param) {
                ListCell<Recipe> tempRecipe = new ListCell<Recipe>() {
                    @Override
                    protected void updateItem(Recipe item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                            String string = "Chicken Pot Pie";
                            if (item.getName().equalsIgnoreCase(string)) {
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };

                tempRecipe.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                tempRecipe.setContextMenu(null);
                            } else {
                                tempRecipe.setContextMenu(listContext);
                            }

                        });
                return tempRecipe;
            }
        });


    }


    @FXML
    public void handleLeftListClick() {


        //Creates a temporary recipe object which points to whatever the mouse clicks on from the left list view
        if (leftListView.getSelectionModel().getSelectedItem() != null) {
            Recipe selected = (Recipe) leftListView.getSelectionModel().getSelectedItem();
            System.out.println("This worked");
            recipeName.setText(selected.getName());
            recipeTime.setText("Total Time: " + selected.getTotalTime());
            StringBuilder recipeDescrip = new StringBuilder(selected.printIngredientsNoName());
            recipeDescrip.append("\n");
            recipeDescrip.append(selected.getDescription());
            mainTextArea.setText(recipeDescrip.toString());
            setCenterImage();
            recipeChosen = selected;
            editRecipe.setDisable(false);
            deleteRecipe.setDisable(false);
        }

    }

    @FXML
    public void deleteRecipe() {

    }


    public void changeAnswer() {
        String searchable = ourText.getText();
        if (searchable != null) {
            Recipe selected = RecipeData.getInstance().findRecipe(searchable);
            if (selected != null) {
                Predicate<Recipe> samplePredicate = new Predicate<Recipe>() {
                    @Override
                    public boolean test(Recipe recipe) {
                        return (recipe.getName().equalsIgnoreCase(searchable));
                    }
                };
                filteredList.setPredicate(samplePredicate);
                if (filteredList.isEmpty()) {
                    mainTextArea.clear();
                } else {
                    leftListView.getSelectionModel().select(selected);
                    handleLeftListClick();
                }

            } else {
                filteredList.setPredicate(allRecipes);
                leftListView.getSelectionModel().selectFirst();
                handleLeftListClick();
                searchTitle.setText("Search for a recipe : None Found!");

            }
        } else if (searchable.isEmpty()) {
            filteredList.setPredicate(allRecipes);
            leftListView.getSelectionModel().selectFirst();
            handleLeftListClick();
        } else {
            System.out.println("Couldn't find it");
        }

    }

    public void deleteItem() {
        Recipe item = leftListView.getSelectionModel().getSelectedItem();
        //This creates a new dialog box that will confirm that you want to delete this item
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Recipe");
        confirm.setHeaderText("Delete " + item.getName() + ".");
        confirm.setContentText(item.getName() + " will be deleted.");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            RecipeData.getInstance().deleteRecipe(item);
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        Recipe recipe = leftListView.getSelectionModel().getSelectedItem();
        if (recipe != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteItem();
            }
        }
    }

    @FXML
    public void showAllRecipesToggle() {
        Recipe selected = leftListView.getSelectionModel().getSelectedItem();
        if (showAllToggle.isSelected()) {
            filteredList.setPredicate(allRecipes);
            if (filteredList.isEmpty()) {
                mainTextArea.clear();
                recipeName.setText("");
                recipeTime.setText("");
            } else if (filteredList.contains(selected)) {
                leftListView.getSelectionModel().select(selected);
                handleLeftListClick();
            } else {
                leftListView.getSelectionModel().selectFirst();
            }
        } else {
            filteredList.setPredicate(allRecipes);
            leftListView.getSelectionModel().select(selected);

        }
    }

    @FXML

    public void searching(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            changeAnswer();
        }

    }

    @FXML
    public void setCenterImage() {
        String imageURL = leftListView.getSelectionModel().getSelectedItem().getImageURL();
        if (imageURL != null) {
            Image newImage = new Image(imageURL);
            centerImage.setImage(newImage);
        }

    }

    @FXML
    public void AddNewRecipeDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(MainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Recipe");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("createRecipeUser.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Couldnt do it");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            controller.processResults();
            System.out.println("Ok Pressed");
        } else {
            System.out.println("Cancel pressed");
        }
    }


    @FXML
    public void editRecipeDialog() {


        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(MainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editRecipeUser.fxml"));


        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());


        } catch (IOException e) {
            System.out.println("Couldnt do it");
            e.printStackTrace();
            return;
        }
        EditRecipeController controller = fxmlLoader.getController();

        buttonOK = ButtonType.OK;

        dialog.getDialogPane().getButtonTypes().add(buttonOK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        Optional<ButtonType> result = dialog.showAndWait();



            if (result.isPresent() && result.get() == buttonOK) {

                    controller.processResults();
                    System.out.println("Ok Pressed");
                    leftListView.getSelectionModel().selectFirst();

            } else {
                System.out.println("Cancel pressed");
            }

    }

    public Recipe getLeftListView(){

        return (leftListView.getSelectionModel().getSelectedItem());
    }


}
