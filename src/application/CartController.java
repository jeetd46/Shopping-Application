package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class CartController {
	

    @FXML
    private Button buyButton;
	
	@FXML
	private Button Remove;
	
	@FXML
	private Button Back;
	
	@FXML
	private Label Backlbl;

    @FXML
    private ListView<String> itemList;
    
    @FXML
    private ListView<Integer> priceList;
      
    @FXML
    private Button Menu;
    
    

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    
    Connection connection=null;
	PreparedStatement item=null;
	PreparedStatement update=null;
	PreparedStatement addPrice=null;
	PreparedStatement history=null;
	ResultSet resultset=null;
	ResultSet priceresultset=null;
	
	ObservableList<String> itemlist=FXCollections.observableArrayList();
	ObservableList<Integer> pricelist=FXCollections.observableArrayList();
	
	int Total=0;


	public void displayCart(ActionEvent event) {
				
		try {
			
			int i=1;
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
  			String Titleid=stage.getTitle();
			while(i<7) {
	    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
	    String sql="SELECT * FROM javafx.cart WHERE Username=?;";
	    item=connection.prepareStatement(sql);
	    item.setString(1, Titleid); 
	    resultset= item.executeQuery();
	    if(resultset.next()) {
	    	String Item=resultset.getString("Item"+i);  
	       if(Item!=null) {
	    	   
	    	   itemlist.add(Item);
	    	   
	    	   String SQL="SELECT Item_price FROM javafx.items WHERE Item_name=?;";
	    	   addPrice=connection.prepareStatement(SQL);
	   	       addPrice.setString(1, Item); 
	   	       priceresultset= addPrice.executeQuery();
	   	       
	   	       if(priceresultset.next()) {
	   	    	   int price=priceresultset.getInt("Item_price");
	   	    	   Total = Total+price;
	   	    	   pricelist.add(price);
	   	       } 
	    	   i++;
	        } 	
	        else {
	         	break;
	        }  	
	    }
			}
        itemList.setItems(itemlist);
        priceList.setItems(pricelist);
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
     
    public void Buy (ActionEvent event) throws IOException {
    	    	
    	Total=0;
    	itemlist.clear();
    	displayCart(event);
    	
        String items=itemList.getItems().toString(); 
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Place Order.fxml"));
        root=loader.load();
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    String Titleid=stage.getTitle();
	    
        PlaceOrderController controller=loader.getController();
        controller.username(Titleid);
        controller.Items=items;
        controller.orderDisplay(Total);
        
	    scene=new Scene(root);
	    stage.setScene(scene);   
	    stage.show();
    }
    
    public void removeItem(ActionEvent event) throws SQLException {
    	
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String Titleid=stage.getTitle();
    	
    	int selected=itemList.getSelectionModel().getSelectedIndex();
    	if(selected>=0) {
    	itemList.getItems().remove(selected);
    	priceList.getItems().remove(selected);

		int i=selected+1;
		
		while(i<7) {
			if(i<6) {
	    String sql1="UPDATE javafx.cart SET "+ "Item"+i +" = " + "Item"+(i+1) + " WHERE Username=?;";
	    item=connection.prepareStatement(sql1);
	    item.setString(1, Titleid);
	    item.executeUpdate();
	    i++;
			}
			else {
				  String sql1="UPDATE javafx.cart SET "+ "Item"+i + "= null WHERE Username=?;";
				    item=connection.prepareStatement(sql1);
				    item.setString(1, Titleid);
				    item.executeUpdate();
				    break;
				
			}
		}
      }
    }

    public void goToMenu(ActionEvent event) throws IOException {
  	
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
      root=loader.load();
      stage=(Stage)((Node)event.getSource()).getScene().getWindow();
      scene=new Scene(root);
      stage.setScene(scene);
  	  stage.show();
  }
    
    public void goBack(ActionEvent event) throws IOException, SQLException {
    	
    	stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String Titleid=stage.getTitle();
    	
    	String goToPage = null;
    	
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
        String sql="SELECT History"+Titleid+" FROM javafx.history;";
        history=connection.prepareStatement(sql);
        resultset=history.executeQuery(); 
        
        while(resultset.next()) {
              goToPage=resultset.getString("History"+Titleid);     	
        }   
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(goToPage));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        if (goToPage.equals("Order Status.fxml")) {
        	OrderStatusController orderStatusController=loader.getController(); 	
            orderStatusController.displayOrderStatus(event);
        
        }    
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        String sql1="DELETE FROM javafx.history WHERE SerialNo IS NOT NULL;";
        update=connection.prepareStatement(sql1);
        update.executeUpdate();
    }
    }
    