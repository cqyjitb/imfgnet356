package yj.core.xhcard.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name="sap_xhcard")
public class Xhcard
        extends BaseDTO
{
    @NotEmpty
    private String werks;
    @NotEmpty
    private String matnr;
    @NotEmpty
    private String charg;
    @Id
    @GeneratedValue
    private String zxhnum;
    private String zxhzt;
    private String zxhzt2;
    private String lgort;
    private String menge;
    private String meins;
    private String zxhwz;
    private String aufnr;
    private String zxhbar;
    private String zjyy;
    private String zscbc;
    private String zscx;
    private String zmnum;
    private String zsctptm;
    private String ztxt;
    private String zbqbd;
    private String chargkc;
    private String zsxwc;//上线完成标识
    private String lineid;

    @Transient
    private String datestr;

    private Date creationDate; //创建时间

    @NotNull
    private Long createdBy; //创建人

    private Date lastUpdatedDate; //更新时间

    private Long lastUpdatedBy; //更新人

    @Transient
    private String bar;
    @Transient
    private Long zsxnum;
    @Transient
    private Long smenge;
    @Transient
    private Long bmenge;
    @Transient
    private Date  attr1;
    @Transient
    private  String maktx;
    @Transient
    private String pkgLineId;
    @Transient
    private String sfflg;
    @Transient
    private String zpgdbar;
    @Transient
    private Long changeNum;//调整数量
    @Transient
    private Long sjjcNum;//实际结存数量
    @Transient
    private Long dlsum;//队列数量
    @Transient
    private String descriptions;
    @Transient
    private String dates;
    @Transient
    private String datee;
    @Transient
    private String wdflag;//围堵状态标识
    @Transient
    private String hdflag;//冻结状态标识
    @Transient
    private String lgobe;//线边库名称
    private String creationDateAfter;
    private String creationDateBefore;
    @Transient
    private String attr7;
    private Integer dfectQty;//未投不良记录数

    private String lastUpdateDate2;//移库日期

    public String getLastUpdateDate2() {
        return lastUpdateDate2;
    }

    public void setLastUpdateDate2(String lastUpdateDate2) {
        this.lastUpdateDate2 = lastUpdateDate2;
    }

    public Integer getDfectQty() {
        return dfectQty;
    }

    public void setDfectQty(Integer dfectQty) {
        this.dfectQty = dfectQty;
    }

    public String getLgobe() {
        return lgobe;
    }

    public void setLgobe(String lgobe) {
        this.lgobe = lgobe;
    }

    public String getCreationDateAfter() {
        return creationDateAfter;
    }

    public void setCreationDateAfter(String creationDateAfter) {
        this.creationDateAfter = creationDateAfter;
    }

    public String getCreationDateBefore() {
        return creationDateBefore;
    }

    public void setCreationDateBefore(String creationDateBefore) {
        this.creationDateBefore = creationDateBefore;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getWdflag() {
        return wdflag;
    }

    public void setWdflag(String wdflag) {
        this.wdflag = wdflag;
    }

    public String getHdflag() {
        return hdflag;
    }

    public void setHdflag(String hdflag) {
        this.hdflag = hdflag;
    }

    @Transient


    private double synum;//剩余数量

    public double getSynum() {
        return synum;
    }

    public void setSynum(double synum) {
        this.synum = synum;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Long getDlsum() {
        return dlsum;
    }

    public void setDlsum(Long dlsum) {
        this.dlsum = dlsum;
    }

    public void setWerks(String werks)
    {
        this.werks = werks;
    }

    public String getWerks()
    {
        return this.werks;
    }

    public void setMatnr(String matnr)
    {
        this.matnr = matnr;
    }

    public String getMatnr()
    {
        return this.matnr;
    }

    public void setCharg(String charg)
    {
        this.charg = charg;
    }

    public String getCharg()
    {
        return this.charg;
    }

    public void setZxhnum(String zxhnum)
    {
        this.zxhnum = zxhnum;
    }

    public String getZxhnum()
    {
        return this.zxhnum;
    }

    public void setZxhzt(String zxhzt)
    {
        this.zxhzt = zxhzt;
    }

    public String getZxhzt()
    {
        return this.zxhzt;
    }

    public void setZxhzt2(String zxhzt2)
    {
        this.zxhzt2 = zxhzt2;
    }

    public String getZxhzt2()
    {
        return this.zxhzt2;
    }

    public void setLgort(String lgort)
    {
        this.lgort = lgort;
    }

    public String getLgort()
    {
        return this.lgort;
    }

    public void setMenge(String menge)
    {
        this.menge = menge;
    }

    public String getMenge()
    {
        return this.menge;
    }

    public void setMeins(String meins)
    {
        this.meins = meins;
    }

    public String getMeins()
    {
        return this.meins;
    }

    public void setZxhwz(String zxhwz)
    {
        this.zxhwz = zxhwz;
    }

    public String getZxhwz()
    {
        return this.zxhwz;
    }

    public void setAufnr(String aufnr)
    {
        this.aufnr = aufnr;
    }

    public String getAufnr()
    {
        return this.aufnr;
    }

    public void setZxhbar(String zxhbar)
    {
        this.zxhbar = zxhbar;
    }

    public String getZxhbar()
    {
        return this.zxhbar;
    }

    public void setZjyy(String zjyy)
    {
        this.zjyy = zjyy;
    }

    public String getZjyy()
    {
        return this.zjyy;
    }

    public void setZscbc(String zscbc)
    {
        this.zscbc = zscbc;
    }

    public String getZscbc()
    {
        return this.zscbc;
    }

    public void setZscx(String zscx)
    {
        this.zscx = zscx;
    }

    public String getZscx()
    {
        return this.zscx;
    }

    public void setZmnum(String zmnum)
    {
        this.zmnum = zmnum;
    }

    public String getZmnum()
    {
        return this.zmnum;
    }

    public void setZsctptm(String zsctptm)
    {
        this.zsctptm = zsctptm;
    }

    public String getZsctptm()
    {
        return this.zsctptm;
    }

    public void setZtxt(String ztxt)
    {
        this.ztxt = ztxt;
    }

    public String getZtxt()
    {
        return this.ztxt;
    }

    public void setZbqbd(String zbqbd)
    {
        this.zbqbd = zbqbd;
    }

    public String getZbqbd()
    {
        return this.zbqbd;
    }

    public String getChargkc()
    {
        return this.chargkc;
    }

    public void setChargkc(String chargkc)
    {
        this.chargkc = chargkc;
    }

    public String getZsxwc() {
        return zsxwc;
    }

    public void setZsxwc(String zsxwc) {
        this.zsxwc = zsxwc;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getDatestr() {
        return datestr;
    }

    public void setDatestr(String datestr) {
        this.datestr = datestr;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public Long getZsxnum() {
        return zsxnum;
    }

    public void setZsxnum(Long zsxnum) {
        this.zsxnum = zsxnum;
    }

    public Long getSmenge() {
        return smenge;
    }

    public void setSmenge(Long smenge) {
        this.smenge = smenge;
    }

    public Long getBmenge() {
        return bmenge;
    }

    public void setBmenge(Long bmenge) {
        this.bmenge = bmenge;
    }

    public Date getAttr1() {
        return attr1;
    }

    public void setAttr1(Date attr1) {
        this.attr1 = attr1;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getPkgLineId() {
        return pkgLineId;
    }

    public void setPkgLineId(String pkgLineId) {
        this.pkgLineId = pkgLineId;
    }

    public String getSfflg() {
        return sfflg;
    }

    public void setSfflg(String sfflg) {
        this.sfflg = sfflg;
    }

    public String getZpgdbar() {
        return zpgdbar;
    }

    public void setZpgdbar(String zpgdbar) {
        this.zpgdbar = zpgdbar;
    }

    public Long getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Long changeNum) {
        this.changeNum = changeNum;
    }

    public Long getSjjcNum() {
        return sjjcNum;
    }

    public void setSjjcNum(Long sjjcNum) {
        this.sjjcNum = sjjcNum;
    }


}
