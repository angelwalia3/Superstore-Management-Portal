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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class WareAdminPortal implements Initializable {

    @FXML
    private SplitPane painfulpane;
    @FXML
    private Label lblown;
    @FXML
    private Button btndisp;
    @FXML
    private TextField txtitemname;
    @FXML
    private TextField txtitemtodisp;
    @FXML
    private TextField txttoorder;
    @FXML
    private Button btnOwnInfo;
    @FXML
    private Button btnseeitem;
    @FXML
    private TextField txtitemunits;
    @FXML
    private ListView<String> listware;
    @FXML
    private Button btnbrowsewarehouses;
    @FXML
    private Button btnlogout;

    /**
     * Initializes the controller class.
     */
    
    String h;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button del;
    public void setname(String k){
        lblown.setText(k);
        h=k;
        List<String> a=new ArrayList<String>();
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select * from wareadmins";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Warehousename");
            a.add(g);
            
        }
        
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        listware.setItems(observableList);
        
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
    private void clickDispOwnInfo(ActionEvent event) {
        String r= lblown.getText();
        
        
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

    @FXML
    private void clickToSeeItem(ActionEvent event) {
        
       
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("Iteminfoforadmins.fxml").openStream());
        
            Iteminfoforadmins out = new Iteminfoforadmins();
            out=fxml.getController();
           out.getname(txtitemtodisp.getText(),h,1);
            
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


    @FXML
    private void clickToBrowseWorld(ActionEvent event) {
        String r=listware.getSelectionModel().getSelectedItem();
        
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
        }
        
        
    }

    @FXML
    private void clickLogOut(ActionEvent event) {
        try{
                
                    FXMLLoader fxml=new FXMLLoader();
                    AnchorPane pane = fxml.load(getClass().getResource("warehouseadminlogin.fxml").openStream());
                   // painfulpane.getChildrenUnmodifiable().setAll(pane);
                    painfulpane.getItems().setAll(pane);
                    //entername                                     
                   
                }
            catch (Exception ex){
                    System.out.println("Can't open even this window!");
                    ex.printStackTrace();
                }
        
    }

    @FXML
    private void click(ActionEvent event) {
        String r= lblown.getText();
        
        
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

    @FXML
    private void additem(ActionEvent event) {
        try{
            FXMLLoader fxml=new FXMLLoader();
            
            AnchorPane pane = fxml.load(getClass().getResource("AddingItem.fxml").openStream());
                    
            AddingItem out = new AddingItem();
            out=fxml.getController();
            out.getname(h);
            
            painfulpane.getItems().setAll(pane);

            
            
        }catch(Exception e){
            
        }
    }

    @FXML
    private void clickToUpdate(ActionEvent event) {
        
        int units = Integer.parseInt(txtitemunits.getText());
        // txtitemname
        int chk=0;
        connectivity c=new connectivity();
        try{
        
            String sql = "select * from "+h+" where Item='"+txtitemname.getText()+"'";
        c.rs=c.st.executeQuery(sql);
        c.rs.next(); chk=1;
            String name=c.rs.getString("Item");
            String password=c.rs.getString("Quantity");
            
            
        sql = "update "+h+" set Quantity = "+units +" where Item = '"+txtitemname.getText() +"'";
        c.st.executeUpdate(sql);
         
        chk=1;
        }
        catch(Exception e){
            System.out.println("Cant form list due to : "+e);
            chk=0;
            
        }finally{
            if(chk==0){
                Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid Search");
            a.show();
                            }
            else{
                Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Item Updated!");
            a.show();
            }
        }
        
    }

    @FXML
    private void clickToDelete(ActionEvent event) {
        
        
        int chk=0;
        connectivity c=new connectivity();
        try{
            
        String sql = "select * from "+h+" where Item='"+txttoorder.getText()+"'";
        c.rs=c.st.executeQuery(sql);
        c.rs.next(); chk=1;
            String name=c.rs.getString("Item");
            String password=c.rs.getString("Quantity");
            
            
        
       sql = "delete from "+h+" where Item = '"+txttoorder.getText() +"' ";
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
