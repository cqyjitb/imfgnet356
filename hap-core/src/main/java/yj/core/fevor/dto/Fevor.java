package yj.core.fevor.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name="sap_fevor")
public class Fevor extends BaseDTO {
    @Id
    @GeneratedValue
    private String werks;
    @NotNull
    private String fevor;
    private String txt;
    private String unitCode;
    private String shiftSeq;//班次轮换类型
    private String shiftDay;//班次轮换日
    private Date creationDate; //创建时间
    private Long createdBy; //创建人
    private Date lastUpdateDate; //更新时间
    private Long lastUpdatedBy; //更新人

    public String getShiftSeq() {
        return shiftSeq;
    }

    public void setShiftSeq(String shiftSeq) {
        this.shiftSeq = shiftSeq;
    }

    public String getShiftDay() {
        return shiftDay;
    }

    public void setShiftDay(String shiftDay) {
        this.shiftDay = shiftDay;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
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

    @Override
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
