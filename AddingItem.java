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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class for adding an item
 *
 * @author Angel and Medha
 */

public class AddingItem implements Initializable {

    @FXML
    private Button btnok;
    @FXML
    private TextField txtitem;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField txtsubcat;
    @FXML
    private TextField txtcost;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txth;
    @FXML
    private TextField txtk;
    @FXML
    private Button btncancel;

    /**
     * Initializes the controller class.
     */
    
    
    String h;
    @FXML
    private AnchorPane backpane;

    /**
     * updates current parameter
     * @param h the parameter to which current h should be set to
     */
    public void getname( String h){
        this.h=h;
        
    }


    /**
     *  method necessary for initialization
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Describes the functionality that occurs when the event of clicking the OK button takes place. It adds an item by the admins with the desired characteristics.
     * @param event describes the button click here
     */
    @FXML
    private void clickOK(ActionEvent event) {
        
        connectivity conn = new connectivity();
        int x=0,chk=0;
        try{
       
        String sql = "select * from "+h;
        conn.rs=conn.st.executeQuery(sql);
        while(conn.rs.next()){
            x=conn.rs.getInt("ID");
            
        }
       chk=0;        
        x++;
        sql="insert into "+h+" (ID,Item,Cat,Subcat,Cost,Quantity,H,K) "
                + "values ("+x+",'"+txtitem.getText()+"','"+txtcat.getText()+"','"+txtsubcat.getText()+
                "',"+txtcost.getText()+","+ txtqty.getText()+","+txth.getText()+","+txtk.getText()+") ";
        System.out.println(sql);
        conn.st.executeUpdate(sql);
        
        
        
        
    }catch(Exception e){
        chk=1;
        System.out.println(e);
      
    }finally{
            if(chk==1){
                Alert s= new Alert(Alert.AlertType.ERROR);
        s.setTitle("Error");
        s.setContentText("Invalid request: "+x);
        s.show();
            }
            else{Alert s= new Alert(Alert.AlertType.INFORMATION);
        s.setTitle("New ID");
        s.setContentText("New Item ID: "+x);
        s.show();
                
            }
        }
        
        
    }

    /**
     * Describes what happens when the cancel button is clicked. The control is taken back to the previous portal.
     * @param event  the action of clicking cancel button here
     */
    @FXML
    private void clickcancel(ActionEvent event) {
        
        
        
        try{
            FXMLLoader fxml=new FXMLLoader();
                    SplitPane pane = fxml.load(getClass().getResource("WareAdminPortal.fxml").openStream());
                    backpane.getChildren().setAll(pane);
                    //entername                                     
                   WareAdminPortal n=new WareAdminPortal();
                   n=fxml.getController();
                   n.setname(h);

            
            
        }catch(Exception e){
            
        }
        
        
        
    }
    
}
