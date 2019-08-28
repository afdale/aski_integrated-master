package com.example.aski_integrated;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ABHI on 9/20/2016.
 */

public class ConnectionClass {

    String IP = "10.14.117.37";
    String DB = "ASKICBT_DEV";
    String USER = "askicbtdev";
    String PASSWORD = "S3m3ru";
    String PORT = "1433";

/*
    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://10.14.119.6:3306/testing?user=test&password=test";*/

        String classs = "net.sourceforge.jtds.jdbc.Driver";
        String url = "jdbc:jtds:sqlserver://" + IP +":"+PORT+";" + "databaseName=" + DB + ";user=" + USER + ";password=" + PASSWORD + ";";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);

            conn = DriverManager.getConnection(url);


            conn = DriverManager.getConnection(ConnURL);
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}