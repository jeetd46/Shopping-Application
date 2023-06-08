package Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.CartController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ServiceImpl {
	
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	  public void addItemsToCart(String ItemName,ActionEvent event) {

	  		try {
	  			int i=1;
	  			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	  			String Titleid=stage.getTitle();
	  			
	  			while(i<7) {
	  				
	  			String sql="SELECT * FROM javafx.cart WHERE Username=?;";
	  			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
	  			checkItem=connection.prepareStatement(sql);
	  			checkItem.setString(1,Titleid);
	  		    resultset= checkItem.executeQuery();
	  		    
	  		    if(resultset.next()) {
	  		    	String Item=resultset.getString("Item"+i);
	  		       if(Item!=null) {
	  		    	   if(Item.equals(ItemName)) {
	  		    		   
	  		    		    Alert alert=new Alert(Alert.AlertType.INFORMATION);
	  					    alert.setContentText("Already in the Cart");
	  					    alert.show();
	  		    		   
	  		    		   break;
	  		    	   }
	  		    	   i++;
	  		        } 	
	  		        else {
	  				    String sql1="UPDATE javafx.cart SET Item" +i + " = ? WHERE Username=?;";
	  				    item=connection.prepareStatement(sql1);
	  				    item.setString(1,ItemName);
	  				    item.setString(2,Titleid);
	  				    item.executeUpdate();
	  				    
	  				    Alert alert=new Alert(Alert.AlertType.INFORMATION);
	  				    alert.setContentText("Item added to Cart");
	  				    alert.show();
	  				    break;
	  		        }
	  		    
	  		  }
	  	}
	  			if(i>=7) {
	  				
	  			    Alert alert=new Alert(Alert.AlertType.ERROR);
	  			    alert.setContentText("Sorry the Cart is Full.");
	  			    alert.show();
	  			}
	                  
	  		}catch(SQLException e) {
	  			e.printStackTrace();
	  		     }
	  }	
	  public void goToCart(ActionEvent event,FXMLLoader loader,String pageName) throws IOException, SQLException {
		  
			PreparedStatement history=null;
		    root=loader.load();
		    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			String Titleid=stage.getTitle();
		    
		    CartController controller=loader.getController();
		    controller.displayCart(event);
		    
		    scene=new Scene(root);
		    stage.setScene(scene);
			stage.show();
			
		    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
		    String sql="INSERT INTO javafx.history(History"+Titleid+") VALUES(?);";
		    history=connection.prepareStatement(sql);
		    history.setString(1, pageName);
		    history.executeUpdate();
			  
		}
	  public void viewStage(ActionEvent event,FXMLLoader loader) throws IOException {
			
		    root=loader.load();
		    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		    scene=new Scene(root);
		    stage.setScene(scene);
			stage.show();
		}
	  	  	
}
