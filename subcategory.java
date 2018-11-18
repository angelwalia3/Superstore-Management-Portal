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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class subcategory implements Initializable {

    @FXML
    private AnchorPane backpane;
    @FXML
    private Button btnsel;
    @FXML
    private Label lbltochange;
    @FXML
    private ListView<String> catlist;
    @FXML
    private ListView<String> subcatlist;
    @FXML
    private ListView<String> itemlist;
    @FXML
    private Button btnselsub;
    @FXML
    private Button btnview;

    /**
     * Initializes the controller class.
     */
    
    private String h,ow;
    @FXML
    private Button btnsortcat;
    @FXML
    private Button btnsortsub;
    @FXML
    private Button btnsortitem;
    
    
    public void getname(String s,String own){
        h=s;
        ow=own;
        List<String> a=new ArrayList<String>();
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select distinct Cat from "+s;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Cat");
            a.add(g);
            
        }
                
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        catlist.setItems(observableList);
        
        
//        observableList =listWare.getItems();
//        System.out.println(observableList);
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cat(ActionEvent event) {
        String r=catlist.getSelectionModel().getSelectedItem();
        List<String> a=new ArrayList<String>();
        List<String> b=new ArrayList<String>();
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select distinct Subcat from "+h+" where Cat = '"+r+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Subcat");
            a.add(g);
            
        }
                
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        subcatlist.setItems(observableList);
        ObservableList<String> observableList2 = FXCollections.observableList(b);
        itemlist.setItems(observableList2);
        
        
        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void subcat(ActionEvent event) {
        
        String r=subcatlist.getSelectionModel().getSelectedItem();
        List<String> a=new ArrayList<String>();
        
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select distinct item from "+h+" where Subcat = '"+r+"'";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Item");
            a.add(g);
            
        }
                
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        itemlist.setItems(observableList);
             
        
        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void item(ActionEvent event) {
        String r=itemlist.getSelectionModel().getSelectedItem();
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("Iteminfoforadmins.fxml").openStream());
        
            Iteminfoforadmins out = new Iteminfoforadmins();
            out=fxml.getController();
            if(h.equals(ow)){
                out.getname(r,h,1);
            } else  out.getname(r,h,0);
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void tosortcat(ActionEvent event) {
    }

    @FXML
    private void tosortsub(ActionEvent event) {
    }

    @FXML
    private void tosortitem(ActionEvent event) {
    }

    
    
}
