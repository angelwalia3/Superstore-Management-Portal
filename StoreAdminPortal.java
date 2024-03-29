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
    public void setname(String k){
        lblownname.setText(k);
        h=k;
    connectivity c = new connectivity();
    
    try{
            
         String sql = "select * from ack where SendTo = '"+h+"'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        if(c.rs.next()==true){
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("orderacknow.fxml").openStream());
            String item=c.rs.getString("Item");
            int qty =c.rs.getInt("Quantity");
            orderacknow out = new orderacknow();
            out=fxml.getController();
            
            
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        out.setf(h,item,qty,stage);
        stage.show();
            
        }
                  
        /*   
            
        String g;
        sql = "select * from storetowareorders";
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            if(c.rs.getInt("WareID")==hello){
                String store=c.rs.getString("Store");
                String item=c.rs.getString("Item");
                int qty = c.rs.getInt("Quantity");
                
                
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("orderpopup.fxml").openStream());
        
            orderpopup out = new orderpopup();
            out=fxml.getController();
            
            
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        out.setf(h,item,store,qty,stage);
        stage.show();
            
        
        
        
                
            }
            
            
            
            
        }
        
        */
        }
        catch(Exception e){
            System.out.println("Cant . just cant : "+e);
        }
        
    }    

    @FXML
    private void clickDispOwnInfo(ActionEvent event) {
        String r= lblownname.getText();
        
        
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
