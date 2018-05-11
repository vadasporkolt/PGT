package Ping;


import java.sql.*;


public class dbsqlconnect {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    private String dbconn="jdbc:oracle:thin:********";
    private String felhasz="******";
    private String jelszo="*****";
    public dbsqlconnect()
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
          
            con = DriverManager.getConnection(dbconn,felhasz,jelszo);
            st=con.createStatement();
            
            
          
        }
        catch(Exception e){System.out.println("Hiba az adatbazis kapcsolatban!");};
    }
    public ResultSet GetData(String utasitas)
    {
        try
        {
          
            rs=st.executeQuery(utasitas);
          
            
        }catch(Exception e)
        {
            System.out.println(e+"getdata");
        }
        return rs;
        
    }
    public void SetData(String kveri)
    {
        try{
        st.executeUpdate(kveri);
        
        }
        catch(Exception ex2)
        {System.out.println(ex2+"Setdata");}
    }
    public void create() throws SQLException 
    {
        String cr="create table PingTable(IPcim VARCHAR(30) not null primary key, Elerhetoe VARCHAR(3))";
        String cr2="create table TraceTable(IPcim VARCHAR(30) not null primary key, Utvonal VARCHAR(4000))";
        
         
        
        try {
            st.executeUpdate(cr);
            
        } catch (SQLException ex) {
            System.out.println(" Pingtabla mar letezik:"+ ex);
        }
        try {
            
            st.executeUpdate(cr2);
        } catch (SQLException ex) {
            System.out.println(" tracetabla mar letezik :"+ ex);
        }
    }
    public void drop()
    {
    	 String cr="drop table PingTable";
         String cr2="drop table TraceTable";
         
          
         
         
         //System.out.println("siker");
         
         
         try {
             st.executeUpdate(cr);
             
         } catch (SQLException ex) {
             System.out.println(" Pingtabla nem letezik:"+ ex);
         }
         try {
             
             st.executeUpdate(cr2);
         } catch (SQLException ex) {
             System.out.println(" tracetabla nem letezik :"+ ex);
         }
    }
    public void close() throws SQLException
    {
        st.close();
        con.close();
    }
   
}
