package purchaselist;

import java.awt.event.*;
import java.awt.*;

//Return SQL_Connection conn!!!
public class login extends Frame implements ActionListener {

    private SQL_Connection conn; //very important object!

    private Button b_login, b_exit;
    private TextField username, password;
    private Label username_l, password_l;

    private String user = "";
    private String pass = "";
    private String login_msg = "Welcome!";
    
    private MainFrame mainframe;
    
    public login() {
        super("Log in:");
        addWindowListener(new Login_Adapter(this));

        b_login = new Button("Log in");
        b_exit = new Button("Exit");
        username = new TextField("root", 15);
        password = new TextField("", 15);
        username_l = new Label("Username: ", Label.LEFT);
        password_l = new Label("Password: ", Label.LEFT);

        password.setEchoChar('*');

        setLayout(new FlowLayout());

        add(username_l);
        add(username);
        add(password_l);
        add(password);
        add(b_login);
        add(b_exit);

        //username.addActionListener(this);
        //password.addActionListener(this);
        b_login.addActionListener(this);
        b_exit.addActionListener(this);
        
        //Initialized
        mainframe = null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(login_msg, 10, 140);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Log in":
                user = username.getText();
                pass = password.getText();
                //debug
                login_msg = "Login as " + user + " : Connecting...";

                //Try to connect to Database
                conn = new SQL_Connection(user, pass);
                login_msg = "Login as " + user + " : " + conn.msg;

                repaint(); //debug

                //If Success, then Set up Main Frame
                if (true == conn.success && null == mainframe) //Check there is only one MainFrame！！！！！！
                {
                    //Set up Main Frame
                    //Must pass SQL_Connection conn to Main Frame!!
                    mainframe = new MainFrame(conn);
                    mainframe.setVisible(false);
                    mainframe.setSize(1000, 400);
                    mainframe.setLocation(180, 250);
                    mainframe.setResizable(false);
                    mainframe.setVisible(true);
                    //Close Log In Frame
                    this.setVisible(false);
                }
                break;
            case "Exit":
                this.setVisible(true);
                System.exit(0);
                break;
        }
        repaint();
    }
}

class Login_Adapter extends WindowAdapter {

    private Frame f;

    public Login_Adapter(Frame f) {
        this.f = f;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        f.setVisible(true);
        System.exit(0);
    }
}
