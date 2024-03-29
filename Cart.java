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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pallavi
 */
public class Cart implements Initializable {

    @FXML
    private Label lbltotal;
    @FXML
    private Label lblfund;
    @FXML
    private Button btnchk;
    @FXML
    private Button btnback;
    @FXML
    private ListView<String> itemlist;
    @FXML
    private Button btnremove;
    @FXML
    private Button btnview;

    /**
     * Initializes the controller class.
     */
    
    private int id,funds;
    private Stage stage;
    
    public void setset(int id, int funds, Stage stage){
        this.id=id;
        this.funds=funds;
        this.stage=stage;
        
        lblfund.setText(""+funds);
        
        
        List<String> a=new ArrayList<String>();
        connectivity c=new connectivity();
        try{
        String g;
        String sql = "select * from c"+id;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Item");
            a.add(g);
            
        }
              
        
        ObservableList<String> observableList = FXCollections.observableList(a);
        itemlist.setItems(observableList);
        int k=0;
        sql="select sum(Total) from c"+id;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            k=c.rs.getInt("sum(Total)");
            
        }
        
        lbltotal.setText(""+k);

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
    private void clicktocheckout(ActionEvent event) {
         int finalmoney=0;
        connectivity c=new connectivity();
        
        try{
        int DedSQL=10;        
        String sql = "select * from c"+id;
        c.rs=c.st.executeQuery(sql);
        int D=DedSQL;
        while(c.rs.next()){
            String g =c.rs.getString("Store");
            String h=c.rs.getString("Item");
            int t= c.rs.getInt("Quantity");
            int money=c.rs.getInt("Total");
            connectivity x=new connectivity();
            int id2=0;
            sql="select * from storeadmins where Storename= '"+g+"'";
            x.rs=x.st.executeQuery(sql);
            System.out.println(sql);
            while(x.rs.next()){
                id2 =x.rs.getInt("ParwareID");
            }
            
            sql="select * from "+g+" where Item= '"+h+"'";
            x.rs=x.st.executeQuery(sql);
            System.out.println(sql);
            while(x.rs.next()){
                
                connectivity l=new connectivity();
                int j= x.rs.getInt("Quantity");
                
                int H=x.rs.getInt("H");
                int K=x.rs.getInt("K");
                if(j<t){
                    //out of stock
                    Alert a= new Alert(Alert.AlertType.ERROR);
                    String okok="Item "+h+" is out of stock. Quantity not sufficient.";
                    a.setContentText(okok);
                    a.show();
                }else if(funds<money) {
                    Alert a= new Alert(Alert.AlertType.ERROR);
                    String okok="Not Enough Funds!";
                    a.setContentText(okok);
                    a.show();
                }
                else{
                    int lol=j-t;
                    sql="update "+g+" set Quantity ="+lol+" where Item = '"+h+"'";
                    System.out.println(sql);
                    l.st.executeUpdate(sql);
                    sql="delete from c"+id+" where Item = '"+h+"'";
                    System.out.println(sql);
                    l.st.executeUpdate(sql);
                    funds-=money;
                    Alert a=new Alert(Alert.AlertType.INFORMATION);
                    String purchase="Purcase of "+h+" successful. Thanks for shopping with us";
                    a.setContentText(purchase);
                    a.show();
                    if(lol==0){
                        int quant= (2*D*K)/H;
                        quant=(int) Math.sqrt(quant);
                        sql="insert into storetowareorders (Store,WareID,Item,Quantity)"
                                + " values ('"+g+"',"+id2+",'"+h+"',"+quant+")";
                        System.out.println(sql);
                        l.st.executeUpdate(sql);
                    }
                }
                
            }
            
           
           
            
           
        }
              
        lblfund.setText(""+funds);
        setset(id,funds,stage);
        
        }
        catch(Exception e){
            System.out.println("Cant due to : ");
            e.printStackTrace();
        }
        
        
        
        
        
        
        
    }

    @FXML
    private void clicktogoback(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void clicktoremove(ActionEvent event) {
        
        String r= itemlist.getSelectionModel().getSelectedItem();
        String sql;
        String sql2="delete from c"+id+" where Item='"+r+"'";
        connectivity c=new connectivity();
        List<String> a=new ArrayList<String>();
        try{
            
            c.st.executeUpdate(sql2);
            String g;
        sql = "select * from c"+id;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            g=c.rs.getString("Item");
            a.add(g);
                       
        }
        ObservableList<String> observableList = FXCollections.observableList(a);
        itemlist.setItems(observableList);
        int k=0;
        sql="select sum(Total) from c"+id;
        c.rs=c.st.executeQuery(sql);
        while(c.rs.next()){
            k=c.rs.getInt("sum(Total)");
            
        }
        
        lbltotal.setText(""+k);
        
        
        }catch(Exception e){
            System.out.println(e);
            
        }
    }

    @FXML
    private void clicktoview(ActionEvent event) {
        
        String r = itemlist.getSelectionModel().getSelectedItem();
        
        
        try{
            FXMLLoader fxml=new FXMLLoader();
            Parent root1 =fxml.load(getClass().getResource("cartitemview.fxml").openStream());
        
            cartitemview out = new cartitemview();
            out=fxml.getController();
            
            
        Scene scene = new Scene(root1);
        Stage stage=new Stage();
        stage.setScene(scene);
        out.setset(id,r,stage);
        stage.show();
            
        }
        catch (Exception e){
            System.out.println("Can't open window!");
            e.printStackTrace();
        }
        
        
        
        
    }
    
}
