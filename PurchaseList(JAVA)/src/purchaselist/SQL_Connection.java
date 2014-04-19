package purchaselist;

import java.sql.*;

//Return Connection conn!!!

class SQL_Connection
{
    public boolean success = false;
    public String msg = "NULL";
    public Connection conn; //very important object!
    
    private final String Driver = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/purchaselist"; //Editable
    
    public SQL_Connection(String user, String pass)
    {
        try
        {
            //Loading Driver
            Class.forName(Driver);
            //Connect
            conn = DriverManager.getConnection(URL, user, pass);
            //Determine Result
            if (!conn.isClosed())
            {
                success = true;
                msg = "Connection is completed!";
            }
        }
        catch(SQLException e)
        {
            success = false;
            msg = "Connection is failed!";
        }
        catch(ClassNotFoundException e)
        {
            success = false;
            msg = "Loading Driver is failed!";
        } 
    }
}
