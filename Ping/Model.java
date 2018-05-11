package Ping;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Model {

	private String ipAdd="IPCIM";
	public int Teszttest(int num1, int num2)
	{
		return num1+num2;
	}
	
    
    public String Muvelet(TextField ip1, TextField ip2, TextField ip3, TextField ip4, String Comm)
    {
    	try{
        	if(0<=Integer.parseInt(ip1.getText())&& Integer.parseInt(ip1.getText())<=255
        			&&0<=Integer.parseInt(ip2.getText())&& Integer.parseInt(ip2.getText())<=255
        			&&0<=Integer.parseInt(ip3.getText())&& Integer.parseInt(ip3.getText())<=255
        			&&0<=Integer.parseInt(ip4.getText())&& Integer.parseInt(ip4.getText())<=255
        			){
        	ipAdd=ip1.getText()+ "."+ip2.getText()+ "."+ip3.getText()+ "."+ip4.getText();
        	
        	switch (Comm) {
            case "Ping":
                return ICMP(ipAdd);
            case "TraceRoute":
                return TraceRT(ipAdd);
        	}
        	}
        	else return "Nem megfelelõ IP cím!";
        	}
        	catch(Exception e)
        	{
        		return "Nem Integer számot adtál meg!";
        		}
		return Comm;
    	
    	
    }
    public String ICMP(String ip)
    {
    	 try {
    		 dbsqlconnect con=new dbsqlconnect();
    		 //String ins;
    	      //String ipAddress = "127.0.0.1";
    	      InetAddress inet = InetAddress.getByName(ip);
    	     // System.out.println("Sending Ping Request to " + ip);
    	      if (inet.isReachable(5000)){
    	    	  con.SetData("insert into PingTable (IPcim, Elerhetoe) values ('"+ip+"', '1')" );
    	    	  con.close();
    	        return ("1");
    	      } else {
    	    	  con.SetData("insert into PingTable (IPcim, Elerhetoe) values ('"+ip+"', '0')" );
    	    	  con.close();
    	        return ("0");
    	      }
    	      
    	    } catch ( Exception e ) {
    	      return ("Kivétel: " + e.getMessage());
    	     
    	    }
    	 
    }
    public String TraceRT(String ip)
    {
    	   BufferedReader in;
    	   dbsqlconnect con=new dbsqlconnect();
    	    String outPut="";
           try{
               Runtime r   =   Runtime.getRuntime();
               String command="tracert "+ip;
               
               Process p   =   r.exec(command);

               in  =   new BufferedReader(new InputStreamReader(p.getInputStream()));

               String line;
               
               
               while((line=in.readLine())!=null){
            	   
            	   
            	  
                  outPut = outPut + line + "\n";
            	  
                   
               }

           }catch(IOException e){

           return (e.toString());

           }
           con.SetData("insert into TraceTable (IPcim, Utvonal) values ('"+ip+"', '"+outPut+"')");
		return outPut;
    }
    public void Windows(String button) throws IOException
    {
    	 Parent root;
         Stage primaryStage=new Stage();
          if(button.equals("Lekérdezés")){
          root = FXMLLoader.load(getClass().getResource("ui3.fxml"));
         }
         else root = FXMLLoader.load(getClass().getResource("ui.fxml"));
         primaryStage.setScene(new Scene(root));
         primaryStage.show();
    }
    public String kveri(String Comm) throws SQLException
    {
    	if(Comm.equals("Pingelt címek"))
    	return Q1();
    	else if(Comm.equals("Válaszolt címek"))
    		return Q2();
    		else if(Comm.equals("Nem válaszolt"))
    			return Q3();
    		else if(Comm.equals("TraceRoute címek"))
    			return Q4();
    		else return Q5();
    }
    private String Q1() throws SQLException
    {
    	String querry ="select IPcim from PingTable";
    	String output="";
    	ResultSet rs;
    	dbsqlconnect con=new dbsqlconnect();
    	rs=con.GetData(querry);
    	while (rs.next()) {
            String Ipcim = rs.getString("IPcim");
            output=output + Ipcim + "\n";
            }
    	con.close();
    	return output;
    }
    private String Q2() throws SQLException
    {
    	String querry ="select IPcim from PingTable where elerhetoe='1' ";
    	String output="";
    	ResultSet rs;
    	dbsqlconnect con=new dbsqlconnect();
    	rs=con.GetData(querry);
    	while (rs.next()) {
            String Ipcim = rs.getString("IPcim");
            output=output + Ipcim + "\n";
            }
    	con.close();
    	return output;
    }
    private String Q3() throws SQLException
    {
    	String querry ="select IPcim from PingTable where elerhetoe='0' ";
    	String output="";
    	ResultSet rs;
    	dbsqlconnect con=new dbsqlconnect();
    	rs=con.GetData(querry);
    	while (rs.next()) {
            String Ipcim = rs.getString("IPcim");
            output=output + Ipcim + "\n";
            }
    	con.close();
    	return output;
    }
    private String Q4() throws SQLException
    {
    	String querry ="select IPcim from TraceTable";
    	String output="";
    	ResultSet rs;
    	dbsqlconnect con=new dbsqlconnect();
    	rs=con.GetData(querry);
    	while (rs.next()) {
            String Ipcim = rs.getString("IPcim");
            output=output + Ipcim + "\n";
            }
    	con.close();
    	return output;
    }
    private String Q5() throws SQLException
    {
    	String querry ="select IPcim, Utvonal from TraceTable";
    	String output="";
    	ResultSet rs;
    	dbsqlconnect con=new dbsqlconnect();
    	rs=con.GetData(querry);
    	while (rs.next()) {
            String Ipcim = rs.getString("IPcim");
            String Utvonal = rs.getString("Utvonal");
            
            output=output + Ipcim + "\n" + Utvonal + "\n";
            }
    	con.close();
    	return output;
    }


}