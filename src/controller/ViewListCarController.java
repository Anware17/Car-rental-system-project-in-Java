package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Voiture;

public class ViewListCarController implements Initializable {
   

    @FXML
    private TableView<Voiture> table;

    @FXML
    private TableColumn<Voiture,String> MatriculeV;

    @FXML
    private TableColumn<Voiture,String> MarqueV;

    @FXML
    private TableColumn<Voiture,String> CouleurV;
    
    @FXML
    private TableColumn<Voiture,String> TypeV;
    
    @FXML
    private TableColumn<Voiture,Double> PriceV;
    
    private final ObservableList<Voiture> listesVoitures=FXCollections.observableArrayList();
	@FXML
	private AnchorPane area_view_car;

    @Override

//Afficher le tableau de la base de donnée sur la tableview     
public void initialize(URL location, ResourceBundle resources) {
try{
  String sql="SELECT Matricule,Marque,Couleur, Type, Price FROM voiture WHERE Etat='Disponible' ";
  ConnectionBD cnx=new ConnectionBD();
  Connection connect=cnx.getConnection();
  PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
  ResultSet rs= preparedStatement.executeQuery();
while (rs.next()){
listesVoitures.add( new Voiture(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5))); }
connect.close();
}catch(SQLException e){
e.printStackTrace();}
		
MatriculeV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Matricule"));
MarqueV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Marque"));
CouleurV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Couleur"));
TypeV.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Type"));
PriceV.setCellValueFactory(new PropertyValueFactory<Voiture,Double>("Price"));
table.setItems(listesVoitures);
 }
  
    public void   handletoEspClient (MouseEvent Event ) throws IOException
    { Parent fxml = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceCustomer.fxml"));
    area_view_car.getChildren().removeAll();
    area_view_car.getChildren().setAll(fxml);}
}
