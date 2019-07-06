package yj.core.webservice_server.dto;

/**
 * Created by 917110140 on 2018/9/8.
 */
public class Req_callMigo {
    private String ACTION;//操作事物

    private String REFDOC;//参考单据

    private String BUDAT;//过账日期

    private String BLDAT;//凭证日期

    private String BKTXT;//凭证抬头文本

    private String BWART;//移动类型

    private String WERKS;//工厂

    private String MATNR;//物料号

    private String LGORT;//库存地点

    private String CHARG;//批次

    private String ERFMG;//数量

    private String ERFME;//单位

    private String AUFNR;//生产订单号

    private String RSNUM;//预留号

    private String RSPOS;//预留行号

    private String TAKEIT;//采用

    private String SMBLN;//冲销物料凭证

    private String SMBLP;//冲销物料凭证行

    private String SJAHR;//冲销凭证年度

    private String UMLGO;//接收库存地点

    private String UMCHA;//接收库存批次

    public String getACTION() {
        return ACTION;
    }

    public void setACTION(String ACTION) {
        this.ACTION = ACTION;
    }

    public String getREFDOC() {
        return REFDOC;
    }

    public void setREFDOC(String REFDOC) {
        this.REFDOC = REFDOC;
    }

    public String getBUDAT() {
        return BUDAT;
    }

    public void setBUDAT(String BUDAT) {
        this.BUDAT = BUDAT;
    }

    public String getBLDAT() {
        return BLDAT;
    }

    public void setBLDAT(String BLDAT) {
        this.BLDAT = BLDAT;
    }

    public String getBKTXT() {
        return BKTXT;
    }

    public void setBKTXT(String BKTXT) {
        this.BKTXT = BKTXT;
    }

    public String getBWART() {
        return BWART;
    }

    public void setBWART(String BWART) {
        this.BWART = BWART;
    }

    public String getWERKS() {
        return WERKS;
    }

    public void setWERKS(String WERKS) {
        this.WERKS = WERKS;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getLGORT() {
        return LGORT;
    }

    public void setLGORT(String LGORT) {
        this.LGORT = LGORT;
    }

    public String getCHARG() {
        return CHARG;
    }

    public void setCHARG(String CHARG) {
        this.CHARG = CHARG;
    }

    public String getERFMG() {
        return ERFMG;
    }

    public void setERFMG(String ERFMG) {
        this.ERFMG = ERFMG;
    }

    public String getERFME() {
        return ERFME;
    }

    public void setERFME(String ERFME) {
        this.ERFME = ERFME;
    }

    public String getAUFNR() {
        return AUFNR;
    }

    public void setAUFNR(String AUFNR) {
        this.AUFNR = AUFNR;
    }

    public String getRSNUM() {
        return RSNUM;
    }

    public void setRSNUM(String RSNUM) {
        this.RSNUM = RSNUM;
    }

    public String getRSPOS() {
        return RSPOS;
    }

    public void setRSPOS(String RSPOS) {
        this.RSPOS = RSPOS;
    }

    public String getTAKEIT() {
        return TAKEIT;
    }

    public void setTAKEIT(String TAKEIT) {
        this.TAKEIT = TAKEIT;
    }

    public String getSMBLN() {
        return SMBLN;
    }

    public void setSMBLN(String SMBLN) {
        this.SMBLN = SMBLN;
    }

    public String getSMBLP() {
        return SMBLP;
    }

    public void setSMBLP(String SMBLP) {
        this.SMBLP = SMBLP;
    }

    public String getSJAHR() {
        return SJAHR;
    }

    public void setSJAHR(String SJAHR) {
        this.SJAHR = SJAHR;
    }

    public String getUMLGO() {
        return UMLGO;
    }

    public void setUMLGO(String UMLGO) {
        this.UMLGO = UMLGO;
    }

    public String getUMCHA() {
        return UMCHA;
    }

    public void setUMCHA(String UMCHA) {
        this.UMCHA = UMCHA;
    }
}
