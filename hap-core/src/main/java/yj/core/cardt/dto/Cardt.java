package yj.core.cardt.dto;

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
@Table(name="sap_cardt")
public class Cardt
        extends BaseDTO
{
    @NotEmpty
    private String zpgdbar; //派工单条码

    @Id
    @GeneratedValue
    private String zpgdbh; //派工单编号

    @NotEmpty
    private String zgxbar; //工序条码

    @NotEmpty
    private String zgxbh; //工序编号

    private String arbpl; //工作中心

    private String ktext; //工作中心短描述

    private String aufnr;

    private String confirmed;

    private String vornr; //操作/活动编号

    private String steus;

    private String ktsch;

    private String ltxa1; //工序短文本

    private String werks; //工厂

    private String zsfdy; //是否进行打印

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;
    @Transient
    private String zxhbar;
    @Transient
    private String maktx;

    private Date creationDate; //创建时间

    @NotNull
    private Long createdBy; //创建人

    private Date lastUpdatedDate; //更新时间

    private Long lastUpdatedBy; //更新人

    public void setZpgdbar(String zpgdbar)
    {
        this.zpgdbar = zpgdbar;
    }

    public String getZpgdbar()
    {
        return this.zpgdbar;
    }

    public void setZpgdbh(String zpgdbh)
    {
        this.zpgdbh = zpgdbh;
    }

    public String getZpgdbh()
    {
        return this.zpgdbh;
    }

    public void setZgxbar(String zgxbar)
    {
        this.zgxbar = zgxbar;
    }

    public String getZgxbar()
    {
        return this.zgxbar;
    }

    public void setZgxbh(String zgxbh)
    {
        this.zgxbh = zgxbh;
    }

    public String getZgxbh()
    {
        return this.zgxbh;
    }

    public void setArbpl(String arbpl)
    {
        this.arbpl = arbpl;
    }

    public String getArbpl()
    {
        return this.arbpl;
    }

    public void setKtext(String ktext)
    {
        this.ktext = ktext;
    }

    public String getKtext()
    {
        return this.ktext;
    }

    public void setVornr(String vornr)
    {
        this.vornr = vornr;
    }

    public String getVornr()
    {
        return this.vornr;
    }

    public void setLtxa1(String ltxa1)
    {
        this.ltxa1 = ltxa1;
    }

    public String getLtxa1()
    {
        return this.ltxa1;
    }

    public void setWerks(String werks)
    {
        this.werks = werks;
    }

    public String getWerks()
    {
        return this.werks;
    }

    public void setZsfdy(String zsfdy)
    {
        this.zsfdy = zsfdy;
    }

    public String getZsfdy()
    {
        return this.zsfdy;
    }

    public void setAttr1(String attr1)
    {
        this.attr1 = attr1;
    }

    public String getAttr1()
    {
        return this.attr1;
    }

    public void setAttr2(String attr2)
    {
        this.attr2 = attr2;
    }

    public String getAttr2()
    {
        return this.attr2;
    }

    public void setAttr3(String attr3)
    {
        this.attr3 = attr3;
    }

    public String getAttr3()
    {
        return this.attr3;
    }

    public void setAttr4(String attr4)
    {
        this.attr4 = attr4;
    }

    public String getAttr4()
    {
        return this.attr4;
    }

    public void setAttr5(String attr5)
    {
        this.attr5 = attr5;
    }

    public String getAttr5()
    {
        return this.attr5;
    }

    public void setAttr6(String attr6)
    {
        this.attr6 = attr6;
    }

    public String getAttr6()
    {
        return this.attr6;
    }

    public void setAttr7(String attr7)
    {
        this.attr7 = attr7;
    }

    public String getAttr7()
    {
        return this.attr7;
    }

    public String getZxhbar()
    {
        return this.zxhbar;
    }

    public void setZxhbar(String zxhbar)
    {
        this.zxhbar = zxhbar;
    }

    public String getMaktx()
    {
        return this.maktx;
    }

    public void setMaktx(String maktx)
    {
        this.maktx = maktx;
    }

    public String getAufnr() {
        return aufnr;
    }

    public void setAufnr(String aufnr) {
        this.aufnr = aufnr;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getKtsch() {
        return ktsch;
    }

    public void setKtsch(String ktsch) {
        this.ktsch = ktsch;
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


    public void setSteus(String steus){
        this.steus = steus;
    }

    public String getSteus(){
        return steus;
    }
}
