package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SignUp_Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	    @FXML
	    private TextField name;
	   
	    @FXML
	    private Label namelbl;
	    
	    @FXML
	    private TextField UserName;
	   
	    @FXML
	    private Label UserNamelbl;
	
	    @FXML
	    private Button SignClick;

	    @FXML
	    private ToggleGroup Gender;
	    
	    @FXML
	    private Label genderlbl;

	    @FXML
	    private RadioButton Radio1;

	    @FXML
	    private RadioButton Radio2;

	    @FXML
	    private RadioButton Radio3;
	    
	    @FXML
	    private PasswordField Password1;
	    
	    @FXML
	    private Label Passwordlbl;
	        
	    @FXML
	    private Button Login;

	    @FXML
	    private PasswordField CnfPassword;
	    
	    @FXML
	    private Label CnfPasswordlbl;
	    
	    
	public void Login(ActionEvent e) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		 stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		 scene=new Scene(root);
		 stage.setScene(scene);
	     stage.show();
	}


	public void SignAction(ActionEvent Event) throws IOException{
		
		 String username=UserName.getText();
		 String Name= name.getText();
		 String Password=Password1.getText();
		 String fPassword=CnfPassword.getText();
		 String gender="";
		 if(Radio1.isSelected()) {
			gender="Male";
		 }
		 else if(Radio2.isSelected()) {
			 gender="Female";
		 }
		 else if(Radio3.isSelected()) {
			 gender="Other";
		 }
		 
         boolean connect=validate_form(username,Name,Password,fPassword);
		 
		 if(connect==true)
		 {
		   boolean connect2=SignUp(username,Name,Password,gender);
		   
		   if(connect==true && connect2==true) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	        root=loader.load();
	        LoginController controller=loader.getController();
	        controller.displayName(Name);
    	    stage=(Stage)((Node)Event.getSource()).getScene().getWindow();
    	    stage.setTitle(username);
		    scene=new Scene(root);
		    stage.setScene(scene);
		 }
		   }		 
	}
	
	
	public boolean validate_form(String username,String Name,String Password,String fPassword) {
		
		if(Name.isEmpty())
		 {
			namelbl.setText("Enter your name");
		 }
		 else
		 {
			 namelbl.setText(" ");
		 }
		
		 if(username.isEmpty())
		 {
			UserNamelbl.setText("Enter your Username");
		 }
		 else
		 {
			 UserNamelbl.setText(" ");
		 }
		 
		 if(Password.isEmpty())
		 {
			Passwordlbl.setText("Enter Password");
		 }
		 else if(Password.length()<8) {
			 
			 Passwordlbl.setText("Password must have at least 8 characters");
		 }
		 else
		 {
			 Passwordlbl.setText(" ");
		 }
         if(Radio1.isSelected()==false && Radio2.isSelected()==false && Radio3.isSelected()==false) {
			 
			 genderlbl.setText("Select Your Gender");
		 }
         else
         {
        	 genderlbl.setText(" ");
         }
		 
	 boolean proceed=true;
		 	 
		 if(Name.isEmpty() || username.isEmpty() || Password.isEmpty() || Password.length()<8)
		 {
			 proceed=false;
		 }
		 
         if(Radio1.isSelected()==false && Radio2.isSelected()==false && Radio3.isSelected()==false && proceed==true) {
			 
			 genderlbl.setText("Select Your Gender");
			 proceed=false;
		 }
		
		 if(!Password.equals(fPassword) && proceed==true)
		 {
			 CnfPasswordlbl.setText("Password didn't Match");
			 proceed=false;
		 }
		 else
		 {
			 CnfPasswordlbl.setText(" ");
		 }
         
	return proceed;
	}
		 
	
	public boolean SignUp(String username,String Name,String Password, String gender) {
		 
		Connection connection=null;
		PreparedStatement CheckUserExist=null;
		PreparedStatement psInsert=null;
		PreparedStatement ordersInsert=null;
		ResultSet resultset=null;
		boolean proceed=true;
		
		try {
			
			String sql="SELECT Username FROM javafx.`database table` WHERE Username=?;";
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
			CheckUserExist=connection.prepareStatement(sql);
		    CheckUserExist.setString(1,username);
		    resultset= CheckUserExist.executeQuery();
				
			if(resultset.isBeforeFirst()) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setContentText("UserName already taken!");
				alert.show();
				proceed=false;
			}
			else{	
			String sql1="INSERT INTO javafx.`database table` (Username, Name, Password, Gender,LogIn_Status) VALUES(?, ?, ?, ?, ?);";
		    psInsert=connection.prepareStatement(sql1);
			psInsert.setString(1, username);
			psInsert.setString(2, Name);
			psInsert.setString(3, Password);
			psInsert.setString(4, gender);
			psInsert.setString(5, "Logged in");
			psInsert.executeUpdate();
			
			String sql2="INSERT INTO javafx.cart (Username) VALUES(?);";
			ordersInsert=connection.prepareStatement(sql2);
			ordersInsert.setString(1,username);
			ordersInsert.executeUpdate();
			
	    	PreparedStatement createColumn;
	        String sql3="ALTER TABLE javafx.history ADD History"+username+" VARCHAR(45);";
	        createColumn=connection.prepareStatement(sql3);
	        createColumn.executeUpdate();
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
		}finally {
			if(resultset!=null) {
				try {
					resultset.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(psInsert!=null) {
				try {
					psInsert.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ordersInsert!=null) {
				try {
					ordersInsert.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(CheckUserExist!=null) {
				try {
					CheckUserExist.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection!=null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return proceed;
	}   
}