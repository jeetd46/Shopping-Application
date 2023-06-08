module Project1 {
	requires   javafx.fxml;
	requires   java.sql;
	requires   javafx.controls;
	requires javafx.base;
	requires javafx.graphics;
	opens application to javafx.graphics, javafx.fxml;
}