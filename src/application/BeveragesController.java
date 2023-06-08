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

public class BeveragesController {
	
	@FXML
	private Button cart;
	
	@FXML
	private Button Menu;
    
		 
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	ServiceImpl service=new ServiceImpl();

      public void addToCartPepsi (ActionEvent event) {   	  
    	String ItemName="Pepsi";
    	service.addItemsToCart(ItemName,event);
}
	       
	  public  void addToCart7UP(ActionEvent event) {		  
	    	String ItemName="7UP";
	    	service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartMazza(ActionEvent event) {		  
		    String ItemName="Mazza";
		    service.addItemsToCart(ItemName,event);		  
	    }
	  
	  public  void addToCartSprite(ActionEvent event) {		  
		    String ItemName="Sprite";
		    service.addItemsToCart(ItemName,event);
	    }
	  
	  public void addTocartCocaCola(ActionEvent event) {
		    String ItemName="CocaCola";
		    service.addItemsToCart(ItemName,event);	  
	  }
	  
	  public void addToCartLimca(ActionEvent event) {		  
		    String ItemName="Limca";
		    service.addItemsToCart(ItemName,event);
	  }	  
	  
	  public void addToCartBlackPepsi(ActionEvent event) {		  
		    String ItemName="Black Pepsi";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
	  public void addToCartMirinda(ActionEvent event) {		  
		    String ItemName="Mirinda";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
	  public void addToCartThumsUp(ActionEvent event) {		  
		    String ItemName="ThumsUp";
		    service.addItemsToCart(ItemName,event);
	  }	
	  
  public void goToCart(ActionEvent event) throws IOException, SQLException {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
	service.goToCart(event,loader,"Beverages.fxml" );
}

  public void goToMenu(ActionEvent event) throws IOException {
	
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	service.viewStage(event,loader );
}
}