package yj.core.SqlConn;

import yj.core.pandian.dto.Pandiantmp;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlConnGzb {
    private static final String dbUrl="jdbc:sqlserver://192.168.0.88;databasename=jb_data";
    private static final String dbUserName="ws_usr"; // 用户名
    private static final String dbPassword="123456";
    private static final String jdbcName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public SqlConnGzb(){

    }

    public static void main(String[] args) {

        SqlConnGzb sqlConnGzb = new SqlConnGzb();
        String sql = "select * from pandiantmp";
        try {
            sqlConnGzb.select(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(jdbcName);
            conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
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

    public int insertPanDianTmp(Pandiantmp pandiantmp) throws Exception{
        Connection con = this.getConnection();
        String sql = "insert INTO jb_data.dbo.pandiantmp (RCDID, WERKS, CARDNO, CARDH, NUM, OPERATOR, RCDDAT, CREATED_BY, CREATION_DATE) VALUES " +
                "(?,?,?,?,?,?,?,?,?)";
        int result = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,pandiantmp.getRcdid());
        pstmt.setString(2,pandiantmp.getWerks());
        pstmt.setString(3,pandiantmp.getCardno());
        pstmt.setString(4,pandiantmp.getCardh());
        pstmt.setDouble(5,pandiantmp.getNum());
        pstmt.setString(6,pandiantmp.getOperator());
        pstmt.setString(7,format.format(pandiantmp.getRcddat()));
        pstmt.setLong(8,pandiantmp.getCreatedBy());
        pstmt.setString(9,format.format(pandiantmp.getCreationDate()));
        result = pstmt.executeUpdate();
        this.closeConnection(con, pstmt);
        return result;
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
