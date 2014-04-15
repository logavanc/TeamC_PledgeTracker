package teamc_pledgetracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

/**
 *
 * @author leverett
 */
public class DatabaseDAL implements IDAL {
    private static final String url = "jdbc:mysql://sql3.freemysqlhosting.net:3306/";
    private static final String dbName = "sql336726";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "sql336726";
    private static final String password = "eD6*iS6*";
    private Connection conn;

    @Override
    public boolean init() {
        boolean bRet = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(url+dbName,userName,password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseDAL.class.getName()).log(Level.SEVERE, null, ex);
            bRet = false;
        }
        return bRet;
    }

    @Override
    public boolean saveData(Pledge pledge) {
        boolean bRet = true;
        try {
            PreparedStatement st = conn.prepareStatement("INSERT into Pledges VALUES(?,?,?)");
            //Make vales SQL safe
            st.setString(1, pledge.getName());
            st.setString(2, pledge.getCharity());
            st.setInt(3, pledge.getPledgeamt());
            
            int iRetVal = st.executeUpdate();
            if (iRetVal != 1) {
                bRet = false;
            }
            //cleanup
            st.close();
            st = null;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAL.class.getName()).log(Level.SEVERE, null, ex);
            bRet = false;
        }
        return bRet;
    }

    @Override
    public List<Pledge> getData() {
        List<Pledge> Pledges = new ArrayList<>();        
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM Pledges");
            while (res.next()) {
                Pledges.add(new Pledge(res.getString("name"), res.getString("charity"), res.getInt("pledge")));
            }
            //cleanup
            res.close();
            res = null;
            st.close();
            st = null;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Pledges;
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                //cleanup
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
