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

public class AtaRiceController {
	
	
	@FXML
	private Button cart;
	
	@FXML
	private Button Menu;
    
		 
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	ServiceImpl service=new ServiceImpl();

      public void addToCartAashirwadAtta (ActionEvent event) {   	  
    	String ItemName="Aashirwad Atta";
    	service.addItemsToCart(ItemName,event);
}
	       
	  public  void addToCartDalRicePowder(ActionEvent event) {		  
	    	String ItemName="Dal Rice Powder";
	    	service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartNewtrapoorna(ActionEvent event) {		  
		    String ItemName="Newtrapoorna";
		    service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartGoldMultiGrain(ActionEvent event) {		  
		    String ItemName="Gold Multi Grain";
		    service.addItemsToCart(ItemName,event);
	    }
	  
	  public void addTocartChakkiAtta(ActionEvent event) {
		    String ItemName="Chakki Atta";
		    service.addItemsToCart(ItemName,event);		  
	  }
	  
	  public void addToCartMuligaanAtta(ActionEvent event) {		  
		    String ItemName="Muligaan Atta";
		    service.addItemsToCart(ItemName,event);
	  }
	  
	  public void addToCartMothersMaida(ActionEvent event) {		  
		    String ItemName="Mother's Maida";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
	  public void addToCartBlackWheat(ActionEvent event) {		  
		    String ItemName="Black Wheat";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
	  public void addToCartChapatiflour(ActionEvent event) {		  
		    String ItemName="Chapati flour";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
	  
  public void goToCart(ActionEvent event) throws IOException, SQLException {
	  	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
	service.goToCart(event,loader,"AtaRice.fxml" );
}

  public void goToMenu(ActionEvent event) throws IOException {
	
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	service.viewStage(event,loader );
}
}