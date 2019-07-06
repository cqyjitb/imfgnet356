package yj.core.webservice_migo.dto;

/**
 * Created by 917110140 on 2018/8/31.
 */
public class DTMIGOReturn {
    private String MBLNR;
    private String MJAHR;
    private String MTYPE;
    private String MTMSG;

    public String getMBLNR() {
        return MBLNR;
    }

    public void setMBLNR(String MBLNR) {
        this.MBLNR = MBLNR;
    }

    public String getMJAHR() {
        return MJAHR;
    }

    public void setMJAHR(String MJAHR) {
        this.MJAHR = MJAHR;
    }

    public String getMTYPE() {
        return MTYPE;
    }

    public void setMTYPE(String MTYPE) {
        this.MTYPE = MTYPE;
    }

    public String getMTMSG() {
        return MTMSG;
    }

    public void setMTMSG(String MTMSG) {
        this.MTMSG = MTMSG;
    }
}
