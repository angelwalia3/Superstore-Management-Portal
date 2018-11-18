/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstoremanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class MainLogin implements Initializable {
    
    

    @FXML
    private Button btnSuper;
    @FXML
    private Button btnWare;
    @FXML
    private Button btnStore;
    @FXML
    private Button btnGuest;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void superClick(ActionEvent event) {
        try{
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("SuperUserLogin.fxml"));
            Parent root1 = (Parent) fxml.load();
        
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void wareClick(ActionEvent event) {
        try{
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("warehouseadminlogin.fxml"));
            Parent root5 = (Parent) fxml.load();
        
        Scene scene = new Scene(root5);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open even this window!");
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void storeClick(ActionEvent event) {
        try{
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("StoreAdminLogin.fxml"));
            Parent root2 = (Parent) fxml.load();
        
        Scene scene = new Scene(root2);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open even this window!");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void clickUser(ActionEvent event) {
        try{
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("CustomerPortal.fxml"));
            Parent root4 = (Parent) fxml.load();
        
        Scene scene = new Scene(root4);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open even this window!");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void clickExit(ActionEvent event) {
        
        Platform.exit();
        
    }
    
}
