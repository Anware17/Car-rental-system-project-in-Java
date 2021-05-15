package controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import connection.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Client;
import models.Reservation;
public class BillingAdminController  {

	
	@FXML
	public AnchorPane billing_area;
	@FXML
	private Button butprint;
	
	@FXML
	private ComboBox <Integer> chooseClient;

@FXML
public TableView<Reservation> table;

@FXML
public TableColumn<Reservation,Integer> clNumR;

@FXML
public TableColumn<Reservation,Integer> clCIN;

@FXML
public TableColumn<Reservation,String> clMatricule;

@FXML
public TableColumn<Reservation,Date> Date_Deb;

@FXML
public TableColumn<Reservation,Date> Date_fin;
@FXML
public TableColumn<Reservation,String> tabpaiment;
@FXML
public TableColumn<Reservation,Double> tabMontant;
	@FXML
	private Button butaffiche;
	
	private final ObservableList<Reservation> listeFact=FXCollections.observableArrayList();
	
	public void affichetab(ActionEvent Event) throws SQLException{
		
		try{
			  String sql="SELECT * FROM tabreservation WHERE cinclient_fk='"+chooseClient.getValue()+"'";
			  
			  ConnectionBD cnx=new ConnectionBD();
			  Connection connect=cnx.getConnection();
			  PreparedStatement preparedStatement=(PreparedStatement)connect.prepareStatement(sql);
			  ResultSet rs= preparedStatement.executeQuery();
		while (rs.next()){
		listeFact.add( new Reservation(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getString(6),rs.getDouble(7))); }
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
		table.setItems(listeFact);
		
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
	
	
public void printFacture (ActionEvent Event) throws SQLException, IOException {
		
	
		 Stage stage=new Stage();
		   
		 Parent ro = (Parent)FXMLLoader.load(getClass().getResource("/vue/BillingAdmin.fxml"));
		 //   paneprint.getChildren().setAll(ro);
		    // Create the Buttons
		    Button pageSetupButton = new Button("Page Setup and Print");
		     
		    // Create the Event-Handlers for the Button
		    pageSetupButton.setOnAction(new EventHandler <ActionEvent>() 
		    {
		        public void handle(ActionEvent event) 
		        { 
		            pageSetup( ro,stage);
		        }
		    });
		     
		    final Label jobStatus = new Label();
			// Create the Status Box
		    HBox jobStatusBox = new HBox(5, new Label("Job Status: "), jobStatus);
		    // Create the Button Box
		    HBox buttonBox = new HBox(pageSetupButton);
		     
		    // Create the VBox
		    VBox root = new VBox(5);

		    // Add the Children to the VBox     
		    root.getChildren().addAll( buttonBox, jobStatusBox,ro);
		    // Set the Size of the VBox
		    root.setPrefSize(400, 300);     
		     
		    // Set the Style-properties of the VBox
		    root.setStyle("-fx-padding: 10;" +
		            "-fx-border-style: solid inside;" +
		            "-fx-border-width: 2;" +
		            "-fx-border-insets: 5;" +
		            "-fx-border-radius: 5;" +
		            "-fx-border-color: blue;");
		     
		    // Create the Scene
		    Scene scene = new Scene(root);
		    // Add the scene to the Stage
		    stage.setScene(scene);
		    // Set the title of the Stage
		    stage.setTitle("A Printing Dialog Example");
		    // Display the Stage
		    stage.show();       
		}
		 
		private void pageSetup(Node node, Stage owner) 
		{
		    // Create the PrinterJob
		    PrinterJob job = PrinterJob.createPrinterJob();
		     
		    if (job == null) 
		    {
		        return;
		    }
		     
		    // Show the page setup dialog
		    boolean proceed = job.showPageSetupDialog(owner);
		     
		    if (proceed) 
		    {
		        print(job, node);
		    }
		}
		 
		private void print(PrinterJob job, Node node) 
		{final Label jobStatus = new Label();
		    // Set the Job Status Message
		    jobStatus.textProperty().bind(job.jobStatusProperty().asString());
		     
		    // Print the node
		    boolean printed = job.printPage(node);
		 
		    if (printed) 
		    {
		        job.endJob(); }}
	
		public void handletoEspAdmin(MouseEvent Event ) throws IOException 
		{ 
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/vue/EspaceAdmin.fxml"));
			billing_area.getChildren().setAll(root);}

}

