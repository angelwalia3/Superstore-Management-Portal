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
public class Warehouseconstruction implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtwarename;
    @FXML
    private TextField txtusername;
    @FXML
    private Button btncreate;
    @FXML
    private TextField txtpass;
    @FXML
    private Button cancleinsertion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createwarehouse(ActionEvent event) {
        connectivity conn = new connectivity();
        int x=0;
        try{
       
        String sql = "select * from wareadmins";
        conn.rs=conn.st.executeQuery(sql);
        while(conn.rs.next()){
            x=conn.rs.getInt("ID");
            
        }
        
        
    
        
        x++;
        sql="insert into wareadmins (ID,Username,Password,Warehousename) "
                + "values ("+x+",'"+txtusername.getText()+"','"+txtpass.getText()+"','"+txtwarename.getText()+"') ";
        System.out.println(sql);
        conn.st.executeUpdate(sql);
        
        sql="create table "+txtwarename.getText()+" (ID INTEGER, Item VARCHAR(30), Cat VARCHAR(30),"
                + " Subcat VARCHAR(30),Cost INTEGER, Quantity INTEGER, H INTEGER, K INTEGER )";
        conn.st.executeUpdate(sql);
        
        Alert s= new Alert(Alert.AlertType.INFORMATION);
        s.setTitle("New ID");
        s.setContentText("New Warehouse Admin ID: "+x);
        s.show();
        
        
    }catch(Exception e){
        System.out.println(e);
    }
        
        
        
        
        
        
        
        
         try{
         
            
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("SuperUserportal.fxml"));
                    pane.getChildren().setAll(pane1);         

        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
    }

    @FXML
    private void ToCancelInsert(ActionEvent event) {
         try{
         
            
        AnchorPane pane1 = FXMLLoader.load(getClass().getResource("SuperUserportal.fxml"));
                    pane.getChildren().setAll(pane1);         

        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
    }
    
}
