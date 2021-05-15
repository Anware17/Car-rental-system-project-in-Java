package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class SignUpController {
	
	@FXML
	private TextField nameup;
	@FXML
	private TextField lnameup;
	@FXML
	private TextField cinup;
	@FXML
	private TextField adressup;
	@FXML
	private TextField phoneup;
	@FXML
	private TextField mailup;
	@FXML
	private TextField txtid;
	@FXML
	private TextField passwordup;
	@FXML
	private TextField Npermisup;
	
	@FXML
	private Label lblerrors;
	@FXML
	private RadioButton radAdmin;
	@FXML
	private RadioButton radCustmor;
	@FXML
	private Button butregister;
	@FXML
	private Button butcancel;
	@FXML
	private Button butwelcom;
	@FXML
	private Pane txtpane;
	
	public void SignupButton (ActionEvent actionevent ) throws SQLException, IOException
//  bouton Sign Up est en action => passage à l'interface d'inscription
{ ConnectionBD cnx=new ConnectionBD();
 Connection connect=cnx.getConnection();
Integer numPhone= Integer.parseInt(phoneup.getText());
Integer numCIN= Integer.parseInt(cinup.getText());
if ( getlongeur(numPhone)!=8 || getlongeur(numCIN) !=8) // si CIN ou numéro téléphone a moins de 8 chiffres
 {   Alert alert= new Alert(AlertType.ERROR);
	 alert.setTitle(" Sign up ");
	 alert.setHeaderText("Information");
	 alert.setContentText(" Phone number or CIN shoud contain exactly 8 numbers !");
     alert.showAndWait();
 }
else if( passwordup.getText().length() <6 ) //si le mot de passe est faible, pas d'inscription=> il faut un MDP fort pour valider l'inscri
{ lblerrors.setTextFill(Color.TOMATO);
lblerrors.setText("Password is weak, write a minimum 6 different characters");}


else if(nameup.getText().isEmpty() || lnameup.getText().isEmpty() || cinup.getText().isEmpty() ||Npermisup.getText().isEmpty() || mailup.getText().isEmpty() || adressup.getText().isEmpty() || phoneup.getText().isEmpty() || passwordup.getText().isEmpty() )
{  Alert alert= new Alert(AlertType.ERROR);
alert.setTitle(" Sign up ");
alert.setHeaderText("Information");
alert.setContentText(" Vous devez remplir tous les champs !");
alert.showAndWait();
	}
else { if (nameup.getText() !=null && lnameup.getText()!=null && cinup.getText() !=null && Npermisup.getText()!=null && mailup.getText()!=null && adressup.getText()!=null && phoneup.getText()!=null && passwordup.getText()!=null )
{	// on met name & password dans le tableau client/admin selon radio bouton
if (radAdmin.isSelected()==true && radCustmor.isSelected()==false) // on stocke le MDP et name dans admintab
{ String sql1="INSERT INTO ADMINTAB (`name`,`password`) VALUES ('"+nameup.getText()+"','"+passwordup.getText()+"')";
	  Statement stat=connect.createStatement();	
	  stat.executeUpdate(sql1);
	  // enregistrement de tous les données dans la table admin
	  String sql="INSERT INTO `table_admin` (`name`,`last name`,`cin`,`NumPermis`,`adress`,`phone`,`email`,`password`) VALUES ('"+nameup.getText()+"','"+lnameup.getText()+"','"+cinup.getText()+"','"+Npermisup.getText()+"','"+adressup.getText()+"','"+phoneup.getText()+"','"+mailup.getText()+"','"+passwordup.getText()+"')";
	  Statement statement=connect.createStatement();	
	  statement.executeUpdate(sql);
	  Alert alert= new Alert(AlertType.ERROR);
	  alert.setTitle(" Register");
	  alert.setHeaderText("Register succsessfully");
	  alert.showAndWait();}

if(radCustmor.isSelected()==true && radAdmin.isSelected()==false)
		//on enregistre les données dans la table customertab	
{ String sql2="INSERT INTO CUSTOMERTAB (`user`,`password`) VALUES ('"+nameup.getText()+"','"+passwordup.getText()+"')";
	  Statement stat2=connect.createStatement();	
	  stat2.executeUpdate(sql2);
	// enregistrement de tous les données dans la table table_client

	String sql="INSERT INTO `table_client` (`nom`,`prenom`,`cinclient`,`numpermis`,`adresse`,`numphone`,`mail`,`password`) VALUES ('"+nameup.getText()+"','"+lnameup.getText()+"','"+cinup.getText()+"','"+Npermisup.getText()+"','"+adressup.getText()+"','"+phoneup.getText()+"','"+mailup.getText()+"','"+passwordup.getText()+"')";
	Statement statement=connect.createStatement();	
	statement.executeUpdate(sql);
	Alert alert= new Alert(AlertType.ERROR);
	alert.setTitle(" Register");
	alert.setHeaderText("Register succsessfully");
	alert.showAndWait();
}
if (radAdmin.isSelected()== false && radCustmor.isSelected()== false ) // test si aucun des boutons n'est sélectionnés 
{Alert alert= new Alert(AlertType.ERROR);
	alert.setTitle(" Login ");
	alert.setHeaderText("Information");
	alert.setContentText(" You should select one of the buttons, Admin or Customer !");
	alert.showAndWait();}
if (radAdmin.isSelected()== true && radCustmor.isSelected()== true ) // test les 2 boutons sont sélectionnés 
{Alert alert= new Alert(AlertType.ERROR);
	alert.setTitle(" Login ");
	alert.setHeaderText("You should choose one of the buttons, Admin or Customer !");
	alert.showAndWait();
	}

}}}
public void welcome (ActionEvent actionevent ) throws SQLException, IOException {
//je passe ici a l'inerface suivante admin ou client selon la radiobouton
if (radAdmin.isSelected()== true && radCustmor.isSelected()== false )
{ Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceAdmin.fxml"));
txtpane.getChildren().setAll(root);
	}

if (radAdmin.isSelected()== false && radCustmor.isSelected()== true )
{Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceCustomer.fxml"));
txtpane.getChildren().setAll(root);}
}
public void CancelButton (ActionEvent actionevent) throws SQLException
{ System.exit(0);
	}

public int getlongeur(int x)
{ int num = String.valueOf(x).length();
return num;}




}
