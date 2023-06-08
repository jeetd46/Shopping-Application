package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Service.ServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class OrderStatusController {	

	private Stage stage;

    @FXML
    private Button Menu;

    @FXML
    public Label displayNamelbl;
    
    @FXML
    private Button cart;
    
    @FXML
    private Button cancel;
    
    @FXML
    private ListView<String> orderStatusList;

    @FXML
    private ListView<String> itemList;

    @FXML
    private ListView<Integer> priceList;
    
    @FXML
    private ListView<Integer> orderIdList;

    Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement update=null;
	ResultSet resultset=null;
	
	ServiceImpl service=new ServiceImpl();
	
	ObservableList<String> itemlist=FXCollections.observableArrayList();
	ObservableList<Integer> pricelist=FXCollections.observableArrayList();
	ObservableList<String> orderStatuslist=FXCollections.observableArrayList();
	ObservableList<Integer> orderIdlist=FXCollections.observableArrayList();
	
    

    public void goToMenu(ActionEvent event) throws IOException {
    	
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
          service.viewStage(event, loader);

    }
    
    public void goToCart(ActionEvent event) throws IOException, SQLException {
    	   	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
  	    service.goToCart(event,loader,"Order Status.fxml" );
    }

     public void displayOrderStatus(ActionEvent event) throws SQLException {
    	 
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        String Titleid=stage.getTitle();
			
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
	    String sql="SELECT Serial_no, Items_ordered, Total_Price, Order_Current_Status FROM javafx.item_orders WHERE Username=?;";
	    item=connection.prepareStatement(sql);
	    item.setString(1, Titleid);
	    resultset= item.executeQuery();
	    
	    while(resultset.next()){
	    	
	    int OrderId=resultset.getInt("Serial_no");	
	    String Item=resultset.getString("Items_ordered"); 
	    int Total=resultset.getInt("Total_Price");
	    
	    String OrderStatus=resultset.getString("Order_Current_Status");
	    orderIdlist.add(OrderId);
	    itemlist.add(Item);
	    pricelist.add(Total);
	    orderStatuslist.add(OrderStatus);
	    }       
	    orderIdList.setItems(orderIdlist);
        itemList.setItems(itemlist);
        priceList.setItems(pricelist);
        orderStatusList.setItems(orderStatuslist);
	}
     
     public void cancelOrder(ActionEvent event) throws SQLException {
    	 
    	 int selectedIndex=itemList.getSelectionModel().getSelectedIndex();
    	 if(selectedIndex>=0) {
    	 int orderId=orderIdList.getItems().get(selectedIndex); 
    	 String status = null;
    	 
    	 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
    	 
    	 String sql1="SELECT Order_Current_Status from javafx.item_orders WHERE Serial_no=?;";
    	 item=connection.prepareStatement(sql1);
    	 item.setInt(1, orderId);
    	 resultset=item.executeQuery();
    	 
    	 if(resultset.next()) {
    		 status=resultset.getString("Order_Current_Status");
    		 
    	    if(status.equals("Order Recieved")){
    	    	
    	    	 Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    	    	 alert.setHeaderText("Are you sure to cancel this Order?");
    	    	 alert.getButtonTypes().remove(0);
    	    	 alert.getButtonTypes().add(ButtonType.YES);
    	    	 
    	    	 if (alert.showAndWait().get()== ButtonType.YES ) {
    	    	
    	                   String sql="UPDATE javafx.item_orders SET Order_Current_Status='Order Cancelled' WHERE Serial_no=?;";
    	                   update=connection.prepareStatement(sql);
    	                   update.setInt(1, orderId);
    	                   update.executeUpdate();
    	                   
    	                   displayNamelbl.setText("Order Cancelled");
    	                   displayNamelbl.setTextFill(Color.RED);
    	 
    	                   orderIdlist.clear();
    	                   itemlist.clear();
    	                   pricelist.clear();
    	                   orderStatuslist.clear();
    	  
    	                    displayOrderStatus(event);
    	 }
    	    }
    	    else {
    	    	Alert alert=new Alert(Alert.AlertType.ERROR);
    	    	alert.setHeaderText("Order doesn't exist!");
    	    	alert.show();   	    	
    	    }
    	 }
       }   	 
     }
}