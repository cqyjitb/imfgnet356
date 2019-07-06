package yj.kanb.viewdataschemaline.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "vb_line_group_h")
public class Viewdataschemaline extends BaseDTO {

    @Id
    @GeneratedValue
    private String groupId;
    private String product;
    private String workshopId;
    private String bukrs;
    private String werks;

    @NotEmpty
    private String matnr;
    private String maktx;

    private String kunnr;//客户编码
    private String name1;//客户名称
    private String sortl;//客户简称
    private String classgrp;//班组
    private String shift;//班次
    private String shiftdes;//班次文本
    private String shifttimebegin;
    private String shifttimeend;
    private String lineLeader;
    private String lineLeaderEn;
    private String leaderPhone;
    private Double cycletime;
    private String workshopName;
    private Double planqty;
    private Double actqty;
    private Double insufqty;
    private Double jdcqqty;//进度差缺
    private Double qcRate;
    private Double oeeRate;
    private Date erdat;
    private Date creationDate; //创建时间

    private Long createdBy; //创建人

    private Date lastUpdateDate; //更新时间

    private Long lastUpdatedBy; //更新人

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getShifttimebegin() {
        return shifttimebegin;
    }

    public void setShifttimebegin(String shifttimebegin) {
        this.shifttimebegin = shifttimebegin;
    }

    public String getShifttimeend() {
        return shifttimeend;
    }

    public void setShifttimeend(String shifttimeend) {
        this.shifttimeend = shifttimeend;
    }

    public String getLineLeader() {
        return lineLeader;
    }

    public void setLineLeader(String lineLeader) {
        this.lineLeader = lineLeader;
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public Double getCycletime() {
        return cycletime;
    }

    public void setCycletime(Double cycletime) {
        this.cycletime = cycletime;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public Double getPlanqty() {
        return planqty;
    }

    public void setPlanqty(Double planqty) {
        this.planqty = planqty;
    }

    public Double getActqty() {
        return actqty;
    }

    public void setActqty(Double actqty) {
        this.actqty = actqty;
    }

    public Double getInsufqty() {
        return insufqty;
    }

    public void setInsufqty(Double insufqty) {
        this.insufqty = insufqty;
    }

    public Double getQcRate() {
        return qcRate;
    }

    public void setQcRate(Double qcRate) {
        this.qcRate = qcRate;
    }

    public Double getOeeRate() {
        return oeeRate;
    }

    public void setOeeRate(Double oeeRate) {
        this.oeeRate = oeeRate;
    }

    public String getClassgrp() {
        return classgrp;
    }

    public void setClassgrp(String classgrp) {
        this.classgrp = classgrp;
    }

    public String getShiftdes() {
        return shiftdes;
    }

    public void setShiftdes(String shiftdes) {
        this.shiftdes = shiftdes;
    }

    public Double getJdcqqty() {
        return jdcqqty;
    }

    public void setJdcqqty(Double jdcqqty) {
        this.jdcqqty = jdcqqty;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getSortl() {
        return sortl;
    }

    public void setSortl(String sortl) {
        this.sortl = sortl;
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

    public Date getErdat() {
        return erdat;
    }

    public void setErdat(Date erdat) {
        this.erdat = erdat;
    }

    public String getLineLeaderEn() {
        return lineLeaderEn;
    }

    public void setLineLeaderEn(String lineLeaderEn) {
        this.lineLeaderEn = lineLeaderEn;
    }
}
