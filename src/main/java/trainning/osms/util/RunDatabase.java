package trainning.osms.util;

import org.h2.tools.Server;
import java.io.IOException;
import java.sql.SQLException;

public class RunDatabase {
	
	public static void main(String[] args) throws SQLException, IOException {
		
		runDatabase();
		
		CreateDatabase createDB = new CreateDatabase();
		createDB.populateDatabase();
		
	}
	
	public static void runDatabase() throws SQLException, IOException{
		Server server = Server.createTcpServer(); // Vamos usar um BD que fica escutando a porta TCP
		server.start();
		System.in.read();
		System.out.println("DB successufully started.");
		
		server.stop();
	}
}
