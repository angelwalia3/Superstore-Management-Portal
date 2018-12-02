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
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Angel & Medha
 */
public class StoreAdminLogin implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private Button btnEnter;
    @FXML
    private PasswordField txtPass;
    @FXML
    private AnchorPane painfulpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * When you enter your credentials and click login, which is the actionevent for this method, it first verifies the credentials. If they matcg, it takes you to the store admin portal, otherwise shows an exception window.
     * @param event
     */
    @FXML
    private void clickEnter(ActionEvent event) {
        
        
        String entername=txtName.getText();
        String enterpass=txtPass.getText();
        connectivity conn=new connectivity();
        
        
        
        try{
        
        String sql="select * from storeadmins where Username = '"+entername+"' and Password = '"+enterpass+"'";
        //System.out.println(sql);
        conn.rs=conn.st.executeQuery(sql);
        
        
            
            if(conn.rs.next() && conn.rs.getString("Username").equals(entername) && 
                    conn.rs.getString("Password").equals(enterpass )){
            
            try{
                
                    FXMLLoader fxml=new FXMLLoader();
                    SplitPane pane = fxml.load(getClass().getResource("StoreAdminPortal.fxml").openStream());
                    painfulpane.getChildren().setAll(pane);
                    //entername                                     
                   StoreAdminPortal n=new StoreAdminPortal();
                   n=fxml.getController();
                   n.setname(conn.rs.getString("Storename"));
            
                           
                    
                    

               

                }
            catch (Exception ex){
                    System.out.println("Can't open even this window!");
                    ex.printStackTrace();
                }
            
            
            
        }else{
                System.out.println("ALERT!");
            Alert s=new Alert(Alert.AlertType.ERROR);
            s.setTitle("Oopsie!");
            s.setContentText("The Username/Password is incorrect. Please Try Again. If the issue persists please contact the SuperUser.");
            s.show();
            }
            
        
        
        
        
        
    }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Oopsie!");
            a.setContentText("Unable to connect. Face the following Error: "+e.toString());
            a.show();
        }
        
        
        
    }
    
}
