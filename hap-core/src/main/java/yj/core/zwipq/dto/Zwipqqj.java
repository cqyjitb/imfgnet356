package yj.core.zwipq.dto;

/**
 * Created by 917110140 on 2018/9/11.
 * 机加取件页面与后台数据传输结构
 */
public class Zwipqqj {
    private int id;
    private String sfflg;
    private int menge;
    private int cursum;
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

    public void setSfflg(String stfflg) {
        this.sfflg = stfflg;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public int getCursum() {
        return cursum;
    }

    public void setCursum(int cursum) {
        this.cursum = cursum;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
