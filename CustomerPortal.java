/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstoremanagement;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class CustomerPortal implements Initializable {

    @FXML
    private Label Luid;
    @FXML
    private TextField Txtuname;
    @FXML
    private Label Lfunds;
    @FXML
    private ListView<String> liststores;
    @FXML
    private Button btnaddfunds;
    @FXML
    private Button btnselstore;
    @FXML
    private Button btnviewcart;
    @FXML
    private TextField txtfunds;
    @FXML
    private TextField txtstore;
    @FXML
    private Button btnlogout;
    @FXML
    private SplitPane painfulpane;
    @FXML
    private Button btnFind;
    @FXML
    private Button btnsort;
    
    private int funfun;
    private Stage s;

    
     private int ID;
    
    public void getID (int id){
        this.ID=id;
        
        Luid.setText(""+ID);
        
         connectivity conn = new connectivity();
       String sql; 
        try{
            
        sql="create table c"+ID+" (Item VARCHAR(30), Cost INTEGER, Quantity INTEGER, Total INTEGER, Store VARCHAR (30) )";
        conn.st.executeUpdate(sql);    
            
            
            
            System.out.println(sql);
            
        }catch (Exception e){
            System.out.println(e);
        }
        
        
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Lfunds.setText("");
        funfun=0;
        
        List<String> a=new ArrayList<String>();
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select * from storeadmins";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Storename");
            a.add(g);
            
        }
                
        
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        liststores.setItems(observableList);
        
        
//        observableList =listWare.getItems();
//        System.out.println(observableList);
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
        }
        
        
        
     
        
        
        
        
        // TODO
    }    
    
    
    
    public void getstage(Stage s){
        this.s=s;
    }
    

    @FXML
    private void clickAddFunds(ActionEvent event) {
        try{
            
            funfun+=Integer.parseInt(txtfunds.getText());
            Lfunds.setText(""+funfun);
            
        }catch(Exception e){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid: "+e);
            a.show();
        }
        
    }

    @FXML
    private void clickSelectStore(ActionEvent event) {
        
        String r=liststores.getSelectionModel().getSelectedItem();
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("subcategory_1.fxml").openStream());
        
            subcategory_1 out = new subcategory_1();
            out=fxml.getController();
            out.getname(r,r);
            out.getid(ID);
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
    private void clickFindStore(ActionEvent event) {
        
         String r=txtstore.getText();
         
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("possiblestorelist.fxml").openStream());
        
            possiblestorelist out = new possiblestorelist();
            out=fxml.getController();
            out.setset(r, ID);
            
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
    private void clickviewCart(ActionEvent event) {
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("Cart.fxml").openStream());
        
            Cart out = new Cart();
            out=fxml.getController();
            
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        out.setset(ID,funfun,stage);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void clickLogOut(ActionEvent event) {
        
          
        connectivity conn = new connectivity();
       String sql; 
        try{
            
        sql="drop table c"+ID ;
        conn.st.executeUpdate(sql);    
            
            
            
            System.out.println(sql);
            
        }catch (Exception e){
            System.out.println(e);
        }
        
        s.close();
    }

    @FXML
    private void cllicktosort(ActionEvent event) {
        List<String> a=new ArrayList<String>();
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select * from storeadmins";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Storename");
            a.add(g);
            
        }
                
        
        Collections.sort(a);
        ObservableList<String> observableList = FXCollections.observableList(a);
        liststores.setItems(observableList);
        
        
//        observableList =listWare.getItems();
//        System.out.println(observableList);
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
        }
        
        
    }
    

    
    
 
    }