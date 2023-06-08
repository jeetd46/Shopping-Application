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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	  @FXML
	  private TextField UserName;
	 
	  @FXML
	  private PasswordField Password;
	 
      @FXML
	  private Button Click;
     
      @FXML
      private Button SignUp;
     
      @FXML
      private Label LogInlbl;
     
      @FXML
      private ImageView myImage;
      
      ServiceImpl service=new ServiceImpl();
     
     
  Image image=new Image(getClass().getResourceAsStream("/resources/gallery-1432664914-strawberry-facts1.jpg"));
          
  public boolean checkUser() {
  		
		   boolean proceed=true;
		   Connection connection=null;
		   PreparedStatement CheckUser=null;

		   String username = UserName.getText();
		   if(username.isEmpty())
		   {
			   LogInlbl.setText("Type Email/Phone Number");
			   proceed=false;
		   }
		   
		   String Lock=Password.getText();
		   if(Lock.isEmpty() && proceed==true)
		   {
			   LogInlbl.setText("Type Password");
			   proceed=false;
		   }
	try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
		String sql="SELECT Username,Password FROM javafx.`database table` WHERE Username=? AND Password=?;";
		CheckUser=connection.prepareStatement(sql);
		CheckUser.setString(1,username);
		CheckUser.setString(2, Lock);
		ResultSet resultset=CheckUser.executeQuery();	
		
		if(!resultset.isBeforeFirst() && proceed==true)
		{
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Error!");
			alert.setContentText("Username/Password didn't match");
			alert.show();
			proceed=false;
		}
		
	    }catch(SQLException e) {
		e.printStackTrace();
	    }
	return proceed;
	}
 
  public void loginAction(ActionEvent Event) throws IOException, SQLException{
	 		
		    boolean connect=checkUser();
		    
		    if(connect==true)
		    {
		    String username=UserName.getText();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	        root=loader.load();
    	    stage=(Stage)((Node)Event.getSource()).getScene().getWindow();
    	    stage.setTitle(username);
		    scene=new Scene(root);
		    stage.setScene(scene);
	    	stage.show();
	    		    	
	    	PreparedStatement createColumn;
	    	Connection connection=null;
	    	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
	        String sql="ALTER TABLE javafx.history ADD History"+username+" VARCHAR(45);";
	        createColumn=connection.prepareStatement(sql);
	        createColumn.executeUpdate();
	        
	        PreparedStatement loginStatus;
	        String sql2="UPDATE javafx.`database table` SET LogIn_Status='Logged in' WHERE Username=?;";
	        loginStatus=connection.prepareStatement(sql2);
	        loginStatus.setString(1, username);
	        loginStatus.executeUpdate();
		    }	    
	} 
  
	public void SignUpAction(ActionEvent Event) throws IOException{
		
	     	FXMLLoader loader =new FXMLLoader(getClass().getResource("Sign Up.fxml"));
	     	service.viewStage(Event,loader);
	}

}