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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class Iteminfoforadmins implements Initializable {

    @FXML
    private AnchorPane backpane;
    @FXML
    private Label lblid;
    @FXML
    private Label lblname;
    @FXML
    private Label lblcat;
    @FXML
    private Label lblsubcat;
    @FXML
    private Label lblprice;
    @FXML
    private Label lblqty;
    @FXML
    private Label lblh;
    @FXML
    private Label lblK;
    @FXML
    private Button btnunits;
    @FXML
    private TextField txtunits;
    @FXML
    private Button btncost;
    @FXML
    private TextField txtcost;

    
    private String exti;
    private String extw;
    private int own;
    public void getname(String item,String ware,int own){
        exti=item;
        extw=ware;
        this.own=own;
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select * from "+ware+" where item = '"+item +"'";
        c.rs=c.st.executeQuery(sql);
        c.rs.next();
                lblid.setText(""+c.rs.getInt("ID"));
                lblname.setText(c.rs.getString("Item"));
                lblcat.setText(c.rs.getString("Cat"));
                lblsubcat.setText(c.rs.getString("Subcat"));
                lblprice.setText(""+c.rs.getString("Cost"));
                lblqty.setText(""+c.rs.getInt("Quantity"));
                lblh.setText(""+c.rs.getInt("H"));
                lblK.setText(""+c.rs.getInt("K"));
                        
        if(own==0){
            btnunits.setDisable(true);
            btncost.setDisable(true);
        }
        
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
        }
        
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateunits(ActionEvent event) {
        int units = Integer.parseInt(txtunits.getText());
        
        connectivity c=new connectivity();
        try{
        
        String sql = "update "+extw+" set Quantity = "+units +" where Item = '"+exti +"'";
        c.st.executeUpdate(sql);
        lblqty.setText(Integer.toString(units));
        }
        catch(Exception e){
            System.out.println("Cant due to : "+e);
            
        }
        
    }

    @FXML
    private void updatecost(ActionEvent event) {
        int cost = Integer.parseInt(txtcost.getText());
        
        connectivity c=new connectivity();
        try{
        
        String sql = "update "+extw+" set Cost = "+cost +" where Item = '"+exti +"'";
        c.st.executeUpdate(sql);
        lblprice.setText(Integer.toString(cost));
        }
        catch(Exception e){
            System.out.println("Cant due to : "+e);
        }
    }
    
}
