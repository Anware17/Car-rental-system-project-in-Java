package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController {
public Pane txtpane;
public TextField txtname;
public TextField txtpassword;
public RadioButton radio1;
public RadioButton radio2;
public Pane pane_area;

public void Button (ActionEvent actionEvent) throws SQLException, IOException {
	
// bouton Login est en action !
ConnectionBD cnx=new ConnectionBD();
Connection connect=cnx.getConnection();
if (radio1.isSelected() && radio2.isSelected()==false) { // c à d l'utilisateur est un admin donc on vérifie l'existance du mot de passe dans la table admintab
	String testname=txtname.getText();
	String testcode=txtpassword.getText();
    Statement stat=connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	

// Vérifier si le nom et password existent dans la table "Admin"
ResultSet rs=stat.executeQuery("SELECT * FROM `admintab` WHERE name LIKE '"+testname+"' AND password LIKE '"+testcode+"'");
if(rs.next())
{ //  Aller vers Espace Admin 
	Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceAdmin.fxml"));
	pane_area.getChildren().setAll(root);
}	 
else
{ Alert alert= new Alert(AlertType.ERROR);
alert.setTitle(" Login ");
alert.setHeaderText("Information");
alert.setContentText(" Welcome Admin : Verify the name user or password !");
alert.showAndWait();
	}
}

if (radio2.isSelected() && radio1.isSelected()==false) { // c à d l'utilisateur est un client donc on vérifie l'existance du mot de passe et name dans la table customer tab
	String namec=txtname.getText();
	String codec=txtpassword.getText();
    Statement stat3=connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	

// Vérifier si le nom et password existent dans la table "customertab"
ResultSet rs1=stat3.executeQuery("SELECT * FROM `customertab` WHERE user LIKE '"+namec+"' AND password LIKE '"+codec+"'");
if(rs1.next())
{ // Espace client
	Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceCustomer.fxml"));
	pane_area.getChildren().setAll(root);}

else
{ Alert alert= new Alert(AlertType.ERROR);
alert.setTitle(" Login ");
alert.setHeaderText("Information");
alert.setContentText(" Welcome Customer :Verify the name user or password !");
alert.showAndWait();
	}
}
if (radio1.isSelected()==false && radio2.isSelected()== false ) // test si aucun des boutons n'est sélectionnés 
{Alert alert= new Alert(AlertType.ERROR);
alert.setTitle(" Login ");
alert.setHeaderText("Information");
alert.setContentText(" You should select one of the buttons, Admin or Customer !");
alert.showAndWait();
	}
if (radio1.isSelected()== true  && radio2.isSelected()== true ) // test les 2 boutons sont sélectionnés 
{Alert alert= new Alert(AlertType.ERROR);
	alert.setTitle(" Login ");
	alert.setHeaderText("You should choose one of the buttons, Admin or Customer !");
	alert.showAndWait();}
}


public void handle (ActionEvent actionevent ) throws IOException 
{   Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/SignUp.fxml"));  
    pane_area.getChildren().setAll(root);
	}


}


	
