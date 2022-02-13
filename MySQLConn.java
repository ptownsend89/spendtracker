package sample;

import java.sql.*;

public class MySQLConn {
    private int id;
    private String userName;
    private String password;
    private String createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    String connectionUrl = "jdbc:mysql://localhost:3306/expendTracker";
    Connection conn = DriverManager.getConnection(connectionUrl,"root","/password123");


    public boolean verifyUser(String username, String pword) {
        try {
            String sqlSelectUsers = "SELECT * FROM expendTracker.users WHERE username = '"+username+"'";
            PreparedStatement ps = conn.prepareStatement(sqlSelectUsers);
            ResultSet rs = ps.executeQuery();
            {
                while (rs.next()) {
                    id = rs.getInt("user_ID");
                    userName = rs.getString("username");
                    password = rs.getString("pword");
                    createdTime = rs.getString("created");
                }
            }
        } catch (Exception e){
            System.out.println("Connection exception occurred: "+ e);
        }
        if (pword.equals(password)){
            return true;
        } else {
            return false;
        }
    }

    public boolean createUser(String username, String pword) {
        boolean ok = false;
        try {
            String insertUser = "INSERT INTO expendTracker.users (username,pword) " +
                    "VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(insertUser);
            //setString index refers to location of parametrised values in insert query
            ps.setString(1,username);
            ps.setString(2,pword);
            ps.executeUpdate();
            System.out.println("Number of records updated: "+ps.getUpdateCount());
            if(ps.getUpdateCount() > 0){
                ok = true;
            }
        } catch (Exception e) {
            System.out.println("Insert error - exception: "+e);
        }
        return ok;
    }
    public MySQLConn() throws SQLException {
    }
}