package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class EspaceCustomerController {

	@FXML
	private Button butListCars;
	@FXML 
	private Pane parea_customer;
	@FXML
	private Button butViewRes;
	
	
	
public void ViewListCars (ActionEvent actionevent) throws SQLException, IOException
	{ 
	Parent fxml= (Parent)FXMLLoader.load(getClass().getResource("/vue/ViewListCar.fxml"));
	parea_customer.getChildren().setAll(fxml);}	
	
//Aller consulter les réservations par le client
public void ViewListRes (ActionEvent actionevent) throws SQLException, IOException
	{ 
	Parent fxml= (Parent)FXMLLoader.load(getClass().getResource("/vue/ReservationCustomer.fxml"));
	parea_customer.getChildren().setAll(fxml);}


	
	
	
	public void ButtonExit (ActionEvent actionevent) throws SQLException
	{ System.exit(0);
		}	
	
}
