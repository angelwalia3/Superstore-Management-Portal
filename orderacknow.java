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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class orderacknow implements Initializable {

    @FXML
    private Button btnok;
    @FXML
    private Label lblonly;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     private String warename,item;
            private int qty;
    private Stage stage;
    public void setf(String w, String i, int q, Stage stage){
        warename=w;
        this.stage=stage;
        item=i;
        
        qty=q;
        
        String ok="You have received "+item+". Quantity: "+qty;
        lblonly.setText(ok);
    }
    
    
    @FXML
    private void clickok(ActionEvent event) {
        
        try{
            connectivity c= new connectivity();
        String sql = "delete from ack where SendTo = '"+warename+"'";
        System.out.println(sql);
        c.st.executeUpdate(sql);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
        
        stage.close();
    }
    
}
