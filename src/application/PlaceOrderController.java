package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Service.ServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlaceOrderController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Label AddressLabel;

    @FXML
    private TextField Addressfield;

    @FXML
    private Button Back;

    @FXML
    private Button Confirm;

    @FXML
    private PasswordField Passwordfield;

    @FXML
    private Label Passwordlbl;

    @FXML
    private Label PhoneLabel;

    @FXML
    private TextField Phonefield;  

    @FXML
    private Label PinLabel;

    @FXML
    private TextField Pinfield;

    @FXML
    private RadioButton Radiobutton;

    @FXML
    private Label StateLabel;

    @FXML
    private TextField Statefield;


    @FXML
    private Label UserNamelbl;

    @FXML
    private Label cashOnDeliveryLabel;
    
    @FXML
    private Label ItemsLabel;
    
    @FXML
    private Label Total_PriceLabel;
    
    
    Connection connection=null;
   	PreparedStatement CheckUser=null;
   	PreparedStatement update=null;
   	ResultSet resultset=null;
   	String Items;
   	ServiceImpl service=new ServiceImpl();


    public void username(String userId) {
    	
    	UserNamelbl.setText(userId);
    }
   	
   	public void orderDisplay(int Total) {
   		
   		Total_PriceLabel.setText(Integer.toString(Total));
   	}
    
   public boolean validate_form(String Address,String Password, String Phone, String State,String Pin) {
	
	boolean proceed=true;
	
		if(Address.isEmpty())
		 {
			AddressLabel.setText("Enter your Address");
		 }
		 else
		 {
			 AddressLabel.setText(" ");
		 }
		 
		 if(Password.isEmpty())
		 {
			Passwordlbl.setText("Enter Password");
		 }
		 else
		 {
			 Passwordlbl.setText(" ");
		 }
		 
		 if(Phone.isEmpty())
		 {
			PhoneLabel.setText("Enter Phone no.");
		 }
		 else
		 {
			 PhoneLabel.setText(" ");
		 }
		 
		 if(State.isEmpty())
		 {
			StateLabel.setText("Enter Your State");
		 }
		 else
		 {
			 StateLabel.setText(" ");
		 }
		 
		 if(Pin.isEmpty())
		 {
			PinLabel.setText("Enter Pin no.");
		 }
		 else
		 {
			 PinLabel.setText(" ");
		 }
		 
         if(Radiobutton.isSelected()==false ) {
			 
			 cashOnDeliveryLabel.setText("Select Payment Method");
			 proceed=false;
		 }
         else
         {
        	 cashOnDeliveryLabel.setText(" ");
         }
		 		 	 
		 if(Address.isEmpty() || Phone.isEmpty() || Password.isEmpty() || State.isEmpty() || Pin.isEmpty())
		 {
			 proceed=false;
		 }    
	return proceed;
	}

   public boolean checkUser(String username,String Password, String Address,String Phone, String State,String Pin, int Total,String OrderStatus) {
	  
	  boolean proceed=true;
	  
	  try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
			String sql="SELECT Username,Password FROM javafx.`database table` WHERE Username=? AND Password=?;";
			CheckUser=connection.prepareStatement(sql);
			CheckUser.setString(1,username);
			CheckUser.setString(2, Password);
		    resultset=CheckUser.executeQuery();	
			
			if(!resultset.isBeforeFirst() && proceed==true)
			{
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Error!");
				alert.setContentText("Password didn't match");
				alert.show();
				proceed=false;
			}
			else{	
				String sql1="INSERT INTO javafx.item_orders (Username, Address, Phone, State, Pin, Items_ordered, Total_Price,Order_Current_Status)"
						+ " VALUES(?, ?, ?, ?,?,?,?,?);";
			    update=connection.prepareStatement(sql1);
				update.setString(1, username);
				update.setString(2, Address);
				update.setString(3, Phone);
				update.setString(4, State);
				update.setString(5, Pin);
				update.setString(6, Items);
				update.setInt(7, Total);
				update.setString(8, OrderStatus);
				update.executeUpdate();
				}	
		    }catch(SQLException e) {
			e.printStackTrace();
		    }
		return proceed;
		}

  
    public void confirmingOrder(ActionEvent event) throws IOException, SQLException {
    	
		 String username=UserNamelbl.getText();
		 String Address= Addressfield.getText();
		 String Password=Passwordfield.getText();
		 String Phone=Phonefield.getText();
		 String State=Statefield.getText();
		 String Pin=Pinfield.getText();
		 String OrderStatus="Order Recieved";
		 int Total=Integer.parseInt(Total_PriceLabel.getText());
		 
        boolean connect=validate_form(Address,Password,Phone,State,Pin);
		 
		 if(connect==true)
		 {
		   boolean connect2=checkUser(username,Password,Address,Phone,State,Pin,Total,OrderStatus);
		   
		   if(connect==true && connect2==true) {
			   
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Order Status.fxml"));
	        root=loader.load();
	        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        OrderStatusController controller=loader.getController();
	        controller.displayNamelbl.setText(OrderStatus);
	        controller.displayOrderStatus(event);
		    scene=new Scene(root);
		    stage.setScene(scene);
		    stage.show();
		    
		    int i=1;
		    while(i<7) {
		    String sql1="UPDATE javafx.cart SET "+ "Item"+i + "= null WHERE Username=?;";
		    try {
			update=connection.prepareStatement(sql1);
		    update.setString(1, UserNamelbl.getText());
		    update.executeUpdate();
		    i++;
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		    }
		 }
		   }
    }

    public void goBack(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        CartController controller=loader.getController();
        controller.displayCart(event);
        scene=new Scene(root);
        stage.setScene(scene);
    	stage.show();

    }
}