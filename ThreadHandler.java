import java.io.*;
import java.net.*;
class ThreadHandler extends Thread {
    Socket newsock;
    int n;
    static String phoneNum;
    ThreadHandler(Socket s, int v) {
        newsock = s;
        n = v;
    }

    
    public void run() {

    Thread thread = new Thread(){
    	public void run(){
		try{
		boolean running = true;
		while(running){
    	  	System.out.println("Thread Running " + phoneNum);
		if(phoneNum != null && !phoneNum.isEmpty()){
			if(createConnection(phoneNum).equals("closed")){
				running = false;
			}			
		}	
		sleep(5000);
		}}
	catch(Exception e){
e.printStackTrace();
}	
        }
    };

thread.start();
	    try {
            System.out.println("Server running");
            PrintWriter outp = new PrintWriter(newsock.getOutputStream(), true);
            BufferedReader inp = new BufferedReader(new InputStreamReader(
                    newsock.getInputStream()));
            boolean more_data = true;
            String line;

            while (more_data) {
                line = inp.readLine();
                if (line == null) {
                    System.out.println("line = null");
                    more_data = false;
                } else {
       		  handleInput(line);         

		  if (line.trim().equals("QUIT"))
                        more_data = false;
                }
            }
            newsock.close();
            System.out.println("Disconnected from client number: " + n);
        } catch (Exception e) {
            System.out.println("IO error " + e);
        }

    }
public static void handleInput(String input){
     String split[] = input.split(":");
     String command = split[0];
     String params = split[1];
     switch(command) {
                        case "quit":;
                        break;
			case "phone":
			phoneNum = params;
			System.out.println("SettingPhoneNumber " + phoneNum);	
//		        createConnection(phone);
			break;
                        case "showDB":
			String resp = showDb();
			System.out.println(resp);		
                        break;
			case "updateDB":
			split = params.split(",");
			String email = split[0];
			String phone = split[1];
			String dbResp = updateDb(email, phone);
    			break;
	}


}
public static String showDb(){
	DatabaseManager db = new DatabaseManager();	
	return db.readDB();
}

public static String updateDb(String email, String phone){
	DatabaseManager db = new DatabaseManager();
	return db.updateDB(email, phone);

}

public String createConnection(String phone){
System.out.println("Conncetion beting testing " + phone);
try{	
if(newsock.isClosed()){
	return "closed";
}
PrintWriter outp = new PrintWriter(newsock.getOutputStream(), true);
	DatabaseManager db = new DatabaseManager();
	String connections = db.getConnection(phoneNum);
	System.out.println("Checking for new requests " + connections);
	if(Integer.parseInt(connections) > 0){
		outp.println("newConnection,{'site':'www.shinjo.com'}");
	}
return connections;
} catch(Exception e){
e.printStackTrace();
}
return "sad face" ; 
}

}
