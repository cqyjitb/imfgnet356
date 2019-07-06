package yj.core.OracleConn;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OracleConn {


    public String dbUrl;
    public String dbUserName;
    public String dbPassword;
    public String  jdbcName;

    public OracleConn(String dbUrl,String dbUserName,String dbPassword,String jdbcName){
        this.dbUrl = dbUrl;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
        this.jdbcName = jdbcName;
    }
    public static void main(String[] args) throws ParseException {
        //        this.mesOraDriver = "oracle.jdbc.OracleDriver";
        //        this.mesOraUrl = "jdbc:oracle:thin:@192.168.4.37:1521:orclyj";
        //        this.mesOraPass = "mes_query_usr";
        //        this.mesOraUserName = "mesapp12345";
//        OracleConn oracleConn = new OracleConn("jdbc:oracle:thin:@192.168.4.37:1521:orclyj","mes_query_usr","mesapp12345","oracle.jdbc.OracleDriver");
//        String sql = "select * from MES_M264Q01.WIP_MAIN_DATA";
//        try {
//            List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//            list = oracleConn.select(sql);
//            if (list.size() > 0){
//                System.out.println(list.size());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sd2 = new SimpleDateFormat("yyyyMMdd");
//
//        SimpleDateFormat sd3 = new SimpleDateFormat("HHmmSS");
//        SimpleDateFormat sd4 = new SimpleDateFormat("HH:mm:SS");
//        Date date = null;
//        try {
//            date = sd3.parse("135922");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String s = sd4.format(date);
//
//            System.out.println(s);
        int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-05-06");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

        Integer week = cal.get(Calendar.WEEK_OF_YEAR) % 2;
        System.out.println(week);


    }

    private  Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(this.jdbcName);
            conn = DriverManager.getConnection(this.dbUrl,this.dbUserName,this.dbPassword);
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
