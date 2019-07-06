package yj.core.tmp.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name="bartobar")
public class Bartobar
        extends BaseDTO
{
    @Id
    @GeneratedValue
    private Long id;
    private String wipZpgdbar;
    private String wipAufnr;
    private String wipAuart;
    private String wipArbpl;
    private String wipMatnr;
    private String newAufnr;
    private String newZpgdbar;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setWipZpgdbar(String wipZpgdbar)
    {
        this.wipZpgdbar = wipZpgdbar;
    }

    public String getWipZpgdbar()
    {
        return this.wipZpgdbar;
    }

    public void setWipAufnr(String wipAufnr)
    {
        this.wipAufnr = wipAufnr;
    }

    public String getWipAufnr()
    {
        return this.wipAufnr;
    }

    public void setWipAuart(String wipAuart)
    {
        this.wipAuart = wipAuart;
    }

    public String getWipAuart()
    {
        return this.wipAuart;
    }

    public void setWipArbpl(String wipArbpl)
    {
        this.wipArbpl = wipArbpl;
    }

    public String getWipArbpl()
    {
        return this.wipArbpl;
    }

    public void setWipMatnr(String wipMatnr)
    {
        this.wipMatnr = wipMatnr;
    }

    public String getWipMatnr()
    {
        return this.wipMatnr;
    }

    public void setNewAufnr(String newAufnr)
    {
        this.newAufnr = newAufnr;
    }

    public String getNewAufnr()
    {
        return this.newAufnr;
    }

    public void setNewZpgdbar(String newZpgdbar)
    {
        this.newZpgdbar = newZpgdbar;
    }

    public String getNewZpgdbar()
    {
        return this.newZpgdbar;
    }
}
