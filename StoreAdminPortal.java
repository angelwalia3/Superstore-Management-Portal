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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class StoreAdminPortal implements Initializable {

    @FXML
    private SplitPane painfulpane;
    @FXML
    private Button btninfo;
    @FXML
    private Label lblownname;
    @FXML
    private Button browse;
    @FXML
    private Button disp;
    @FXML
    private TextField txttodisp;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtunits;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnlog;
    @FXML
    private Button btnadd;
    @FXML
    private Button btndel;
    @FXML
    private TextField txtdelname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    String h;

    /**
     * sets initial labels names
     * @param k the label name to be used
     */
    public void setname(String k){
        lblownname.setText(k);
        h=k;}


    /**
     * Use this method and button when u want to display the information of the store you're a store admin for. a new window showing information pops up.
     * @param event
     */
    @FXML
    private void dispowninfo(ActionEvent event) {
        String r= h;
        
        // incomplete
        
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
     * on clicking the browse store button, a new window is popped, allowing you to browse through the categories,sub-categories and items of your own store. It also allows you to make changes in your store's inventory there.
     * @param event
     */
    @FXML
    private void browseStore(ActionEvent event) {
        
        String r= lblownname.getText();
        
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("subcategory.fxml").openStream());
        
            subcategory out = new subcategory();
            out=fxml.getController();
            out.getname(r,h);
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Incorrect Action Requested!");
            a.show();
        }
        
    }

    /**
     * When u click display button, it opens a new item information window for admins where u can view item properties.
     * @param event
     */
    @FXML
    private void clickDisp(ActionEvent event) {
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("Iteminfoforadmins.fxml").openStream());
        
            Iteminfoforadmins out = new Iteminfoforadmins();
            out=fxml.getController();
           out.getname(txttodisp.getText(),h,1);
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
            Alert s= new Alert(Alert.AlertType.ERROR);
        s.setTitle("Error");
        s.setContentText("Invalid request: "+e);
        s.show();
        }
    }

    /**
     * After entering the desired name and units for the item, this method simply intructs the store admin to add such an item in its store's inventory.
     * @param event
     */
    @FXML
    private void clickUpdate(ActionEvent event) {
        int units = Integer.parseInt(txtunits.getText());
        // txtitemname
        int chk=0;
        connectivity c=new connectivity();
        try{
            
        String sql = "select * from "+h+" where Item='"+txtname.getText()+"'";
        c.rs=c.st.executeQuery(sql);
        c.rs.next(); chk=1;
            String name=c.rs.getString("Item");
            String password=c.rs.getString("Quantity");
            
            
        
       sql = "update "+h+" set Quantity = "+units +" where Item = '"+txtname.getText() +"'";
        c.st.executeUpdate(sql);
         
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
            chk=0;
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Search");
            a.show();
            
        }finally{
            if(chk==1){
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Item Updated!");
            a.show();
            }
        }
        
        
    }

    /**
     * When u click log out, this method closes the current window and takes u back to the main login window.
     * @param event
     */
    @FXML
    private void clickLogOut(ActionEvent event) {
        try{
                
                    FXMLLoader fxml=new FXMLLoader();
                    AnchorPane pane = fxml.load(getClass().getResource("StoreAdminLogin.fxml").openStream());
                   // painfulpane.getChildrenUnmodifiable().setAll(pane);
                    painfulpane.getItems().setAll(pane);
                    //entername                                     
                   
                }
            catch (Exception ex){
                    System.out.println("Can't open even this window!");
                    ex.printStackTrace();
                }
        
        
    }

    /**
     * When u enter an items name, this method first check if that item exists. if it does, exceptions are thrown. if not, then it adds the new item in its own store.
     * @param event
     */
    @FXML
    private void clickToAdd(ActionEvent event) {
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            
            AnchorPane pane = fxml.load(getClass().getResource("AddingItem_1.fxml").openStream());
                    
            AddingItem_1 out = new AddingItem_1();
            out=fxml.getController();
            out.getname(h);
            
            painfulpane.getItems().setAll(pane);

            
            
        }catch(Exception e){
            
        }
    }

    /**
     * When the delete button is clicked in the screen, it first checks if such an item exists in the store. if no, it shows appropriate exceptions. If yes, the  it proceeds to delete that items data from its own store.
     * @param event
     */
    @FXML
    private void btnDelete(ActionEvent event) {
        
        // txtitemname
        int chk=0;
        connectivity c=new connectivity();
        try{
            
        String sql = "select * from "+h+" where Item='"+txtdelname.getText()+"' or where Subcat='"+txtdelname.getText()+"' or where Cat='"+txtdelname.getText()+"'";
        c.rs=c.st.executeQuery(sql);
        c.rs.next(); chk=1;
            String name=c.rs.getString("Item");
            String password=c.rs.getString("Quantity");
            
            
        
       sql = "delete from "+h+" where Item = '"+txtdelname.getText() +"'";
        c.st.executeUpdate(sql);
         System.out.println(sql);
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
            chk=0;
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Operation! Item Doesnot Exist!");
            a.show();
            
        }finally{
            if(chk==1){
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Item Deleted!");
            a.show();
            }
        }
        
    }
    
}
