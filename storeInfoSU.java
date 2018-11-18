/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstoremanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class storeInfoSU implements Initializable {

    @FXML
    private Label lbld;
    @FXML
    private Label lblid;
    @FXML
    private Label lbladmin;
    @FXML
    private Label lblname;
    @FXML
    private Label lblpar;
    
    @FXML
    private ListView<?> listinventory;

    /**
     * Initializes the controller class.
     */
    
    String f;
    
    public void setf(String r){
        f=r;
        
        try{
        connectivity c=new connectivity();
        String sql = "select * from storeadmins where Storename = '"+f+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        
        while(c.rs.next()){
            lblid.setText(""+c.rs.getInt("ID"));
            lblname.setText(f);
        
            //lblname.setText(""+c.rs.getString("Username"));
            lbladmin.setText(""+c.rs.getString("Username"));
            lbld.setText("");
            lblpar.setText(""+c.rs.getInt("ParwareID"));
           
        }
        
    }catch (Exception e){
        
    }
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
