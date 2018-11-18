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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private Label Lstoreid;
    @FXML
    private ListView<?> liststores;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickAddFunds(ActionEvent event) {
    }

    @FXML
    private void clickSelectStore(ActionEvent event) {
    }

    @FXML
    private void clickFindStore(ActionEvent event) {
    }

    @FXML
    private void clickviewCart(ActionEvent event) {
    }

    @FXML
    private void clickLogOut(ActionEvent event) {
    }
    
}
