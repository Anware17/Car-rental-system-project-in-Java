package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Reservation;
import models.Voiture;	

public class ConnectionBD {
		public Connection conn=null;
				
		public  Connection getConnection () {
			String user="root";
			String password="";
			String url="jdbc:mysql://localhost/carauto?autoReconnect=true&useSSL=false";
			try { 
		          Class.forName("com.mysql.jdbc.Driver");
		          conn= DriverManager.getConnection(url,user,password);
		          System.out.println("connexion marche bien !");
		          return conn;
		} catch (Exception e ) { 
			e.printStackTrace();}
	return conn;
		}
		
		//idv
/*public static Voiture getVoituresId( int id)
		{ Voiture voiture= new Voiture(id, null, null, null, null, null,null);
		  try {
			String sql="SELECT * FROM `voiture` WHERE idv=?";
			ConnectionBD cnx=new ConnectionBD();
			Connection connect=cnx.getConnection();	
			PreparedStatement stat;
			stat= connect.prepareStatement(sql);  
			stat.setInt(1, id);  
			ResultSet rst= stat.executeQuery();
			if (rst.next()) {
				voiture.setIdv(rst.getInt(1));
				voiture.setMatricule(rst.getString(2));
				voiture.setMarque(rst.getString(3));
				voiture.setCouleur(rst.getString(4));
				voiture.setType(rst.getString(5));
				voiture.setEtat(rst.getString(6));
				voiture.setPrice(rst.getDouble(7));
			} connect.close();
		  } catch (SQLException e)
		  { e.printStackTrace();}
		return voiture;	
		}*/

public static int Update (Voiture veh) {
int st=0;
String sql="UPDATE voiture SET Matricule=?,Marque=?,Couleur=?,Type=?,Etat=?,Price=?  WHERE idv=?";
ConnectionBD cnx=new ConnectionBD();
Connection conn=cnx.getConnection();
try {
	PreparedStatement stat;
	stat= conn.prepareStatement(sql);
	stat.setString(1,veh.getMatricule());
	stat.setString(2,veh.getMarque());
	stat.setString(3,veh.getCouleur());
	stat.setString(4,veh.getType());
	stat.setString(5,veh.getEtat());
	stat.setDouble(6,veh.getPrice());
	stat.setInt(7,veh.getIdv()); 
	stat.executeUpdate();
    conn.close();
    } catch (SQLException e) {
          e.printStackTrace();}
return st; 
    }

public static int Update_reservation (Reservation res) {
int st=0;
String sql="UPDATE tabreservation SET cinclient_fk=?,Matricule_fk=?,DateDebut=?,DateFin=?,TypePaiment=?,Montant=?  WHERE NumRes=?";
ConnectionBD cnx=new ConnectionBD();
Connection conn=cnx.getConnection();
try {
	PreparedStatement stat;
	stat= conn.prepareStatement(sql);
	stat.setInt(1,res.getCinclient_fk());
	stat.setString(2,res.getMatricule_fk());
	stat.setDate(3,res.getDateDebut());
	stat.setDate(4,res.getDateFin());
	stat.setString(5,res.getTypePaiment());
	stat.setDouble(6,res.getMontant());
	stat.setInt(7,res.getNumRes()); 
	stat.executeUpdate();
    conn.close();
    } catch (SQLException e) {
          e.printStackTrace();}
return st; 
    }


}

