package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class LoginController {
	
	    private Stage stage;
	    private Scene scene;
	    private Parent root;
		
	    @FXML
	    private Label LogLabel;
	 
	    @FXML
	    private Button Logout;
	    
	    @FXML
	    private Button Cart;
	    
	    @FXML
	    private Label label;
		
		@FXML
		private Button Orders;
	    
	

   	public void displayName(String Name) {
		
		LogLabel.setText("Welcome "+Name);
		
	}
   	
    public void fruits(ActionEvent event) throws IOException, SQLException {
    	
    	String FileId="Fruits.fxml";
    	openStage(event,FileId);
    }
    
   public void ataRice(ActionEvent event) throws IOException, SQLException {
 
	   String FileId="AtaRice.fxml";
       openStage(event,FileId);
    }
    
     public void beverages (ActionEvent event) throws IOException, SQLException {
   		
    	 String FileId="Beverages.fxml";
         openStage(event,FileId);
    }
 
      public void snackes(ActionEvent event) throws IOException, SQLException {
		
     	 String FileId="Snacks.fxml";
         openStage(event,FileId);
 }
      
      public void kitchen(ActionEvent event) throws IOException, SQLException {
  		
      	 String FileId="Home&Kitchen.fxml";
          openStage(event,FileId);
  }
     
      public void Household_Cleaning(ActionEvent event) throws IOException, SQLException {
    		
       	 String FileId="Household&Cleaning.fxml";
           openStage(event,FileId);
   }      
      public void orderStatus(ActionEvent event) throws IOException, SQLException {
   	   
   	   String FileId="Order Status.fxml";
          openStage(event,FileId);    
      }
    
      public void openStage(ActionEvent event,String FileId) throws IOException, SQLException {
  	  	
      	FXMLLoader loader = new FXMLLoader(getClass().getResource(FileId));
        root=loader.load();
  	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
  	    
  	    switch(FileId) {
  	     
                                  
  	    case "Cart.fxml":     CartController cartcontroller=loader.getController();
                               cartcontroller.displayCart(event);
                               break;
                              
  	    case "Order Status.fxml":     OrderStatusController orderStatuscontroller=loader.getController();
	                                  orderStatuscontroller.displayOrderStatus(event);    
                                      break;                       
  	    
  	    }
  	    scene=new Scene(root);
  	    stage.setScene(scene);  
  	    stage.show();
      }
      
    public void goToCart(ActionEvent event) throws IOException, SQLException {
    	
    	Connection connection=null;
    	PreparedStatement history=null;
    	
 	    String FileId="Cart.fxml";
        openStage(event,FileId);
        
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
        String sql="INSERT INTO javafx.history(History"+stage.getTitle()+ ") VALUES('Login.fxml');";
        history=connection.prepareStatement(sql);
        history.execute();
    }
    
   public void Logout(ActionEvent event) throws IOException, SQLException {
   		
   		 Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
   		 alert.setTitle("Logout");
   		 alert.setHeaderText("You are about to logout!");
   		 if(alert.showAndWait().get()== ButtonType.OK) {
   			 
   		 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   		 
	     PreparedStatement createColumn;
	     Connection connection=null;
	     connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
	     String sql="ALTER TABLE javafx.history DROP History"+stage.getTitle();
	     createColumn=connection.prepareStatement(sql);
	     createColumn.executeUpdate();
	     
	     PreparedStatement loginStatus;
	     String sql2="UPDATE javafx.`database table` SET LogIn_Status=null WHERE Username=?;";
         loginStatus=connection.prepareStatement(sql2);
         loginStatus.setString(1, stage.getTitle());
	     loginStatus.executeUpdate();
	    
	     shoppingMain shoppingMain=new shoppingMain();
  		 shoppingMain.start(stage);
   		}
   }

}