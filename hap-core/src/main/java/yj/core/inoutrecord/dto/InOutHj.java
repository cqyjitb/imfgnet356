package yj.core.inoutrecord.dto;

/**
 * Created by 917110140 on 2018/9/14.
 */
public class InOutHj {
    private int id;
    private String sfflg;
    private String ZOTYPE;
    private String  VORNR;
    private String checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSfflg() {
        return sfflg;
    }

    public void setSfflg(String sfflg) {
        this.sfflg = sfflg;
    }

    public String getZOTYPE() {
        return ZOTYPE;
    }

    public void setZOTYPE(String ZOTYPE) {
        this.ZOTYPE = ZOTYPE;
    }

    public String getVORNR() {
        return VORNR;
    }

    public void setVORNR(String VORNR) {
        this.VORNR = VORNR;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
