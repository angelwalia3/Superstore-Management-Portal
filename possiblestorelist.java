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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class possiblestorelist implements Initializable {

    @FXML
    private ListView<String> liststores;
    @FXML
    private Button btnselstroe;

    private String pn;
    private int id;
    
    public void setset(String h, int id){
        pn=h; this.id=id;
        
        
         String r=pn;
        List<String> a=new ArrayList<String>();
        
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select * from storeadmins where Storename like '%"+r+"%'";
        System.out.println(sql);
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Storename");
            a.add(g);
            
        }
                
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        liststores .setItems(observableList);
             
        
        
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
        
        
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        
        
       
        
        
    }    

    @FXML
    private void clickToSelect(ActionEvent event) {
        String r=liststores.getSelectionModel().getSelectedItem();
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("subcategory_1.fxml").openStream());
        
            subcategory_1 out = new subcategory_1();
            out=fxml.getController();
            out.getname(r,r);
            out.getid(id);
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
    
}
