package purchaselist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class MainFrame extends JFrame {
    final public String[] COLUMN_NAME = {"ID", "Seconds", "Currency", "TrackingNumber",
        "ExpressCompany", "Expressage", "Title", "Category",
        "Producer", "Price", "AdditionalFee", "Amount",
        "Importance", "Conditions", "Bought", "URL", "Info"};
    final public String[] ImportanceText = {"Not Important", "Normal", "Important", "Extremely Important", "Other"};
    final public String[] ConditionsText = {"Damaged", "Normal Used", "Almost New", "New", "Other"};
    final public String[] BoughtText = {"Not Bought", "Already Bought"};
    private JTable MainTable;
    final private SQL_Connection sql_conn;
    final private SQL_Process sql_pro;
    private Data_Package data_package;
    private Query_Package query_package;
    private int SelectedRowIndex; //Maybe use "long"
    private DefaultTableModel TableModel;
    
    public MainFrame(SQL_Connection sql_conn) {
        //Must passed in SQL_Connection
        this.sql_conn = sql_conn;
        this.sql_pro = new SQL_Process(sql_conn.conn);
        this.Initialize();
    }

    public MainFrame(SQL_Connection sql_conn, Query_Package query_Package) {
        //Must passed in SQL_Connection
        this.sql_conn = sql_conn;
        this.query_package = query_Package;
        this.sql_pro = new SQL_Process(sql_conn.conn);
        this.Initialize();
    }

    private void Initialize() {
        //Initialize Variable
        SelectedRowIndex = -1;
        //Get ContentPane
        Container ContentPane = getContentPane();
        ContentPane.setLayout(new BorderLayout());
        //Add Table
        TableModel = new DefaultTableModel(null, COLUMN_NAME);
        MainTable = new JTable(TableModel) {
            @Override
            public boolean isCellEditable(int row, int coloum) //Set Table uneditable
            {
                return false;
            }
        };
        MainTable.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        MainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        MainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Add SrollPane
        JScrollPane table_scrollpane = new JScrollPane(MainTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add Button
        JPanel mainframebutton = new MainFrameButton(this);
        //Add all the components to ContentPane
        ContentPane.add(table_scrollpane, BorderLayout.NORTH);
        ContentPane.add(mainframebutton, BorderLayout.SOUTH);
        //Add Listeners
        MainTable.addMouseListener(new MainFrameMouse(this, MainTable));
        this.addWindowListener(new MainFrameAdapter(this));
    }

    public SQL_Connection getSQL_Connection() {
        return sql_conn;
    }

    public SQL_Process getSQL_Process() {
        return sql_pro;
    }

    public void setQueryPackage(Query_Package query_package) {
        //After query, use this function to pass in the data.
        this.query_package = query_package;
    }

    public Query_Package getQueryPackage() {
        return query_package;
    }

    public int getSelectedRowIndex() {
        return SelectedRowIndex;
    }

    public void setSelectedRowIndex(int SelectedRowIndex) {
        this.SelectedRowIndex = SelectedRowIndex;
    }
    
    public long getIDFromTable(int SelectedRowIndex){
        if(SelectedRowIndex < 0)
        {
            return -1; //SelectedRowIndex Invalid
        }
        else
        {
            Object Temp_ID = MainTable.getValueAt(SelectedRowIndex, 0);
            try
            {
                return Long.parseLong(Temp_ID.toString());    
            }
            catch(NumberFormatException e)
            {
                return -2; //NumberFormatException
            }
        }
    }
    
    public boolean RemoveTableRow(int SelectedRowIndex)
    {
        try
        {
            TableModel.removeRow(SelectedRowIndex);
            return true;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
    }    
    public void RefreshTable() {
        //Refresh Table Content
        //Case 1 : If query_package is null pointer or empty
        if (null == query_package || 0 == query_package.GetAmount()) 
        {
            //Delete all data in the Table
            while (TableModel.getRowCount() > 0)
            {
                TableModel.removeRow(0);
            }
            SelectedRowIndex = -1; //Set Selected Row Index = -1
        } 
        //Case 2: There are some data
        else 
        {
            //Delete all data in the Table
            while (TableModel.getRowCount() > 0)
            {
                TableModel.removeRow(0);
            }
            //Add data
            Object[] Temp = new Object[17];
            Data_Package[] Temp_Data_Package = query_package.GetData();
            for (int i = 0; i < (int) query_package.GetAmount(); i++) //UNSAFE!!!!
            {
                Temp[0] = Temp_Data_Package[i].ID;
                Temp[1] = Temp_Data_Package[i].year + "-" + Temp_Data_Package[i].month + "-" + Temp_Data_Package[i].day;
                Temp[2] = Temp_Data_Package[i].Currency;
                Temp[3] = Temp_Data_Package[i].TrackingNumber;
                Temp[4] = Temp_Data_Package[i].ExpressCompany;
                Temp[5] = Temp_Data_Package[i].Expressage;
                Temp[6] = Temp_Data_Package[i].Title;
                Temp[7] = Temp_Data_Package[i].Category;
                Temp[8] = Temp_Data_Package[i].Producer;
                Temp[9] = Temp_Data_Package[i].Price;
                Temp[10] = Temp_Data_Package[i].AdditionalFee;
                Temp[11] = Temp_Data_Package[i].Amount;
                Temp[12] = ImportanceText[Temp_Data_Package[i].Importance - 1];
                Temp[13] = ConditionsText[Temp_Data_Package[i].Conditions - 1];
                Temp[14] = BoughtText[Temp_Data_Package[i].Bought];
                Temp[15] = Temp_Data_Package[i].URL;
                Temp[16] = Temp_Data_Package[i].Info;
                TableModel.addRow(Temp); //Add into TableModel
            }
            SelectedRowIndex = -1; //Set Selected Row Index = -1
        }
    }
}

class MainFrameButton extends JPanel implements ActionListener {

    final private MainFrame F;
    private QueryFrame queryframe;
    private AddFrame addframe;
    private EditFrame editframe;
    
    public MainFrameButton(MainFrame F) {
        //SubFrame Initialize
        queryframe = null;
        addframe = null;
        editframe = null;
        
        //Layout
        setLayout(new FlowLayout());
        //Components(Buttons)
        JButton b_query = new JButton("Query");
        JButton b_add = new JButton("Add");
        JButton b_edit = new JButton("Edit");
        JButton b_del = new JButton("Delete");
        JButton b_copy = new JButton("Copy");
        JButton b_logout = new JButton("Log Out");
        add(b_query);
        add(b_add);
        add(b_edit);
        add(b_del);
        add(b_copy);
        add(b_logout);
        //Listener
        b_query.addActionListener(this);
        b_add.addActionListener(this);
        b_edit.addActionListener(this);
        b_del.addActionListener(this);
        b_copy.addActionListener(this);
        b_logout.addActionListener(this);
        //Initialize other variable
        this.F = F;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String Command = ae.getActionCommand();
        switch (Command) {
            case "Query":
                if (null == queryframe) //Never create a Query Frame
                {
                    queryframe = new QueryFrame(F);
                    queryframe.setVisible(false);
                    queryframe.setSize(500, 800);
                    queryframe.setLocation(600, 0);
                    queryframe.setResizable(false);
                    queryframe.setVisible(true);
                }
                else //UNSAFE!!!
                {
                    queryframe.setVisible(true);
                }
                break;
            case "Add":
                if (null == addframe) //Never create an Add Frame
                {
                    addframe = new AddFrame(F);
                    addframe.setVisible(false);
                    addframe.setSize(500, 800);
                    addframe.setLocation(600, 0);
                    addframe.setResizable(false);
                    addframe.setVisible(true);
                }
                else //UNSAFE!!!
                {
                    addframe.setVisible(true);
                }
                break;
            case "Edit":
                if (-1 == F.getSelectedRowIndex()) //No row is selected
                {
                    JOptionPane.showMessageDialog(this, "No row is selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    editframe = new EditFrame(F, F.getSelectedRowIndex());
                    editframe.setVisible(false);
                    editframe.setSize(500, 800);
                    editframe.setLocation(600, 0);
                    editframe.setResizable(false);
                    editframe.setVisible(true);
                }
                break;
            case "Delete":
                //MUST SYNCHRONIZE WITH TABLE
                if (-1 == F.getSelectedRowIndex()) //No row is selected
                {
                    JOptionPane.showMessageDialog(this, "No row is selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int Confirmation;
                    Confirmation = JOptionPane.showConfirmDialog(this, "Are you sure to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (0 == Confirmation) //Yes
                    {
                        SQL_Process sql_pro = F.getSQL_Process();
                        boolean DeleteResult = sql_pro.DeleteRecord(F.getIDFromTable(F.getSelectedRowIndex()));
                        if (true == DeleteResult) //Success
                        {
                            //Synchronize with table
                            boolean removetablerow;
                            removetablerow = F.RemoveTableRow(F.getSelectedRowIndex());
                            if (false == removetablerow) JOptionPane.showMessageDialog(this, "Synchronize with table failed! Please use Query to update table!", "Error", JOptionPane.ERROR_MESSAGE);
                            //Show Success Message
                            JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Connection failed! (Part: deleting Record!)", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                break;
            case "Copy":
                if (-1 == F.getSelectedRowIndex()) //No row is selected
                {
                    JOptionPane.showMessageDialog(this, "No row is selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int Confirmation;
                    Confirmation = JOptionPane.showConfirmDialog(this, "Are you sure to copy this record?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (0 == Confirmation) //Yes
                    {
                        SQL_Process sql_pro = F.getSQL_Process();
                        boolean CopyResult = sql_pro.CopyRecord(F.getSelectedRowIndex());
                        if (true == CopyResult) //Success
                        {
                            JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Connection failed! (Part: copying Record!)", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                break;
            case "Log Out":
                System.exit(0);
                break;
            default:
                //Should not run to here!
                break;
        }
    }
}

class MainFrameMouse extends MouseAdapter {

    //Because of using Swing, use capitalized character
    final private MainFrame F;
    final private JTable T;
    private int RowIndex; //Maybe use "long"

    public MainFrameMouse(MainFrame F, JTable T) {
        this.F = F;
        this.T = T;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //DoubleClick
        if (2 == me.getClickCount()) {
            RowIndex = T.getSelectedRow();
            F.setSelectedRowIndex(RowIndex);
            //Start Editor
            EditFrame editframe = new EditFrame(F, F.getSelectedRowIndex());
            editframe.setVisible(false);
            editframe.setSize(500, 800);
            editframe.setLocation(600, 0);
            editframe.setResizable(false);
            editframe.setVisible(true);
        } else //Others, just set the RowIndex
        {
            RowIndex = T.getSelectedRow();
            F.setSelectedRowIndex(RowIndex);
        }
    }
}

class MainFrameAdapter extends WindowAdapter {

    final private JFrame F;

    public MainFrameAdapter(JFrame F) {
        this.F = F;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        F.setVisible(true);
        System.exit(0);
    }

}
