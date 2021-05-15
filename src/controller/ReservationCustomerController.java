package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.Reservation;


public class ReservationCustomerController {
	@FXML
	private Pane res_area;
	@FXML
	private TextField txtsearch;
	@FXML
	private Button butaffich;
	@FXML
	private TableView<Reservation> table;
	@FXML
	private TableColumn<Reservation,Integer> tabnum;
	@FXML
	private TableColumn<Reservation,Integer> tabcin;
	@FXML
	private TableColumn<Reservation,String> tabmat;
	@FXML
	private TableColumn<Reservation,String> tabType;
	@FXML
	private TableColumn<Reservation,Double> tabPrice;
	@FXML
	private TableColumn<Reservation,Date> tabDD;
	@FXML
	private TableColumn<Reservation,Date> tabDF;
	private final ObservableList<Reservation> listesRes=FXCollections.observableArrayList();
	
	public void affiche_tab (ActionEvent Event) throws SQLException{
		String enterCIN =txtsearch.getText();
		Integer convertCIN= Integer.valueOf(enterCIN);
		try{ 	
			String sql="SELECT * FROM tabreservation WHERE  cinclient_fk='"+convertCIN+"'";
			//String sql="SELECT Matricule_fk , DateDebut , DateFin , TypePaiment , Montant FROM tabreservation WHERE  cinclient_fk = '"+convertCIN+"' "; 
			 ConnectionBD cnx=new ConnectionBD();
			  Connection connect=cnx.getConnection();
			  PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
			  ResultSet rs= preparedStatement.executeQuery();
		while (rs.next()){
		listesRes.add( new Reservation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getString(6),rs.getDouble(7))); }
		connect.close();
		}catch(SQLException e){
		e.printStackTrace();}
		tabnum.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("numRes"));
		tabcin.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("cinclient_fk"));
		tabmat.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Matricule_fk"));
		tabDD.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateDebut"));
		tabDF.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateFin"));
		tabType.setCellValueFactory(new PropertyValueFactory<Reservation,String>("TypePaiment"));
		tabPrice.setCellValueFactory(new PropertyValueFactory<Reservation,Double>("Montant"));
		table.setItems(listesRes);
		}
		
	
	public void handleRetour (MouseEvent Event ) throws IOException
	{ Parent fxml = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceCustomer.fxml"));
	res_area.getChildren().removeAll();
	res_area.getChildren().setAll(fxml);}
	
	
	
	
	}

