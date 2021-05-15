package controller;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;

import connection.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Voiture;


public class  GestionVoituresController implements Initializable{
	
	    @FXML
        private TextField txtResearch ; 
	
	    @FXML
	    private TableView<Voiture> table;

	    @FXML
	    private TableColumn<Voiture,Integer> idVoiture;

	    @FXML
	    private TableColumn<Voiture,String> MatriculeV;

	    @FXML
	    private TableColumn<Voiture,String> MarqueV;

	    @FXML
	    private TableColumn<Voiture,String> CouleurV;
	    
	    @FXML
	    private TableColumn<Voiture,String> TypeV;
	    
	    @FXML
	    private TableColumn<Voiture,String> EtatV;
	    
	    @FXML
	    private TableColumn<Voiture,Double> PriceV;
	    
	    private final ObservableList<Voiture> listesVoitures=FXCollections.observableArrayList();
	 
	    @FXML
	    private Button butUpdate;
	   
	    @FXML
	    private TextField txtidv;
	   
	    @FXML
	    private TextField txtmatricule;
	    @FXML
	    private  TextField txtMarque;
	    @FXML
	    private ChoiceBox<String> txtCouleur;
	    @FXML
	    private ChoiceBox <String> txtType;
	    
	    @FXML
	    private ChoiceBox<String> txtEtat;
	  
	    @FXML
	    private TextField txtPrice;
		@FXML
		private Button butDelete;
		@FXML
		private Button butSave;
		@FXML
		private AnchorPane esp_area;

	    @Override
	
//Afficher le tableau de la base de donnée sur la tableview     
public void initialize(URL location, ResourceBundle resources) {
ObservableList<String> listesColor=FXCollections.observableArrayList("Blanc","Rouge","Noir","Vert","Gris","Bleu","Biege","Bronze","Vert d'eau","Violet","Corail","Argent","Aubergine");
txtCouleur.setItems(listesColor);
ObservableList<String> listesType=FXCollections.observableArrayList("Essence","Diesel");
txtType.setItems(listesType);
ObservableList<String> listesEtat=FXCollections.observableArrayList("Disponible","Non disponible");
txtEtat.setItems(listesEtat);
try{
	  String sql="SELECT * FROM voiture ";
	  ConnectionBD cnx=new ConnectionBD();
	  Connection connect=cnx.getConnection();
	  PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
	  ResultSet rs= preparedStatement.executeQuery();
while (rs.next()){
listesVoitures.add( new Voiture(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7))); }
connect.close();
}catch(SQLException e){
e.printStackTrace();}
			

idVoiture.setCellValueFactory(new PropertyValueFactory<Voiture,Integer>("idv"));
MatriculeV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Matricule"));
MarqueV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Marque"));
CouleurV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Couleur"));
TypeV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Type"));
EtatV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Etat"));
PriceV.setCellValueFactory(new PropertyValueFactory<Voiture,Double>("Price"));
table.setItems(listesVoitures);
setCellValueFromTableToTextField(); }
	
	    
public void getVoiture (ActionEvent event ) throws IOException, ParseException {	
	 	//on fait un filtre 
FilteredList <Voiture> listesVfiltre= new FilteredList<>(listesVoitures,e -> true) ;  
txtResearch.setOnKeyReleased (e -> {
txtResearch.textProperty().addListener((observable,oldValue, newValue) -> {
listesVfiltre.setPredicate(Voiture -> { 
if (newValue==null || newValue.isEmpty()) {
			return true ;}	
String lowerCaseFilter= newValue.toLowerCase();    
String idvoiture=String.valueOf(Voiture.getIdv());
if (idvoiture.contains(newValue) ) { //filtrer selon l'id de voiture
   return true;
} else if (Voiture.getMarque().toLowerCase().contains(lowerCaseFilter)) { //filtrer selon la marque
   return true;
}else if (Voiture.getMatricule().toLowerCase().contains(lowerCaseFilter)) { //filtrer selon le matricule
    	  return true;
}else if (Voiture.getType().toLowerCase().contains(lowerCaseFilter)) {
    	  return true;} // filtrer selon le type
else if (Voiture.getEtat().toLowerCase().contains(lowerCaseFilter)) { //filtrer selon l'état
	  return true;}
 return false;
       });
    });
SortedList<Voiture> sortedData= new SortedList<>( listesVfiltre);
sortedData.comparatorProperty().bind(table.comparatorProperty());
table.setItems(sortedData);
});
 }

//we Set Cell Value of TableView To TextField on Mouse Click
public void setCellValueFromTableToTextField()
{ table.setOnMouseClicked(new EventHandler <MouseEvent> () {
		
@Override
public void handle(MouseEvent event)  {
	//afficher les champs d'une ligne dans les textfield
	 Voiture vo =table.getItems().get(table.getSelectionModel().getSelectedIndex());
	 String idvoiture=String.valueOf(vo.getIdv());
	 txtidv.setText(idvoiture);
	 txtmatricule.setText(vo.getMatricule());
	 txtMarque.setText(vo.getMarque());
	 txtCouleur.setValue(vo.getCouleur());
	 txtType.setValue(vo.getType());
	 txtEtat.setValue(vo.getEtat());
	 String prix = Double.toString(vo.getPrice());
	 txtPrice.setText(prix);}
});
}

public void UpdateVoiture (ActionEvent event ) throws IOException, ParseException {

String id= txtidv.getText();
int IDvoiture = Integer.parseInt(id);
String champMatricule=txtmatricule.getText();
String champMarque=(String) txtMarque.getText();
String champCouleur=(String)txtCouleur.getValue();
String champType=(String)txtType.getValue();
String champEtat=(String)txtEtat.getValue();
String champPrix=(String) txtPrice.getText();
if(txtmatricule.getText().isEmpty() || txtMarque.getText().isEmpty() || txtCouleur.getValue().isEmpty() ||txtPrice.getText().isEmpty() || txtType.getValue().isEmpty() || txtEtat.getValue().isEmpty() )
{
	Alert alert= new Alert(AlertType.ERROR);
	alert.setTitle(" Update ");
	alert.setHeaderText("Information");
	alert.setContentText(" Vous devez remplir tous les champs !");
	alert.showAndWait();

}
else {
Voiture vo =table.getItems().get(table.getSelectionModel().getSelectedIndex());
vo.setIdv(IDvoiture);
vo.setMatricule(champMatricule);
vo.setMarque(champMarque);
vo.setCouleur(champCouleur);
vo.setType(champType);
vo.setEtat(champEtat);
Double doprix= Double.parseDouble(champPrix);
vo.setPrice(doprix);
int status = ConnectionBD.Update(vo);
if (status==0)
	   { Alert alert= new Alert(AlertType.INFORMATION);
	    alert.setTitle("Update Voiture ");
	    alert.setHeaderText("Information");
	    alert.setContentText("Update successful !");
        alert.showAndWait();	
     
	   } else {
		 Alert alert= new Alert(AlertType.ERROR);
		 alert.setTitle("Update Voiture !");
		 alert.setHeaderText("Information");
		 alert.setContentText("Update failed, try again!");
	     alert.showAndWait();}
refrechTable();
} }

public void refrechTable()
{   listesVoitures.clear();
	initialize(null,null);}


@FXML
public void deleteVoiture (ActionEvent event ) throws IOException
{   Voiture vo =table.getItems().get(table.getSelectionModel().getSelectedIndex());
	table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
	try {
		String sql="DELETE FROM voiture WHERE idv=?" ;
		ConnectionBD cnx=new ConnectionBD();
		Connection connect=cnx.getConnection();	
		PreparedStatement stat;
		stat= connect.prepareStatement(sql);
		stat.setInt(1,vo.getIdv());
		stat.executeUpdate();
		connect.close();
		
	  } catch (SQLException e) {
		  e.printStackTrace();
	  } 
	 Alert alert= new Alert(AlertType.INFORMATION);
	  alert.setTitle("Delete Car");
	  alert.setHeaderText("Information");
	  alert.setContentText(" Delete successfully !");
	  alert.showAndWait();
  }

public void saveCar (ActionEvent Event ) throws SQLException, IOException
{   

	ConnectionBD cnx=new ConnectionBD();
	Connection connect=cnx.getConnection();	
	String sql="INSERT INTO voiture (`Matricule`,`Marque`,`Couleur`,`Type`,`Etat`,`Price`) VALUES ('"+txtmatricule.getText()+"','"+txtMarque.getText()+"','"+txtCouleur.getValue()+"','"+txtType.getValue()+"','"+txtEtat.getValue()+"','"+txtPrice.getText()+"')";
	if(txtmatricule.getText().isEmpty() || txtMarque.getText().isEmpty() || txtCouleur.getValue().isEmpty() ||txtPrice.getText().isEmpty() || txtType.getValue().isEmpty() || txtEtat.getValue().isEmpty() )
	{
		Alert alert= new Alert(AlertType.ERROR);
		alert.setTitle(" Save Car ");
		alert.setHeaderText("Information");
		alert.setContentText(" Vous devez remplir tous les champs !");
		alert.showAndWait();

	}
	else {
	try {
	Statement statement = connect.createStatement();
		statement.executeUpdate(sql);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}	refrechTable();
	}}

public void handleRetour (MouseEvent Event ) throws IOException
{ Parent fxml = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceAdmin.fxml"));
esp_area.getChildren().removeAll();
esp_area.getChildren().setAll(fxml);}

}


