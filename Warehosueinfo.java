/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstoremanagement;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class Warehosueinfo implements Initializable {

    @FXML
    private AnchorPane papaanchor;
    @FXML
    private Label lblid;
    @FXML
    private Label lblname;
    @FXML
    private Label lbladmin;
    @FXML
    private ListView<String> listitem;
    @FXML
    private ListView<String> liststore;
    
    String f;
    public void setf(String r){
        f=r;
        List<String> a=new ArrayList<String>();
        try{
        connectivity c=new connectivity();
        String sql = "select * from wareadmins where Warehousename = '"+f+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        
        while(c.rs.next()){
            lblid.setText(""+c.rs.getInt("ID"));
            lblname.setText(f);
        
            //lblname.setText(""+c.rs.getString("Username"));
            lbladmin.setText(""+c.rs.getString("Username"));
           
        }
        
        
        String g;
        sql = "select * from storeadmins where ParwareID ="+lblid.getText();
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Storename");
            a.add(g);
            
        }
        List<String> h= new ArrayList<String>();
        sql = "select * from "+f;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Item");
            h.add(g);
            
        }
        
        
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        liststore.setItems(observableList);
        ObservableList<String> observableList2 = FXCollections.observableList(h);
        listitem.setItems(observableList2);
        
        
        
        
        
    }catch (Exception e){
        
    }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
    }    
    
}
