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

public class SnacksController {
	
	@FXML
	private Button cart;
	
	@FXML
	private Button Menu;

    
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	ServiceImpl service=new ServiceImpl();

      public void addToCartPopcorn (ActionEvent event) {   	  
    	String ItemName="Popcorn";
		service.addItemsToCart(ItemName,event);
}
	       
	  public  void addToCartKind(ActionEvent event) {		  
	    	String ItemName="Kind";
			service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartKurkure(ActionEvent event) {		  
		    String ItemName="Kurkure";
			service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartFreekyFries(ActionEvent event) {		  
		    String ItemName="Freeky Fries";
			service.addItemsToCart(ItemName,event);
	    }
	  
	  public void addTocartHideSeek(ActionEvent event) {
		    String ItemName="Hide&Seek";
			service.addItemsToCart(ItemName,event);		  
	  }
	  
	  public void addToCartMinis(ActionEvent event) {		  
		    String ItemName="Minis";
			service.addItemsToCart(ItemName,event);
	  }	  
	  
	  public void addToCartLays(ActionEvent event) {		  
		    String ItemName="Lay's";
			service.addItemsToCart(ItemName,event);
	  }	  
	  public void addToCartLaysStax(ActionEvent event) {		  
		    String ItemName="Lay's Stax";
			service.addItemsToCart(ItemName,event);
	  }
	  public void addToCartKitKat(ActionEvent event) {		  
		    String ItemName="KitKat";
			service.addItemsToCart(ItemName,event);
	  }
	  
  public void goToCart(ActionEvent event) throws IOException, SQLException {
	  
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
	  service.goToCart(event,loader,"Snacks.fxml" );
	  
}

  public void goToMenu(ActionEvent event) throws IOException {
	
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    service.viewStage(event, loader);
}
}