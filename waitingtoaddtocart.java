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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */

public class waitingtoaddtocart implements Initializable {

    
    
    
    
    @FXML
    private Label lblid;
    @FXML
    private Label lblname;
    @FXML
    private Label lblcat;
    @FXML
    private Label lblsub;
    @FXML
    private Label lblprice;
    @FXML
    private Button btnok;
    @FXML
    private Button btncancel;
    @FXML
    private TextField txtqty;

    /**
     * Initializes the controller class.
     */
    
    private String store,item;
    private int ID;
    private Stage stage;
    
    public void setset(String store, int ID, Stage stage,String item){
        this.ID=ID;
        this.stage=stage;
        this.store=store;
        this.item=item;
        
        
        connectivity c = new connectivity();
        int g=0;
        try{
            
        String sql ;
        sql = "select * from "+store+" where  item = '"+item+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
             lblid.setText(""+c.rs.getInt("ID"));
             lblname.setText(c.rs.getString("Item"));
             lblcat.setText(c.rs.getString("Cat"));
             lblsub.setText(c.rs.getString("Subcat"));
             lblprice.setText(""+c.rs.getInt("Cost"));

            
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
    private void clickok(ActionEvent event) {
        
        
        connectivity c = new connectivity();
        int g=0;
        try{
            int qty=Integer.parseInt(txtqty.getText());
        String sql ;
        sql = "select * from "+store+" where  item = '"+item+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
             g=c.rs.getInt("Cost");
            
            
        }
                
             
        sql="insert into c"+ID+" (Item,Cost,Quantity,Total,Store) "+"values "
                + "('"+item+"',"+g+","+Integer.parseInt(txtqty.getText())+","+g*Integer.parseInt(txtqty.getText())+",'"+store +"') ";
        System.out.println(sql);
        c.st.executeUpdate(sql);
        
        stage.close();    
        
    }catch(Exception e){
        
        Alert a=new Alert(Alert.AlertType.ERROR);
        a.setContentText("Not happening bruh: "+e);
        a.show();
        
        }
    
    }

    @FXML
    private void clickcancel(ActionEvent event) {
        stage.close();
    }
    
}
