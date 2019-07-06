package yj.kanb.wipngrecord.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_ng_record")
public class NgRecord extends BaseDTO {
    @Id
    private String werks;//工厂
    @NotEmpty
    private String deptId;//车间
    @NotEmpty
    private String lineId;//产线
    @NotEmpty
    private String matnr;//物料编码

    private String maktx;//物料描述
    @NotEmpty
    private Date erdat;//生产日期

    private Integer qty;//汇总

    private String gmein;//单位
    @NotEmpty
    private String zotype;//取出原因
    @NotEmpty
    private String zissuetxt;//缺陷代码

    private String ztext;//缺陷描述

    private String dateStart;
    private String dateEnd;
    private String scrapRate;//不良比
    private String scrapRateSum;//累计不良比

    @Transient
    private Integer gmnga;//装箱数
    private Double ppm;//当月PPM

    public Double getPpm() {
        return ppm;
    }

    public void setPpm(Double ppm) {
        this.ppm = ppm;
    }

    public Integer getGmnga() {
        return gmnga;
    }

    public void setGmnga(Integer gmnga) {
        this.gmnga = gmnga;
    }

    public String getScrapRate() {
        return scrapRate;
    }

    public void setScrapRate(String scrapRate) {
        this.scrapRate = scrapRate;
    }

    public String getScrapRateSum() {
        return scrapRateSum;
    }

    public void setScrapRateSum(String scrapRateSum) {
        this.scrapRateSum = scrapRateSum;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public Date getErdat() {
        return erdat;
    }

    public void setErdat(Date erdat) {
        this.erdat = erdat;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getGmein() {
        return gmein;
    }

    public void setGmein(String gmein) {
        this.gmein = gmein;
    }

    public String getZotype() {
        return zotype;
    }

    public void setZotype(String zotype) {
        this.zotype = zotype;
    }

    public String getZissuetxt() {
        return zissuetxt;
    }

    public void setZissuetxt(String zissuetxt) {
        this.zissuetxt = zissuetxt;
    }

    public String getZtext() {
        return ztext;
    }

    public void setZtext(String ztext) {
        this.ztext = ztext;
    }
}
