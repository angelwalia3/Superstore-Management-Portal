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
public class cartitemview implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private Label lblitem;
    @FXML
    private Label lblcost;
    @FXML
    private Label lblqty;
    @FXML
    private Label lbltotal;

    /**
     * Initializes the controller class.
     */
    
    private int id;
    private String item;
    private Stage stage;
    
    
    public void setset(int id, String item, Stage stage){
        this.id=id;
        this.item=item;
        this.stage=stage;
        
        connectivity c = new connectivity();
        int g=0;
        try{
            
        String sql ;
        sql = "select * from c"+id+" where  item = '"+item+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
             lblcost.setText(""+c.rs.getInt("Cost"));
             lblqty.setText(""+c.rs.getInt("Quantity"));
             lblitem.setText(c.rs.getString("Item"));
             lbltotal.setText(""+c.rs.getInt("Total"));
             

            
        }
                
          
        
    }catch(Exception e){
        
        System.out.println(e);
        
        }
        
        
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicktoback(ActionEvent event) {
        stage.close();
    }
    
}
