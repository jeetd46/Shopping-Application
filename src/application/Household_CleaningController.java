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

public class Household_CleaningController {
	
	@FXML
	private Button cart;
	
	@FXML
	private Button Menu;

    
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	ServiceImpl service=new ServiceImpl();

      public void addToCartHarpic (ActionEvent event) {   	  
    	String ItemName="Harpic";
    	service.addItemsToCart(ItemName,event);
}
	       
	  public  void addToCartTide(ActionEvent event) {		  
	    	String ItemName="Tide";
	    	service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartIzol(ActionEvent event) {		  
		    String ItemName="Izol";
		    service.addItemsToCart(ItemName,event);	  
	    }
	  
	  public  void addToCartRin(ActionEvent event) {		  
		    String ItemName="Rin";
		    service.addItemsToCart(ItemName,event);
	    }
	  
	  public void addTocartSunlight(ActionEvent event) {
		    String ItemName="Sunlight";
		    service.addItemsToCart(ItemName,event);		  
	  }
	  
	  public void addToCartScotchBrite(ActionEvent event) {		  
		    String ItemName="Scotch brite";
		    service.addItemsToCart(ItemName,event);
	  }	  
	  
	  public void addToCartDetol(ActionEvent event) {		  
		    String ItemName="Detol";
		    service.addItemsToCart(ItemName,event);
	  }	  
	  public void addToCartVimBar(ActionEvent event) {		  
		    String ItemName="Vim Bar";
		    service.addItemsToCart(ItemName,event);
	  }
	  public void addToCartVanish(ActionEvent event) {		  
		    String ItemName="Vanish";
		    service.addItemsToCart(ItemName,event);
	  }
	   
  public void goToCart(ActionEvent event) throws IOException, SQLException {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
	service.goToCart(event,loader,"Household&Cleaning.fxml" );
}

  public void goToMenu(ActionEvent event) throws IOException {
	
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	service.viewStage(event,loader );
}
}
