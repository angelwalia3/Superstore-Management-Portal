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
 * @author Angel & Medha
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

    /**
     * establishes connectibility with database and creates tables. Sets initial label/button texts.
     * @param id customer id
     */
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


    /**
     * sets current stage.
     * @param s
     */
    public void getstage(Stage s){
        this.s=s;
    }


    /**
     * As the button add funds is pressed, the value of the label is incremented by the same amount and customer has more funds.
     * @param event Button click
     */
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

    /**
     * When you select a store to browse through its contents, it opens a new window showing necessary info.
     * @param event Button click
     */
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

    /**
     * The find store method finds the store with the highest matching characters we have entered as input and displays it on the store list.
     * @param event Button click
     */
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

    /**
     * This provides a way to view the unique cart associated to each customer. A new Cart window/Stage is opened with necessary information.
     * @param event Button click
     */
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

    /**
     * Logs the current customer out of the data base. Main login page reappears.
     * @param event
     */
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

    /**
     * when u click sort, the stores list view is sorted aphabetically and displayed. this function works according to the event-clicking sort button.
     * @param event
     */
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