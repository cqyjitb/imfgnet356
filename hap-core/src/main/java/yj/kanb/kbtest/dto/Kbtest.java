package yj.kanb.kbtest.dto;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ExtensionAttribute(disable=true)
@Table(name = "kbtest")
public class Kbtest extends BaseDTO {
    @Id
    @GeneratedValue
    private String id; //上线记录号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
