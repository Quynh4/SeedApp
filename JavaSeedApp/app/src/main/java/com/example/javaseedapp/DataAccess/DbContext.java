package com.example.javaseedapp.DataAccess;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbContext {
    String username, pass, ip, port, database;

    public Connection conn = ConnectionClass();

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            Log.e("Error:", e.getMessage() );
        }
        return rs;
    }

    public int countRows(String table, String search) {
        int te = 0;
        String sql = "Select count(*) from "+table+" where "+search;
        ResultSet rs1 = getData(sql);
        try {
            while (rs1.next()) {
                te = rs1.getInt(1);
            }
            rs1.close();
        } catch (Exception ex) {
            Log.e("Error:", ex.getMessage() );
        }
        return te;
    }

    public Connection ConnectionClass() {
        ip = "172.20.10.3";
        database = "TriolingoDatabase";
        username = "sa1";
        pass = "123456";
        port = "1433";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection con = null;
        String connectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"
                    + port+";"+ "databasename="+ database+";user="
                    +username+";password="+pass+";";
            con = DriverManager.getConnection(connectionURL);
        }
        catch (Exception ex) {
            Log.e("Error:", ex.getMessage() );
        }
        return con;
    }
}
