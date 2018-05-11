package Ping;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Text output;
    @FXML private TextField ip1,ip2,ip3,ip4;

    @FXML
     private Text ipAddress, INFO;
     @FXML
     private MenuItem tesztmenu;
     
    private Model model = new Model();
    
    @FXML
    private void processNumpad(ActionEvent event) {
        
    	String Comm = ((Button)event.getSource()).getText();
    	ipAddress.setText(model.Muvelet(ip1, ip2, ip3, ip4, Comm));
         
    }

    
    @FXML
    private void processOperator(ActionEvent event) throws IOException {
    	String value = ((Button)event.getSource()).getText();
      model.Windows(value);
    
    }
    @FXML
    private void information(ActionEvent event) throws SQLException
    {
    	String Comm = ((Button)event.getSource()).getText();
    	INFO.setText(model.kveri(Comm));
    }
    @FXML
    private void processDrop() throws SQLException
    {
    	dbsqlconnect con=new dbsqlconnect();
    	con.drop();
    	con.create();
    	con.close();
    }
}