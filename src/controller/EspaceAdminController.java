package controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class EspaceAdminController {

@FXML
private Button mangCars;

@FXML
private Button  mangBo;

@FXML
private Button  managBill ;

@FXML
private Button  butexit ;

@FXML
private Pane adminArea;

public void handletoCars(ActionEvent actionEvent ) throws IOException 
{ 
	Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/GestionVoitures.fxml"));
	adminArea.getChildren().setAll(root);}

public void handletoBooking(ActionEvent actionEvent ) throws IOException 
{ Parent fxml = (Parent)FXMLLoader.load(getClass().getResource("/vue/BookingAdmin.fxml"));
adminArea.getChildren().setAll(fxml);}

public void handletoBilling(ActionEvent actionEvent ) throws IOException 
{ Parent fxml = (Parent)FXMLLoader.load(getClass().getResource("/vue/BillingAdmin.fxml"));
adminArea.getChildren().setAll(fxml);}

public void ButtonExit (ActionEvent actionEvent) throws SQLException
{
	System.exit(0);
}

}