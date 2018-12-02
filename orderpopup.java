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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class orderpopup implements Initializable {

    @FXML
    private Label lbltext;
    @FXML
    private Button btnfulfill;
    @FXML
    private Button btnignore;
    @FXML
    private Button btnforward;

    /**
     * Initializes the controller class.
     */
    private String warename,item,store;
            private int qty;
    private Stage stage;
    public void setf(String w, String i, String s, int q, Stage stage){
        warename=w;
        this.stage=stage;
        item=i;
        store=s;
        qty=q;
        String f="Order sent by "+store+". Item- "+item+". Quantity- "+qty+".";
        lbltext.setText(f);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tofulfill(ActionEvent event) {
        try{
        connectivity c=new connectivity();
        String sql = "select * from "+warename+" where Item = '"+item+"'";
        int avail=0;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            avail=c.rs.getInt("Quantity");
            
            if(avail>=qty){
                connectivity x=new connectivity();
                sql="update "+warename+" set Quantity = "+(avail-qty) +" where Item = '"+item+"'";
                x.st.executeUpdate(sql);
                System.out.println(sql);
                sql="update "+store+" set Quantity = "+qty +" where Item = '"+item+"'";
                x.st.executeUpdate(sql);
                System.out.println(sql);
                sql="insert into ack (SendTo,Item,Quantity) values('"+store+"','"+item+"',"+qty+") ";
                x.st.executeUpdate(sql);
                System.out.println(sql);
               
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Fulfilled!");
                a.show();
                stage.close();
                 sql="delete from storetowareorders where Store = '"+store+"'";
                 x.st.executeUpdate(sql);
                sql="delete from waretoware where OrderedBy = '"+store+"'";
                x.st.executeUpdate(sql);
            }else{
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setContentText("Cant fulfill not enough!");
                a.show();
            }
            
            
           
        }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void toignore(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void toforward(ActionEvent event) {
        
        try{
        connectivity c=new connectivity();
        String sql = "insert into waretoware (OrderedBy,Item,Quantity) values('"+store+"','"+item+"',"+qty+") ";
        
        c.st.executeUpdate(sql);
                
       sql="delete from storetowareorders where Store = '"+store+"'";
                 c.st.executeUpdate(sql);
                sql="delete from waretoware where OrderedBy = '"+store+"'";
                c.st.executeUpdate(sql);     
            
           
        
        }catch(Exception e){
        System.out.println(e);
        }
        
       stage.close(); 
    }
    
}
