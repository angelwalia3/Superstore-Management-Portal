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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SuperuserPortal implements Initializable {

    @FXML
    private ListView<String> listWare;
    @FXML
    private ListView<String> listStore;
    @FXML
    private Button btnSelW;
    @FXML
    private Button btnSSel;
    @FXML
    private Button btnWConst;
    @FXML
    private Button btnSConst;
    @FXML
    private Button btnLogOut;
    @FXML
    private AnchorPane anchorSU;
    @FXML
    private Button btndropware;
    @FXML
    private Button dropstore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        List<String> h= new ArrayList<String>();
        sql = "select * from wareadmins";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Warehousename");
            h.add(g);
            
        }
        
        
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        listStore.setItems(observableList);
        ObservableList<String> observableList2 = FXCollections.observableList(h);
        listWare.setItems(observableList2);
        
//        observableList =listWare.getItems();
//        System.out.println(observableList);
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
        }
        
        
        
    }    

    String r;

    /**
     * this method works when the button select is clicked. After selecting a warehouse from the list view, this method opens up a warehouse info. page where all info. relevant to the superuser is displayed.
     * @param event
     */
    @FXML
    private void clickWSel(ActionEvent event) {
        r=listWare.getSelectionModel().getSelectedItem();
        //System.out.println(r);
        
        
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("Warehosueinfo.fxml").openStream());
        
            Warehosueinfo out = new Warehosueinfo();
            out=fxml.getController();
            out.f=r;
            out.setf(r);
            
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
     *  this method works when the button select is clicked. After selecting a store from the list view, this method opens up a store info. page where all info. relevant to the superuser is displayed.
     * @param event
     */
    @FXML
    private void clickStoreSel(ActionEvent event) {
        r=listStore.getSelectionModel().getSelectedItem();
        
       
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("storeInfoSU.fxml").openStream());
        
            storeInfoSU out = new storeInfoSU();
            out=fxml.getController();
            out.f=r;
            out.setf(r);
            
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
     * When warehouse construction has been intructed by the SU, then this opens a new window where all the details of the warehouse and its admin can be entered for construction.
     * @param event
     */
    @FXML
    private void clickWareConstruction(ActionEvent event) {
        
        try{
         
            
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Warehouseconstruction.fxml"));
                    anchorSU.getChildren().setAll(pane);         

        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
    }

    /**
     * When store construction has been intructed by the SU, then this opens a new window where all the details of the store and its admin can be entered for construction.
     * @param event
     */
    @FXML
    private void clickStroeConstruct(ActionEvent event) {
        try{
         
            
        AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreConstuct.fxml"));
                    anchorSU.getChildren().setAll(pane);         

        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
    }

    /**
     * It activates when log out is clicked. it logs u out as a super user and takes u back to the main login page.
     * @param event
     */
    @FXML
    private void clickLogOut(ActionEvent event) {
        try{
                   
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("SuperUserLogin.fxml"));
                    anchorSU.getChildren().setAll(pane);         

            }
       catch (Exception ex){
                    System.out.println("Can't close this window!");
                    ex.printStackTrace();
            }
    }

    /**
     * After selecting a warehouse from the list-view, if u click delete, this method deletes that warehouse for the superstore along with its admin.
     * @param event
     */
    @FXML
    private void droptableware(ActionEvent event) {
        r=listWare.getSelectionModel().getSelectedItem();
        String sql="drop table "+r;
        String sql2="delete from wareadmins where Warehousename='"+r+"'";
        connectivity c=new connectivity();
        List<String> a=new ArrayList<String>();
        try{
            c.st.executeUpdate(sql);
            c.st.executeUpdate(sql2);
            String g;
        sql = "select * from wareadmins";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Warehousename");
            a.add(g);
                       
        }
        ObservableList<String> observableList = FXCollections.observableList(a);
        listWare.setItems(observableList);
        }catch(Exception e){
            System.out.println(e);
            
        }
        
    }

    /**
     *  After selecting a store from the list-view, if u click delete, this method deletes that store for the superstore along with its admin.* @param event
     */
    @FXML
    private void droptablestore(ActionEvent event) {
        
        
        r=listStore.getSelectionModel().getSelectedItem();
        String sql="drop table "+r;
        String sql2="delete from storeadmins where Storename='"+r+"'";
        connectivity c=new connectivity();
        List<String> a=new ArrayList<String>();
        try{
            c.st.executeUpdate(sql);
            c.st.executeUpdate(sql2);
            String g;
        sql = "select * from storeadmins";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Storename");
            a.add(g);
                       
        }
        ObservableList<String> observableList = FXCollections.observableList(a);
        listStore.setItems(observableList);
        }catch(Exception e){
            System.out.println(e);
            
        }
        
    }
    
}
