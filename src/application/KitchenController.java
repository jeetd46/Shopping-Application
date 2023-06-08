package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Service.ServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

 public class KitchenController {
	
	@FXML
	private Button cart;
	
	@FXML
	private Button Menu;

		 
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	ServiceImpl service=new ServiceImpl();

      public void addToCartMObCleaner (ActionEvent event) {   	  
    	String ItemName="Mob Cleaner";
    	service.addItemsToCart(ItemName,event);
}
	       
	  public  void addToCartPan(ActionEvent event) {		  
	    	String ItemName="pan";
	    	service.addItemsToCart(ItemName,event);
	    }
	  
	  public  void addToCartKnife(ActionEvent event) {		  
		    String ItemName="Knife";
		    service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartGasLight(ActionEvent event) {		  
		    String ItemName="Gas Light";
		    service.addItemsToCart(ItemName,event);
	    }
	  
	  public void addTocartContainer(ActionEvent event) {
		    String ItemName="Container";
		    service.addItemsToCart(ItemName,event);		  
	  }
	  
	  public void addToCartBakeware(ActionEvent event) {		  
		    String ItemName="Bakeware";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
	  public void addToCartSteelUtensils(ActionEvent event) {		  
		    String ItemName="Steel Utensils";
		    service.addItemsToCart(ItemName,event);
	  }
	  
	  public void addToCartSteelContainer(ActionEvent event) {		  
		    String ItemName="Steel Container";
		    service.addItemsToCart(ItemName,event);
	  }	

	  public void addToCartPlates(ActionEvent event) {		  
		    String ItemName="Plates";
		    service.addItemsToCart(ItemName,event);
	  }	  
	    
  public void goToCart(ActionEvent event) throws IOException, SQLException {
	  
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
	service.goToCart(event,loader,"Home&Kitchen.fxml" );
}

  public void goToMenu(ActionEvent event) throws IOException {
	
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	 service.viewStage(event,loader );
}
}
