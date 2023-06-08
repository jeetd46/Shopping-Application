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

public class FruitsController {
	
	@FXML
	private Button cart;
	
	@FXML
	private Button Menu;

	@FXML
	private Button addMango;

    @FXML
    private Button addPineapple;
    
    @FXML
    private Button addStrawbery;

    @FXML
    private Button addWatermelon;
    
    @FXML
    private Button addCherry;
    
    @FXML
    private Button addBananna;
	
		 
	Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement checkItem=null;
	ResultSet resultset =null;
	ServiceImpl service=new ServiceImpl();

      public void addToCartMango (ActionEvent event) {		
    	  String ItemName="Mango";
    	  service.addItemsToCart(ItemName,event);
}
	  
	  public  void addToCartStrawbery(ActionEvent event) {		  
		  String ItemName="Strawbery";
		  service.addItemsToCart(ItemName,event);  
	    }
	  
	  public  void addToCartWatermelon(ActionEvent event) {
		  String ItemName="Watermelon";
		  service.addItemsToCart(ItemName,event);
	    }
	  
	  public  void addToCartPineapple(ActionEvent event) {		  
		  String ItemName="Pineapple";
		  service.addItemsToCart(ItemName,event);
	    }
	  
	  public void addTocartBananna(ActionEvent event) {
		  String ItemName="Bananna";
		  service.addItemsToCart(ItemName,event);  
	  }
	  
	  public void addToCartCherry(ActionEvent event) {
		  
		  String ItemName="Cherry";
		  service.addItemsToCart(ItemName,event);
	  }	  
	  
  public void goToCart(ActionEvent event) throws IOException, SQLException {

	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
	service.goToCart(event,loader,"Fruits.fxml" );
}

  public void goToMenu(ActionEvent event) throws IOException {
	
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	service.viewStage(event,loader );
}

}