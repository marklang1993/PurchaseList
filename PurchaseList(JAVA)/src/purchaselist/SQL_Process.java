package purchaselist;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 
class Data_Package
{
    public long ID;
    public long Seconds;
    public String Currency;
    public String TrackingNumber;
    public String ExpressCompany;
    public double Expressage;
    public String Title;
    public String Category;
    public String Producer;
    public double Price;
    public double AdditionalFee;
    public long Amount;
    public int Importance;
    public int Conditions;
    public int Bought;
    public String URL;
    public String Info;
    
    public int year;
    public int month;
    public int day;
    
    public void DatetoSeconds(int year, int month, int day)
    {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //Change to String
        time += year;
        if (month < 10) time = time + "0" + month;
        else time += month;
        if (day < 10) time = time + "0" + day;
        else time += day;
        
        try
        {
            this.Seconds = sdf.parse(time).getTime();
        }
        catch (ParseException e) //Format Error
        {
            this.Seconds = 0;
        }
    }
    
    public void SecondstoDate(long Seconds)
    {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(Seconds);
        
        time = sdf.format(date);
        
        year = new Integer(time.substring(0, 4)).intValue();
        month = new Integer(time.substring(4, 6)).intValue();
        day = new Integer(time.substring(6, 8)).intValue();
    }
}

class Query_Package
{
    private ArrayList <Data_Package> Data; //Declare data type
    public Query_Package()
    {
        //Creat a new Query_Package
        Data = new ArrayList();
    }
    public long GetAmount()
    {
        return Data.size();
    }
    public Data_Package[] GetData()
    {
        if(0 == this.GetAmount())
        {
            return null; //when size = 0, return null
        }
        else
        {
            return Data.toArray(new Data_Package[Data.size()]);
        }
    }
    public void AddData(Data_Package data)
    {
        Data.add(data);
    }
    public void RemoveData(int index)
    {
        Data.remove(index);
    }
}
//###########################################################################

class SQL_Process
{
    private Connection conn;
    private boolean setup = true;
    private Statement statement;
    private String sql;
    
    public SQL_Process(Connection conn) 
    {
        this.conn = conn;
    }
    public long QueryMaxID()
    {
        try
        {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT MAX(ID) FROM list1;");
            resultset.next(); //Very important!!!
            return resultset.getLong("MAX(ID)");
        }
        catch(SQLException e)
        {
            return -1; //Error returns -1!!!
        }
    }
    public boolean AddRecord(Data_Package data) //Add new Record
    {
        //#########Highly Recommend to use QueryMaxID() as data.ID!!!!!!!! But need to check whether there exists QueryMaxID() or not!
        sql = "INSERT INTO list1 VALUES(" + data.ID + "," + data.Seconds + ",\"" + data.Currency + "\",\"" + data.TrackingNumber + "\",\""
                + data.ExpressCompany + "\"," + data.Expressage + ",\"" + data.Title + "\",\"" + data.Category + "\",\"" + data.Producer + "\","
                + data.Price + "," + data.AdditionalFee + "," + data.Amount + "," + data.Importance + "," + data.Conditions + ","
                + data.Bought + ",\"" + data.URL + "\",\"" + data.Info + "\");";
        try
        {
            //System.out.println(sql);
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }    
    }
    public boolean CopyRecord(long ID)
    {
        //Use QueryMaxID() to get ID for new copyed record
        Query_Package Copyed = QueryRecord(ID);
        if (null == Copyed) return false; //Failed to get the original one
        //Get ID for NewData
        long NewID = QueryMaxID() + 1; //IMPORTANT TO ADD 1
        if (-1 == NewID) return false; //Failed to get ID
        //Get Original Data
        Data_Package OriginalData[] = Copyed.GetData();
        //Since there should be only one record in this Data_Package array, just use index = 0
        Data_Package NewData = OriginalData[0];
        //Change ID for NewData
        NewData.ID = NewID;
        //Add
        return AddRecord(NewData);
    }
    public boolean ModifyRecord(long ID, Data_Package data) //Modify old Record
    {
        //The ID for new one is the ID passed in (Since modifying ID by users is forbidden)
        if (false == DeleteRecord(ID)) return false; //fail to delete the original one, return without doing anything
        //Delete successfully, then Add the modified one
        data.ID = ID; //Put the old ID
        return AddRecord(data); //Return the state of adding the modified one, return
    }
    public boolean DeleteRecord(long ID[])
    {
        //Delete based on Array ID
        //Realized by DeleteRecord(long ID), no need to write try...catch...
        boolean Total_state = true; //the state for this batch delete
        for(int i = 0; i < ID.length; i++)
        {
            boolean state;
            state = this.DeleteRecord(i);
            if (false == state) Total_state = false;//if fail to delete this
        }
        return Total_state; //One or more records cannot be deleted, return false; Or return true.
    }
    public boolean DeleteRecord(long ID)
    {
        //Delete based on ID
        try
        {
            this.sql = "DELETE FROM list1 WHERE ID = " + ID + " ;";
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    public Query_Package QueryRecord(Data_Package data)
    {
        //Accurate one WITHOUT ID
        //Realized by QuaryRecord(String sql), no need to write try...catch...
        Query_Package Query;
        /*
        this.sql = "SELECT * FROM list1 WHERE Seconds = " + data.Seconds + " AND Currency = \"" + data.Currency + "\""
                + " AND TrackingNumber = \"" + data.TrackingNumber + "\" AND ExpressCompany = \"" + data.ExpressCompany + "\" AND Expressage = " + data.Expressage
                + " AND Title = \"" + data.Title + "\" AND Category = \"" + data.Category + "\" AND Producer = \"" + data.Producer + "\" AND Price = " + data.Price 
                + " AND AdditionalFee = " + data.AdditionalFee + " AND Amount = " + data.Amount + " AND Importance = " + data.Importance
                + " AND Conditions = " + data.Conditions + " AND Bought = " + data.Bought + " AND URL = \"" + data.URL + "\" AND Info = \"" + data.Info + "\";"; 
        */
        this.sql = "SELECT * FROM list1 WHERE Seconds = " + data.Seconds + " AND Currency LIKE \"" + data.Currency + "\""
                + " AND TrackingNumber LIKE \"" + data.TrackingNumber + "\" AND ExpressCompany LIKE \"" + data.ExpressCompany + "\" AND Expressage = " + data.Expressage
                + " AND Title LIKE \"" + data.Title + "\" AND Category LIKE \"" + data.Category + "\" AND Producer LIKE \"" + data.Producer + "\" AND Price = " + data.Price 
                + " AND AdditionalFee = " + data.AdditionalFee + " AND Amount = " + data.Amount + " AND Importance = " + data.Importance
                + " AND Conditions = " + data.Conditions + " AND Bought = " + data.Bought + " AND URL LIKE \"" + data.URL + "\" AND Info LIKE \"" + data.Info + "\";"; 
        Query = QueryRecord(this.sql);
        return Query;
    }
    public Query_Package QueryRecord(long ID)
    {
        //Accurate one WITH ID
        //Realized by QuaryRecord(String sql), no need to write try...catch...
        Query_Package Query;
        this.sql = "SELECT * FROM list1 WHERE ID = " + ID + ";"; 
        Query = QueryRecord(this.sql);
        return Query;
    }
    public Query_Package QueryRecordWithCondition(String Condition)
    {
        //Accurate one WITH ID
        //Realized by QuaryRecord(String sql), no need to write try...catch...
        Query_Package Query;
        this.sql = "SELECT * FROM list1 WHERE " + Condition + ";"; 
        Query = QueryRecord(this.sql);
        return Query;
    }
    public Query_Package QueryRecord(String sql)
    {
        Query_Package Query;
        //General one
        try
        {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(sql); //NOT using this.sql
            Query = new Query_Package(); //Create a new Query_Package;
            while(resultset.next()) //Determine whether does next data exist
            {
                //Get one record
                Data_Package Data = new Data_Package();
                Data.ID = resultset.getLong("ID");
                Data.Seconds = resultset.getLong("Seconds");
                Data.Currency = resultset.getString("Currency");
                Data.TrackingNumber = resultset.getString("TrackingNumber");
                Data.ExpressCompany = resultset.getString("ExpressCompany");
                Data.Expressage = resultset.getDouble("Expressage");
                Data.Title = resultset.getString("Title");
                Data.Category = resultset.getString("Category");
                Data.Producer = resultset.getString("Producer");
                Data.Price = resultset.getDouble("Price");
                Data.AdditionalFee = resultset.getDouble("AdditionalFee");
                Data.Amount = resultset.getLong("Amount");
                Data.Importance = resultset.getInt("Importance");
                Data.Conditions = resultset.getInt("Conditions");
                Data.Bought = resultset.getInt("Bought");
                Data.URL = resultset.getString("URL");
                Data.Info = resultset.getString("Info");
                
                Data.SecondstoDate(Data.Seconds); //Already changed to Date
                Query.AddData(Data);
            }
            //If there is no data, the size of Query is 0.
            return Query;
        }
        catch(SQLException e)
        {
            return null;
        }
    }
}
