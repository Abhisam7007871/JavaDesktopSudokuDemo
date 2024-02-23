package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class SudokuApplication extends Application {
    private IUserInterfaceContract.ViewuiImpl;


    public void start(Stage primaryStage) throws Exception{
        uiImpl = new UserInterfaceImpl(primaryStage);
        try{
            SudokuBuildLogic.build(uiImpl);
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] arga){
        launch(args);
    }


}