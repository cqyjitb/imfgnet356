package yj.core.HanaCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HanaCon {

    public String DRIVER;
    public String URL;
    public String USERNAME;
    public String PASSWORD;
    public HanaCon(String url,String username,String password,String driver){

        this.DRIVER = driver;
        this.URL    = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public static void main(String[] args) {
//        this.HanaDRIVER = "com.sap.db.jdbc.Driver";
//        this.HanaUrl ="jdbc:sap://192.168.3.11:30015?reconnect=true";
//        this.HanaUserName = "FINEREPORT";
//        this.HanaPass = "Finereport3333";
//        this.mandt = "800";


        HanaCon hanaCon = new HanaCon("jdbc:sap://192.168.3.11:30015?reconnect=true","FINEREPORT","Finereport6666","com.sap.db.jdbc.Driver");
        String sql = "select * from "+ "SAPABAP1"+ "."+"MARA";

        try {
            hanaCon.select(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List select(String sql) throws Exception {
        Connection con = this.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list = this.processResult(rs);
        this.closeConnection(con, pstmt);
        return list;

    }

    private Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(this.DRIVER);
            conn = DriverManager.getConnection(this.URL,this.USERNAME,this.PASSWORD);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private List<Map<String, Object>> processResult(ResultSet rs) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        while (rs.next()){
            Map<String,Object> rowData = new HashMap<String,Object>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));

            }
            list.add(rowData);
        }
        return list;

    }

    private void closeConnection(Connection con, Statement stmt)  throws Exception{

        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }

    }
}
