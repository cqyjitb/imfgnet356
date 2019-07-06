package yj.core.util;

public class WebServerHelp {

    private String username;
    private String password;

    private String HanaDRIVER;
    private String HanaUrl;
    private String HanaUserName;
    private String HanaPass;
    private String mandt;

    private String mesOraDriver;
    private String mesOraUrl;
    private String mesOraUserName;
    private String mesOraPass;


    public WebServerHelp(){
        //dev
        this.username = "HAPUSER";
        this.password = "Yjsap123@CQ";

        //dev
        this.HanaDRIVER = "com.sap.db.jdbc.Driver";
        this.HanaUrl ="jdbc:sap://192.168.3.20:35015?reconnect=true";
        this.HanaUserName = "FINEREPORT";
        this.HanaPass = "Finereport159";
        this.mandt = "300";

        //dev
        this.mesOraDriver = "oracle.jdbc.OracleDriver";
        this.mesOraUrl = "jdbc:oracle:thin:@192.168.94.93:1521:orclyj";
        this.mesOraUserName = "mes_query_usr";
        this.mesOraPass = "mesapp12345";

//        //prd
//          this.username = "HAPUSER";
//          this.password = "YJhap201707@CQ";
//        //prd
//
//        this.HanaDRIVER = "com.sap.db.jdbc.Driver";
//        this.HanaUrl ="jdbc:sap://192.168.3.11:30015?reconnect=true";
//        this.HanaUserName = "FINEREPORT";
//        this.HanaPass = "Finereport6666";
//        this.mandt = "800";
//
//
//        this.mesOraDriver = "oracle.jdbc.OracleDriver";
//        this.mesOraUrl = "jdbc:oracle:thin:@192.168.4.37:1521:orclyj";
//        this.mesOraPass = "mesapp12345";
//        this.mesOraUserName = "mes_query_usr";
    }

    public String getUsername() {
        return username;
    }

    public String getHanaDRIVER() {
        return HanaDRIVER;
    }

    public void setHanaDRIVER(String hanaDRIVER) {
        HanaDRIVER = hanaDRIVER;
    }

    public String getHanaUrl() {
        return HanaUrl;
    }

    public void setHanaUrl(String hanaUrl) {
        HanaUrl = hanaUrl;
    }

    public String getHanaUserName() {
        return HanaUserName;
    }

    public void setHanaUserName(String hanaUserName) {
        HanaUserName = hanaUserName;
    }

    public String getHanaPass() {
        return HanaPass;
    }

    public void setHanaPass(String hanaPass) {
        HanaPass = hanaPass;
    }

    public String getMesOraDriver() {
        return mesOraDriver;
    }

    public void setMesOraDriver(String mesOraDriver) {
        this.mesOraDriver = mesOraDriver;
    }

    public String getMesOraUrl() {
        return mesOraUrl;
    }

    public void setMesOraUrl(String mesOraUrl) {
        this.mesOraUrl = mesOraUrl;
    }

    public String getMesOraUserName() {
        return mesOraUserName;
    }

    public void setMesOraUserName(String mesOraUserName) {
        this.mesOraUserName = mesOraUserName;
    }

    public String getMesOraPass() {
        return mesOraPass;
    }

    public void setMesOraPass(String mesOraPass) {
        this.mesOraPass = mesOraPass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }
}
