package yj.core.afko.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@ExtensionAttribute(disable=true)
@Table(name="sap_afko")
public class Afko
        extends BaseDTO
{
    @NotEmpty
    private String mandt;
    @NotEmpty
    private String aufnr;
    @NotEmpty
    private String posnr;
    @NotEmpty
    private String bukrs;
    @Id
    @GeneratedValue
    private String werks;
    private String gltrp;
    private String gstrp;
    private String auart;
    private String auart_txt;
    private String fevor;
    private String txt;
    private String dispo;
    private String dsnam;
    private String aufpl;
    private String plnbez;
    private String maktx;
    private String gmein;
    private Double gamng;
    private Double gasmg;
    private Double igmng;
    private Double umrez;
    private String groes;
    private String charg;
    private String status;
    private String rsnum;
    @Transient
    private Double flgrg;//浮动报工率
    @Transient
    private Double gamng_bd;
    @Transient
    private Double cansum;
    @Transient
    private Double cursum;
    @Transient
    private String maxno;

    @Transient
    private String qmatnr;//压铸订单物料
    @Transient
    private  String qmaktx;
    @Transient
    private String qaufnr;//压铸订单
    @Transient
    private Double qgamng;//压铸订单数量
    @Transient
    private String qdauat;//压铸订单类型
    @Transient
    private String yverid;//压铸生产版本
    @Transient
    private String yaufnr;
    @Transient
    private String ygstrp;
    @Transient
    private String ygltrp;

    //创建机加流转时区分班次使用
    @Transient
    private String isA;
    @Transient
    private String isB;
    @Transient
    private String isC;

    @Transient
    private String productdate;

    @Transient
    private String arbpl;//工作中心

    public String getArbpl() {
        return arbpl;
    }

    public void setArbpl(String arbpl) {
        this.arbpl = arbpl;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getAufnr() {
        return aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getPosnr() {
        return posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getGltrp() {
        return gltrp;
    }

    public void setGltrp(String gltrp) {
        this.gltrp = gltrp;
    }

    public String getGstrp() {
        return gstrp;
    }

    public void setGstrp(String gstrp) {
        this.gstrp = gstrp;
    }

    public String getAuart() {
        return auart;
    }

    public void setAuart(String auart) {
        this.auart = auart;
    }

    public String getAuart_txt() {
        return auart_txt;
    }

    public void setAuart_txt(String auart_txt) {
        this.auart_txt = auart_txt;
    }

    public String getFevor() {
        return fevor;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getAufpl() {
        return aufpl;
    }

    public void setAufpl(String aufpl) {
        this.aufpl = aufpl;
    }

    public String getPlnbez() {
        return plnbez;
    }

    public void setPlnbez(String plnbez) {
        this.plnbez = plnbez;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getGmein() {
        return gmein;
    }

    public void setGmein(String gmein) {
        this.gmein = gmein;
    }

    public Double getGamng() {
        return gamng;
    }

    public void setGamng(Double gamng) {
        this.gamng = gamng;
    }

    public Double getGasmg() {
        return gasmg;
    }

    public void setGasmg(Double gasmg) {
        this.gasmg = gasmg;
    }

    public Double getIgmng() {
        return igmng;
    }

    public void setIgmng(Double igmng) {
        this.igmng = igmng;
    }

    public Double getUmrez() {
        return umrez;
    }

    public void setUmrez(Double umrez) {
        this.umrez = umrez;
    }

    public String getGroes() {
        return groes;
    }

    public void setGroes(String groes) {
        this.groes = groes;
    }

    public String getCharg() {
        return charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getGamng_bd() {
        return gamng_bd;
    }

    public void setGamng_bd(Double gamng_bd) {
        this.gamng_bd = gamng_bd;
    }

    public Double getCansum() {
        return cansum;
    }

    public void setCansum(Double cansum) {
        this.cansum = cansum;
    }

    public Double getCursum() {
        return cursum;
    }

    public void setCursum(Double cursum) {
        this.cursum = cursum;
    }

    public String getMaxno() {
        return maxno;
    }

    public void setMaxno(String maxno) {
        this.maxno = maxno;
    }

    public String getQmatnr() {
        return qmatnr;
    }

    public void setQmatnr(String qmatnr) {
        this.qmatnr = qmatnr;
    }

    public String getQaufnr() {
        return qaufnr;
    }

    public void setQaufnr(String qaufnr) {
        this.qaufnr = qaufnr;
    }

    public Double getQgamng() {
        return qgamng;
    }

    public void setQgamng(Double qgamng) {
        this.qgamng = qgamng;
    }

    public String getQdauat() {
        return qdauat;
    }

    public void setQdauat(String qdauat) {
        this.qdauat = qdauat;
    }

    public String getQmaktx() {
        return qmaktx;
    }

    public void setQmaktx(String qmaktx) {
        this.qmaktx = qmaktx;
    }

    public String getYverid() {
        return yverid;
    }

    public void setYverid(String yverid) {
        this.yverid = yverid;
    }

    public String getYgstrp() {
        return ygstrp;
    }

    public void setYgstrp(String ygstrp) {
        this.ygstrp = ygstrp;
    }

    public String getYgltrp() {
        return ygltrp;
    }

    public void setYgltrp(String ygltrp) {
        this.ygltrp = ygltrp;
    }

    public Double getFlgrg() {
        return flgrg;
    }

    public void setFlgrg(Double flgrg) {
        this.flgrg = flgrg;
    }

    public String getYaufnr() {
        return yaufnr;
    }

    public void setYaufnr(String yaufnr) {
        this.yaufnr = yaufnr;
    }

    public String getIsA() {
        return isA;
    }

    public void setIsA(String isA) {
        this.isA = isA;
    }

    public String getIsB() {
        return isB;
    }

    public void setIsB(String isB) {
        this.isB = isB;
    }

    public String getIsC() {
        return isC;
    }

    public void setIsC(String isC) {
        this.isC = isC;
    }

    public String getProductdate() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    public String getRsnum() {
        return rsnum;
    }

    public void setRsnum(String rsnum) {
        this.rsnum = rsnum;
    }

    public String getDsnam() {
        return dsnam;
    }

    public void setDsnam(String dsnam) {
        this.dsnam = dsnam;
    }
}
