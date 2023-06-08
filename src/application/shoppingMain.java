package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class shoppingMain extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws SQLException, IOException {
		
		Connection connection=null;
		PreparedStatement CheckLoggedIn=null;
		ResultSet resultset=null;
		
		try {
			
		String sql="SELECT LogIn_Status ,Username FROM javafx.`database table` WHERE LogIn_Status='Logged in';";
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx","root","Jeet@123");
		CheckLoggedIn=connection.prepareStatement(sql);
	    resultset= CheckLoggedIn.executeQuery();
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(resultset.next()) {
			String username=resultset.getString("Username");
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(username);
			Image image=new Image(getClass().getResourceAsStream("/resources/Logo.png"));
			primaryStage.getIcons().add(image);
			primaryStage.show();
		}
		
		else {
			
		try {
			
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("NEEDS");
			Image image=new Image(getClass().getResourceAsStream("/resources/Logo.png"));
			primaryStage.getIcons().add(image);	
			primaryStage.show();
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}