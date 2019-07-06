package yj.kanb.vbgroupdtl.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "vb_group_dtl")
public class Vbgroupdtl extends BaseDTO {
    @Id
    @GeneratedValue
    private String vbgroupId;
    private String bukrs;
    private String werks;

    @NotEmpty
    private String groupId;
    private String groupType;


    public String getVbgroupId() {
        return vbgroupId;
    }

    public void setVbgroupId(String vbgroupId) {
        this.vbgroupId = vbgroupId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
