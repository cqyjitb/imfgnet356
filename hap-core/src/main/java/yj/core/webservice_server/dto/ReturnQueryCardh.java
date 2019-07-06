package yj.core.webservice_server.dto;

import net.sf.json.JSONArray;

public class ReturnQueryCardh {

    private String syncdate;
    private String flag;
    private String message;
    private JSONArray cardh;
    private JSONArray cardt;

    public String getSyncdate() {
        return syncdate;
    }

    public void setSyncdate(String syncdate) {
        this.syncdate = syncdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONArray getCardh() {
        return cardh;
    }

    public void setCardh(JSONArray cardh) {
        this.cardh = cardh;
    }

    public JSONArray getCardt() {
        return cardt;
    }

    public void setCardt(JSONArray cardt) {
        this.cardt = cardt;
    }

    //    public List<Cardt> getCardts() {
//        return cardts;
//    }
//
//    public void setCardts(List<Cardt> cardts) {
//        this.cardts = cardts;
//    }

    //    private String zpgdbar; //派工单条码
//
//    private String zpgdbh; //派工单编号
//
//    private String zxhnum; //箱号
//
//    private String bukrs;
//
//    private String werks; //工厂
//
//    private String matnr; //物料编号
//
//    private Double stdqt; //标准装框数
//
//    private String aufnr; //订单号
//
//    private String aufnr2;
//
//    private String auart; //订单类型
//
//    private String auart2;
//
//    private Double menge;
//
//    private Double gamng; //来源订单数量1
//
//    private Double gamng2;
//
//    private String gmein; //基本计量单位
//
//    private Double plqty; //流转卡创建数量
//
//    private Double ecqty; //首工序报工数量
//
//    private Double alqty; //流转卡完工数量(末工序)
//
//    private Double qtysm; //料废数量
//
//    private Double qtysp; //工废数量
//
//    private Double tqtys; //班次总生产数量
//
//    private Double cqtys; //压铸冷模件数量
//
//    private String lgort;
//
//    private String charg; //批号
//
//    private String gstrp; //流转卡计划开始日期
//
//    private String schst; //流转卡计划开始时间
//
//    private String gltrp; //流转卡计划完成日期
//
//    private String schdt;
//
//    private String actgstrp;
//
//    private String actst;
//
//    private String actgltrp; //流转卡实际完成日期
//
//    private String actdt; //流转卡实际完成时间
//
//    private String zdybs; //打印标识
//
//    private String status; //流转卡状态
//
//    private String status2; //流转卡前一状态
//
//    private String diecd; //模具编号
//
//    private String deqty; //模数
//
//    private String fprddat;
//
//    private String eprddat;
//
//    private String stwks;
//
//    private String shift;
//
//    private String sfflg;
//
//    private String charg2;
//
//    private String relzpgdbar;
//
//    private String relaufnr;
//
//    private String mtlbd;
//
//    private String mtllt;
//
//    private String prtnr;
//
//    private String casds;
//
//    private String attr1;
//
//    private String attr2;
//
//    private String attr3;
//
//    private String attr4;
//
//    private String attr5;
//
//    private String attr6;
//
//    private String attr7;
//
//    public String getZpgdbar() {
//        return zpgdbar;
//    }
//
//    public void setZpgdbar(String zpgdbar) {
//        this.zpgdbar = zpgdbar;
//    }
//
//    public String getZpgdbh() {
//        return zpgdbh;
//    }
//
//    public void setZpgdbh(String zpgdbh) {
//        this.zpgdbh = zpgdbh;
//    }
//
//    public String getZxhnum() {
//        return zxhnum;
//    }
//
//    public void setZxhnum(String zxhnum) {
//        this.zxhnum = zxhnum;
//    }
//
//    public String getBukrs() {
//        return bukrs;
//    }
//
//    public void setBukrs(String bukrs) {
//        this.bukrs = bukrs;
//    }
//
//    public String getWerks() {
//        return werks;
//    }
//
//    public void setWerks(String werks) {
//        this.werks = werks;
//    }
//
//    public String getMatnr() {
//        return matnr;
//    }
//
//    public void setMatnr(String matnr) {
//        this.matnr = matnr;
//    }
//
//    public Double getStdqt() {
//        return stdqt;
//    }
//
//    public void setStdqt(Double stdqt) {
//        this.stdqt = stdqt;
//    }
//
//    public String getAufnr() {
//        return aufnr;
//    }
//
//    public void setAufnr(String aufnr) {
//        this.aufnr = aufnr;
//    }
//
//    public String getAufnr2() {
//        return aufnr2;
//    }
//
//    public void setAufnr2(String aufnr2) {
//        this.aufnr2 = aufnr2;
//    }
//
//    public String getAuart() {
//        return auart;
//    }
//
//    public void setAuart(String auart) {
//        this.auart = auart;
//    }
//
//    public String getAuart2() {
//        return auart2;
//    }
//
//    public void setAuart2(String auart2) {
//        this.auart2 = auart2;
//    }
//
//    public Double getMenge() {
//        return menge;
//    }
//
//    public void setMenge(Double menge) {
//        this.menge = menge;
//    }
//
//    public Double getGamng() {
//        return gamng;
//    }
//
//    public void setGamng(Double gamng) {
//        this.gamng = gamng;
//    }
//
//    public Double getGamng2() {
//        return gamng2;
//    }
//
//    public void setGamng2(Double gamng2) {
//        this.gamng2 = gamng2;
//    }
//
//    public String getGmein() {
//        return gmein;
//    }
//
//    public void setGmein(String gmein) {
//        this.gmein = gmein;
//    }
//
//    public Double getPlqty() {
//        return plqty;
//    }
//
//    public void setPlqty(Double plqty) {
//        this.plqty = plqty;
//    }
//
//    public Double getEcqty() {
//        return ecqty;
//    }
//
//    public void setEcqty(Double ecqty) {
//        this.ecqty = ecqty;
//    }
//
//    public Double getAlqty() {
//        return alqty;
//    }
//
//    public void setAlqty(Double alqty) {
//        this.alqty = alqty;
//    }
//
//    public Double getQtysm() {
//        return qtysm;
//    }
//
//    public void setQtysm(Double qtysm) {
//        this.qtysm = qtysm;
//    }
//
//    public Double getQtysp() {
//        return qtysp;
//    }
//
//    public void setQtysp(Double qtysp) {
//        this.qtysp = qtysp;
//    }
//
//    public Double getTqtys() {
//        return tqtys;
//    }
//
//    public void setTqtys(Double tqtys) {
//        this.tqtys = tqtys;
//    }
//
//    public Double getCqtys() {
//        return cqtys;
//    }
//
//    public void setCqtys(Double cqtys) {
//        this.cqtys = cqtys;
//    }
//
//    public String getLgort() {
//        return lgort;
//    }
//
//    public void setLgort(String lgort) {
//        this.lgort = lgort;
//    }
//
//    public String getCharg() {
//        return charg;
//    }
//
//    public void setCharg(String charg) {
//        this.charg = charg;
//    }
//
//    public String getGstrp() {
//        return gstrp;
//    }
//
//    public void setGstrp(String gstrp) {
//        this.gstrp = gstrp;
//    }
//
//    public String getSchst() {
//        return schst;
//    }
//
//    public void setSchst(String schst) {
//        this.schst = schst;
//    }
//
//    public String getGltrp() {
//        return gltrp;
//    }
//
//    public void setGltrp(String gltrp) {
//        this.gltrp = gltrp;
//    }
//
//    public String getSchdt() {
//        return schdt;
//    }
//
//    public void setSchdt(String schdt) {
//        this.schdt = schdt;
//    }
//
//    public String getActgstrp() {
//        return actgstrp;
//    }
//
//    public void setActgstrp(String actgstrp) {
//        this.actgstrp = actgstrp;
//    }
//
//    public String getActst() {
//        return actst;
//    }
//
//    public void setActst(String actst) {
//        this.actst = actst;
//    }
//
//    public String getActgltrp() {
//        return actgltrp;
//    }
//
//    public void setActgltrp(String actgltrp) {
//        this.actgltrp = actgltrp;
//    }
//
//    public String getActdt() {
//        return actdt;
//    }
//
//    public void setActdt(String actdt) {
//        this.actdt = actdt;
//    }
//
//    public String getZdybs() {
//        return zdybs;
//    }
//
//    public void setZdybs(String zdybs) {
//        this.zdybs = zdybs;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getStatus2() {
//        return status2;
//    }
//
//    public void setStatus2(String status2) {
//        this.status2 = status2;
//    }
//
//    public String getDiecd() {
//        return diecd;
//    }
//
//    public void setDiecd(String diecd) {
//        this.diecd = diecd;
//    }
//
//    public String getDeqty() {
//        return deqty;
//    }
//
//    public void setDeqty(String deqty) {
//        this.deqty = deqty;
//    }
//
//    public String getFprddat() {
//        return fprddat;
//    }
//
//    public void setFprddat(String fprddat) {
//        this.fprddat = fprddat;
//    }
//
//    public String getEprddat() {
//        return eprddat;
//    }
//
//    public void setEprddat(String eprddat) {
//        this.eprddat = eprddat;
//    }
//
//    public String getStwks() {
//        return stwks;
//    }
//
//    public void setStwks(String stwks) {
//        this.stwks = stwks;
//    }
//
//    public String getShift() {
//        return shift;
//    }
//
//    public void setShift(String shift) {
//        this.shift = shift;
//    }
//
//    public String getSfflg() {
//        return sfflg;
//    }
//
//    public void setSfflg(String sfflg) {
//        this.sfflg = sfflg;
//    }
//
//    public String getCharg2() {
//        return charg2;
//    }
//
//    public void setCharg2(String charg2) {
//        this.charg2 = charg2;
//    }
//
//    public String getRelzpgdbar() {
//        return relzpgdbar;
//    }
//
//    public void setRelzpgdbar(String relzpgdbar) {
//        this.relzpgdbar = relzpgdbar;
//    }
//
//    public String getRelaufnr() {
//        return relaufnr;
//    }
//
//    public void setRelaufnr(String relaufnr) {
//        this.relaufnr = relaufnr;
//    }
//
//    public String getMtlbd() {
//        return mtlbd;
//    }
//
//    public void setMtlbd(String mtlbd) {
//        this.mtlbd = mtlbd;
//    }
//
//    public String getMtllt() {
//        return mtllt;
//    }
//
//    public void setMtllt(String mtllt) {
//        this.mtllt = mtllt;
//    }
//
//    public String getPrtnr() {
//        return prtnr;
//    }
//
//    public void setPrtnr(String prtnr) {
//        this.prtnr = prtnr;
//    }
//
//    public String getCasds() {
//        return casds;
//    }
//
//    public void setCasds(String casds) {
//        this.casds = casds;
//    }
//
//    public String getAttr1() {
//        return attr1;
//    }
//
//    public void setAttr1(String attr1) {
//        this.attr1 = attr1;
//    }
//
//    public String getAttr2() {
//        return attr2;
//    }
//
//    public void setAttr2(String attr2) {
//        this.attr2 = attr2;
//    }
//
//    public String getAttr3() {
//        return attr3;
//    }
//
//    public void setAttr3(String attr3) {
//        this.attr3 = attr3;
//    }
//
//    public String getAttr4() {
//        return attr4;
//    }
//
//    public void setAttr4(String attr4) {
//        this.attr4 = attr4;
//    }
//
//    public String getAttr5() {
//        return attr5;
//    }
//
//    public void setAttr5(String attr5) {
//        this.attr5 = attr5;
//    }
//
//    public String getAttr6() {
//        return attr6;
//    }
//
//    public void setAttr6(String attr6) {
//        this.attr6 = attr6;
//    }
//
//    public String getAttr7() {
//        return attr7;
//    }
//
//    public void setAttr7(String attr7) {
//        this.attr7 = attr7;
//    }
}
