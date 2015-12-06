import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.UUID;
// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

class DatabaseManager {



public String readDB(){    
       	System.out.println("READDB");
	 try {
            // The newInstance() call is a work around for some
            // broken Java implementationi

Class.forName("com.mysql.jdbc.Driver");
Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "symelosh");
//Statement stat = myConn.createStatement();

	    Statement stat = myConn.createStatement();
	    ResultSet res = stat.executeQuery("SELECT * FROM tokenRequests");
	    String retVal = "";
	    while(res.next()){
		retVal += res.getString("email") + " , " + res.getString("phone");
	    }
	    return retVal;
           
        } catch (Exception ex) {
		System.out.println("ERROR");
		ex.printStackTrace();           
       }
    	return "noething";
    }

public String updateDB(String email, String phone){
System.out.println("Updating Database");  
         try {
            // The newInstance() call is a work around for some
            // broken Java implementationi


Class.forName("com.mysql.jdbc.Driver");
Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "symelosh");
//Statement stat = myConn.createStatement();

            Statement stat = myConn.createStatement();
	    UUID uuid = UUID.randomUUID();
	    String randomUUIDString = uuid.toString();
	    String query = " UPDATE tokenRequests SET tokenRequests.token='" + randomUUIDString  + "' WHERE tokenRequests.email='" + email  + "' AND tokenRequests.phone='" + phone  + "';";
	    System.out.println(query);
	    int res =  stat.executeUpdate(query);
            System.out.println(res);
	    String retVal = Integer.toString(res);
        } catch (Exception ex) {
                System.out.println("ERROR");
                ex.printStackTrace();
       }
        return "noething";
    }
public String getConnection(String phone){
   try {
            // The newInstance() call is a work around for some
            // broken Java implementationi

Class.forName("com.mysql.jdbc.Driver");
Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "symelosh");
//Statement stat = myConn.createStatement();

            Statement stat = myConn.createStatement();
            ResultSet res = stat.executeQuery("SELECT * FROM tokenRequests WHERE tokenRequests.phone='" + phone + "';");
            String retVal = "";
	    int itter = 0;
            while(res.next()){
		itter++;
                retVal += res.getString("email") + " , " + res.getString("phone");
            }
            return Integer.toString(itter);

        } catch (Exception ex) {
                System.out.println("ERROR");
                ex.printStackTrace();
       }
        return "nothing";

}
}
