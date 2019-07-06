package yj.kanb.wipdateclass.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.Table;
import java.util.Date;

@ExtensionAttribute(disable=true)
@Table(name = "wip_date_class")
public class DateClass extends BaseDTO {
    private String startDate;//开始日期
    private String endDate;//结束日期
    private String className;//类名
    private Date creationDate;

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
