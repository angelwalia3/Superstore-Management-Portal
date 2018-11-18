
package superstoremanagement;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author Pallavi
 */
public class connectivity {
    
    public Connection conn;
    public Statement st;
    public PreparedStatement pst;
    public ResultSet rs;
    
    
    
    public connectivity(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/superstore","root","");
            st=conn.createStatement();
        }catch(Exception e){
           System.out.println("Unable to connect! Oopsie!"); 
        }
    }

    
    
}
