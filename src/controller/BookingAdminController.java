package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import connection.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Client;
import models.Reservation;
import models.Voiture;

public class BookingAdminController {
@FXML
private AnchorPane bookpane;

@FXML
private TableView<Reservation> table;

@FXML
private TableColumn<Reservation,Integer> clNumR;

@FXML
private TableColumn<Reservation,Integer> clCIN;

@FXML
private TableColumn<Reservation,String> clMatricule;

@FXML
private TableColumn<Reservation,Date> Date_Deb;

@FXML
private TableColumn<Reservation,Date> Date_fin;
@FXML
private TableColumn<Reservation,String> tabpaiment;
@FXML
private TableColumn<Reservation,Double> tabMontant;

@FXML
private ComboBox<Integer> chooseClient;
@FXML
private ComboBox <String> chooseMatricule;
@FXML
private TextField txtnumRes;
@FXML
private TextField txtMatricule;
@FXML
private TextField txtTypPai;
@FXML
private TextField txtMontant;
@FXML
private DatePicker DateSortie;
@FXML
private DatePicker DateRetour;
@FXML
private Button ButValide ;
@FXML
private Button butAffich;
@FXML
private Button butModif;
@FXML
private Button butAnnul;
@FXML
private TextField txtResearch;
@FXML
private TextArea duree_location;

private final ObservableList<Reservation> listesReserv=FXCollections.observableArrayList();

public void affichetab(ActionEvent Event) throws SQLException{
try{
	  String sql="SELECT * FROM tabreservation ";
	  //String sql1="SELECT Price FROM voiture WHERE Matricule=?  ";
	  ConnectionBD cnx=new ConnectionBD();
	  Connection connect=cnx.getConnection();
	  PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
	  ResultSet rs= preparedStatement.executeQuery();
while (rs.next()){
listesReserv.add( new Reservation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getString(6),rs.getDouble(7))); }
connect.close();
}catch(SQLException e){
e.printStackTrace();}

clNumR.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("numRes"));
clCIN.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("cinclient_fk"));
clMatricule.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Matricule_fk"));
Date_Deb.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateDebut"));
Date_fin.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateFin"));
tabpaiment.setCellValueFactory(new PropertyValueFactory<Reservation,String>("TypePaiment"));
tabMontant.setCellValueFactory(new PropertyValueFactory<Reservation,Double>("Montant"));
table.setItems(listesReserv);
}

public void RechercheReservation (ActionEvent event ) throws IOException, ParseException {	
 	//on fait un filtre 
FilteredList <Reservation> listesResfiltre= new FilteredList<>(listesReserv,e -> true) ;  
txtResearch.setOnKeyReleased (e -> {
txtResearch.textProperty().addListener((observable,oldValue, newValue) -> {
	listesResfiltre.setPredicate(Reservation -> { 
if (newValue==null || newValue.isEmpty()) {
		return true ;}	  
String num_res=String.valueOf(Reservation.getNumRes());
if (num_res.contains(newValue) ) { //filtrer selon le numéro de la reservation
return true;
}
return false;
   });
});
SortedList<Reservation> sortedData= new SortedList<>(listesResfiltre);
sortedData.comparatorProperty().bind(table.comparatorProperty());
table.setItems(sortedData);
});
 }


//liste client selon num cin
public static ObservableList<Integer> CINClient() throws ClassNotFoundException {
ObservableList<Integer> listClient = FXCollections.observableArrayList();

String sql = "SELECT cinclient FROM table_client WHERE`cinclient`";
try {
	ConnectionBD cnx=new ConnectionBD();
	Connection connect=cnx.getConnection();
PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
ResultSet rs= preparedStatement.executeQuery();
while (rs.next()) {

Client cl =new Client ();

cl.setCinclient(rs.getInt(1));
listClient.add(cl.getCinclient());}
} catch (SQLException ex) {
ex.printStackTrace();

} return listClient;}

@FXML
private void CustomerCIN (MouseEvent event) {
ObservableList<Integer> listClient;
try {
listClient= CINClient();
chooseClient.setItems(listClient);}
catch (ClassNotFoundException ex) 
{
ex.printStackTrace();
}}


public void saveReservation (ActionEvent Event ) throws SQLException, IOException{

ConnectionBD cnx=new ConnectionBD();
Connection connect=cnx.getConnection();
String sql="INSERT INTO tabreservation (`cinclient_fk`,`Matricule_fk`,`DateDebut`,`DateFin`,`TypePaiment`,`Montant`) VALUES ('"+chooseClient.getValue()+"','"+chooseMatricule.getValue()+"','"+DateSortie.getValue()+"','"+DateRetour.getValue()+"','"+txtTypPai.getText()+"','"+txtMontant.getText()+"')";
try {
Statement statement = connect.createStatement();
	statement.executeUpdate(sql);
} catch (SQLException e) {	
	e.printStackTrace();}	
refrechTable();}

//liste des voitures disponibles selon le matricule
public static ObservableList<String> MatriculeVoiture() throws ClassNotFoundException {
ObservableList<String> listVoitures = FXCollections.observableArrayList();

String sql = "SELECT Matricule FROM voiture WHERE `Etat`= 'Disponible' ";
try {
	ConnectionBD cnx=new ConnectionBD();
	Connection connect=cnx.getConnection();
PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
ResultSet rs= preparedStatement.executeQuery();
while (rs.next()) {
Voiture car =new Voiture ();

car.setMatricule(rs.getString(1));
listVoitures.add(car.getMatricule());

}
} catch (SQLException ex) {
ex.printStackTrace();

} return listVoitures;
}


@FXML
private void MatriculeCarButton (MouseEvent event) {
ObservableList<String>listVoitures;
try {
	listVoitures= MatriculeVoiture() ;
	 chooseMatricule.setItems(listVoitures);}
catch (ClassNotFoundException ex) 
{
ex.printStackTrace();
}}


public void UpdateReservation (ActionEvent event ) throws IOException, ParseException, SQLException {
String numres=txtnumRes.getText();
int Nres = Integer.parseInt(numres);
Integer cin=chooseClient.getValue();
String matricul=(chooseMatricule.getValue()).toString();
Date valdeb = java.sql.Date.valueOf(DateSortie.getValue());
Date dateFin = java.sql.Date.valueOf(DateRetour.getValue());
String champTypePai=txtTypPai.getText();
Double champPrix= Double.valueOf(txtMontant.getText());
Reservation resi =table.getItems().get(table.getSelectionModel().getSelectedIndex());
resi.setNumRes(Nres);
resi.setCinclient_fk(cin);
resi.setMatricule_fk(matricul);
resi.setDateDebut(valdeb);
resi.setDateFin(dateFin);
resi.setTypePaiment(champTypePai);
resi.setMontant(champPrix);
int status = ConnectionBD.Update_reservation(resi);
if (status==0)
	   { Alert alert= new Alert(AlertType.INFORMATION);
	    alert.setTitle("Update Reservation ");
	    alert.setHeaderText("Information");
	    alert.setContentText("Update successful !");
        alert.showAndWait();	
     
	   } else {
		 Alert alert= new Alert(AlertType.ERROR);
		 alert.setTitle("Update Reservation !");
		 alert.setHeaderText("Information");
		 alert.setContentText("Update failed, try again!");
	     alert.showAndWait();}
refrechTable();
}

public void refrechTable() throws SQLException {
	listesReserv.clear();
	affichetab(null);	
}
public void handletoEspAdmin(MouseEvent Event ) throws IOException 
{ 
	Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceAdmin.fxml"));
	bookpane.getChildren().setAll(root);}
@FXML
public void AnnulerReservation (ActionEvent event ) throws IOException
{  Reservation res =table.getItems().get(table.getSelectionModel().getSelectedIndex());
	table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
	try {
		String sql="DELETE FROM tabreservation WHERE NumRes=?" ;
		ConnectionBD cnx=new ConnectionBD();
		Connection connect=cnx.getConnection();	
		PreparedStatement stat;
		stat= connect.prepareStatement(sql);
		stat.setInt(1,res.getNumRes());
		stat.executeUpdate();
		connect.close();  } catch (SQLException e) {
		  e.printStackTrace();
	  } 
	 Alert alert= new Alert(AlertType.INFORMATION);
	  alert.setTitle("Annulation");
	  alert.setHeaderText("Information");
	  alert.setContentText("Cancellation of reservation is affected successfully!");
	  alert.showAndWait();
  }
//calculer la différence entee 2 dates pour trouver la période de location 
public void calculDeffdate (ActionEvent Event)
{ SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
	 Date valdeb = java.sql.Date.valueOf(DateSortie.getValue());
	 Date dateFin = java.sql.Date.valueOf(DateRetour.getValue());
	 String daydeb= String.valueOf(valdeb);
	 String dayend= String.valueOf(dateFin);
	 String sql = "SELECT Price FROM voiture WHERE `Matricule`='"+chooseMatricule.getValue()+"'";
	 try {  java.util.Date dateBefore = myFormat.parse(daydeb);
     java.util.Date dateAfter = myFormat.parse(dayend);
     long difference = dateAfter.getTime() - dateBefore.getTime();
     float daysBetween = (difference / (1000*60*60*24));
     String duree= String.valueOf((int)daysBetween);
     duree_location.setText(duree);
     
		 ConnectionBD cnx=new ConnectionBD();
		Connection connect=cnx.getConnection();
		PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
		ResultSet rs= preparedStatement.executeQuery();
	     
	       while (rs.next()) {
	   		Double prixcar=rs.getDouble(1);  
	       txtMontant.setText(String.valueOf(daysBetween*prixcar)); }
	       connect.close();
	 } catch (Exception e) {
	       e.printStackTrace();
	 }

}
}


	






