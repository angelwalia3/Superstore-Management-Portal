/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstoremanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class StoreConstuct implements Initializable {

    @FXML
    private AnchorPane back;
    @FXML
    private TextField txtstorename;
    @FXML
    private TextField txtpass;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtparid;
    @FXML
    private Button btnNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * This method takes the values of the four inputs entered, and based on that creates a new store under the mentioned store admin (store admin is also created), with the given details about admin ID Password and store's name ,etc.
     * @param event
     */
    @FXML
    private void clickCreateStore(ActionEvent event) {
        
        connectivity conn = new connectivity();
        int x=0;
        try{
       
        String sql = "select * from storeadmins";
        conn.rs=conn.st.executeQuery(sql);
        while(conn.rs.next()){
            x=conn.rs.getInt("ID");
            
        }
        int j=0;
       sql = "select * from wareadmins";
        conn.rs=conn.st.executeQuery(sql);
        while(conn.rs.next()){
            j=conn.rs.getInt("ID");
            
        }
        int chk=Integer.parseInt(txtparid.getText());
        if(j>=chk){
            x++;
        sql="insert into storeadmins (ID,Username,Password,Storename,ParwareID) "
                + "values ("+x+",'"+txtusername.getText()+"','"+txtpass.getText()+"','"+txtstorename.getText()+"',"+txtparid.getText()+") ";
        
        
        
        
        
        
        System.out.println(sql);
        conn.st.executeUpdate(sql);
        
        sql="create table "+txtstorename.getText()+" (ID INTEGER, Item VARCHAR(30), Cat VARCHAR(30),"
                + " Subcat VARCHAR(30),Cost INTEGER, Quantity INTEGER, H INTEGER, K INTEGER )";
        conn.st.executeUpdate(sql);
        
        Alert s= new Alert(Alert.AlertType.INFORMATION);
        s.setTitle("New ID");
        s.setContentText("New Store Admin ID: "+x);
        s.show();
            
        }
        else{
            Alert f=new Alert(Alert.AlertType.ERROR);
            f.setContentText("Warehouse ID non Existent!");
            f.show();
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("SuperUserportal.fxml"));
                    back.getChildren().setAll(pane1);      
        }
    
        
        
        
        
    }catch(Exception e){
        System.out.println("Something Bad Happened! :: "+e);
        
    }
        
        
        
        
        
        
        
        
         try{
         
            
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("SuperUserportal.fxml"));
                    back.getChildren().setAll(pane1);         

        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
        
    }

    /**
     * clicking on the cancel button will close the current window and take you back to the previous super user portal.
     * @param event
     */
    @FXML
    private void clicktocancel(ActionEvent event) {
        try{
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("SuperUserportal.fxml"));
                    back.getChildren().setAll(pane1); 
        }catch(Exception e){
            System.out.println("NO!");
        }
        
    }
    
}
