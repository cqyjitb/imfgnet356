package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.resb.dto.Resb;
import yj.core.resb.service.IResbService;
import yj.core.t435t.dto.T435t;
import yj.core.t435t.service.IT435tService;
import yj.core.webservice_server.dto.*;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface="yj.core.webservice_server.IsyncAufnr", serviceName="SyncAufnr")
public class SyncAufnrImp
        implements IsyncAufnr
{
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IT435tService t435tService;
    @Autowired
    private IResbService resbService;

    public ReturnMessage sync(List<Rec_afko> rec_afko, List<Rec_afvc> rec_afvc , List<Rec_marc> rec_marc , List<Rec_t435t> rec_t435t, List<Rec_resb> rec_resbs)
    {
        int num_afko = 0;
        int num_afvc = 0;
        String l_aufnr = null;
        String l_aufpl = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        ReturnMessage rt = new ReturnMessage();
        try
        {
            if (rec_afko.size() > 0) {
                for (int i = 0; i < rec_afko.size(); i++)
                {
                    Afko afko = new Afko();

                    String aufnr = rec_afko.get(i).getAufnr();
                    l_aufnr = aufnr;
                    String mandt = rec_afko.get(i).getMandt();
                    String ponsr = rec_afko.get(i).getPosnr();
                    String auart = rec_afko.get(i).getAuart();
                    String auart_txt = rec_afko.get(i).getAuart_txt();
                    Double gamng = rec_afko.get(i).getGamng();
                    String werks = rec_afko.get(i).getWerks();
                    String charg = rec_afko.get(i).getCharg();
                    String aufpl = rec_afko.get(i).getAufpl();
                    String status = rec_afko.get(i).getStatus();
                    l_aufpl = aufpl;
                    String groes = rec_afko.get(i).getGroes();
                    Double gasmg = rec_afko.get(i).getGasmg();
                    Double igmng = rec_afko.get(i).getIgmng();
                    String gltrp = rec_afko.get(i).getGltrp();
                    String gstrp = rec_afko.get(i).getGstrp();
                    String bukrs = rec_afko.get(i).getBukrs();
                    String dispo = rec_afko.get(i).getDispo();
                    String fevor = rec_afko.get(i).getFevor();
                    String txt = rec_afko.get(i).getTxt();
                    Double umrez = rec_afko.get(i).getUmrez();
                    String maktx = rec_afko.get(i).getMaktx();
                    String plnbez = rec_afko.get(i).getPlnbez();
                    String gmein = rec_afko.get(i).getGmein();
                    String rsnum = rec_afko.get(i).getRsnum();
                    String dsnam = rec_afko.get(i).getDsnam();
                    afko.setBukrs(bukrs);
                    afko.setDispo(dispo);
                    afko.setFevor(fevor);
                    afko.setTxt(txt);
                    afko.setUmrez(umrez);
                    afko.setMaktx(maktx);
                    afko.setPlnbez(plnbez);
                    afko.setGmein(gmein);
                    afko.setGroes(groes);
                    afko.setAufpl(aufpl);
                    afko.setAufnr(aufnr);
                    afko.setMandt(mandt);
                    afko.setPosnr(ponsr);
                    afko.setAuart(auart);
                    afko.setAuart_txt(auart_txt);
                    afko.setGamng(gamng);
                    afko.setWerks(werks);
                    afko.setCharg(charg);
                    afko.setGstrp(gstrp);
                    afko.setGltrp(gltrp);
                    afko.setGasmg(gasmg);
                    afko.setIgmng(igmng);
                    afko.setRsnum(rsnum);
                    afko.setStatus(status);
                    afko.setDsnam(dsnam);
                    if (this.afkoService.selectReturnNum(afko) > 0) {
                        num_afko += this.afkoService.updateSync(afko);
                    } else {
                        num_afko += this.afkoService.insertSync(afko);
                    }
                }
            }
            if (rec_afvc.size() > 0) {
//                for (int i = 0; i < rec_afvc.size(); i++)
//                {
//                    Afvc afvc = new Afvc();
//                    String aufpl = rec_afvc.get(i).getAufpl();
//                    String aplzl = rec_afvc.get(i).getAplzl();
//                    String werks = rec_afvc.get(i).getWerks();
//                    String vornr = rec_afvc.get(i).getVornr();
//                    String steus = rec_afvc.get(i).getSteus();
//                    String ltxa1 = rec_afvc.get(i).getLtxa1();
//                    String arbpl = rec_afvc.get(i).getArbpl();
//                    String ktext = rec_afvc.get(i).getKtext();
//                    String flag =  rec_afvc.get(i).getFlag();
//                    String ktsch = rec_afvc.get(i).getKtsch();
//                    afvc.setAufpl(aufpl);
//                    afvc.setAplzl(aplzl);
//                    afvc.setWerks(werks);
//                    afvc.setVornr(vornr);
//                    afvc.setSteus(steus);
//                    afvc.setLtxa1(ltxa1);
//                    afvc.setArbpl(arbpl);
//                    afvc.setKtext(ktext);
//                    afvc.setKtsch(ktsch);
//                    afvc.setFlag(flag);
//                    if (this.afvcService.selectReturnNum(afvc) > 0) {
//                        num_afvc += this.afvcService.updateSync(afvc);
//                    } else {
//                        num_afvc += this.afvcService.insertSync(afvc);
//                    }
//                }

                //修改逻辑 针对重读主数据问题 先删除整单工序数据 然后重新写工序数据
                    String aufpl = rec_afvc.get(0).getAufpl();
                    afvcService.deleteByAufpl(aufpl);//删除整单数据
                for (int i = 0; i < rec_afvc.size(); i++)
                {
                    Afvc afvc = new Afvc();
                    aufpl = rec_afvc.get(i).getAufpl();
                    String aplzl = rec_afvc.get(i).getAplzl();
                    String werks = rec_afvc.get(i).getWerks();
                    String vornr = rec_afvc.get(i).getVornr();
                    String steus = rec_afvc.get(i).getSteus();
                    String ltxa1 = rec_afvc.get(i).getLtxa1();
                    String arbpl = rec_afvc.get(i).getArbpl();
                    String ktext = rec_afvc.get(i).getKtext();
                    String flag =  rec_afvc.get(i).getFlag();
                    String ktsch = rec_afvc.get(i).getKtsch();
                    afvc.setAufpl(aufpl);
                    afvc.setAplzl(aplzl);
                    afvc.setWerks(werks);
                    afvc.setVornr(vornr);
                    afvc.setSteus(steus);
                    afvc.setLtxa1(ltxa1);
                    afvc.setArbpl(arbpl);
                    afvc.setKtext(ktext);
                    afvc.setKtsch(ktsch);
                    afvc.setFlag(flag);
                    afvc.setCreationDate(new Date());
                        num_afvc += this.afvcService.insertSync(afvc);

                }
            }

            if (rec_marc.size() > 0){

                for (int i = 0;i<rec_marc.size();i++){
                    Marc marc = new Marc();
                    marc.setAttr1(rec_marc.get(i).getAttr1());
                    marc.setAttr1(rec_marc.get(i).getAttr2());
                    marc.setAttr1(rec_marc.get(i).getAttr3());
                    marc.setAttr1(rec_marc.get(i).getAttr4());
                    marc.setAttr1(rec_marc.get(i).getAttr5());
                    marc.setAttr1(rec_marc.get(i).getAttr6());
                    marc.setAttr1(rec_marc.get(i).getAttr7());
                    marc.setAusss(rec_marc.get(i).getAusss());
                    marc.setBrgew(rec_marc.get(i).getBrgew());
                    marc.setBstma(rec_marc.get(i).getBstma());
                    marc.setBstmi(rec_marc.get(i).getBstmi());
                    marc.setBstrf(rec_marc.get(i).getBstrf());
                    marc.setBwtty(rec_marc.get(i).getBwtty());
                    marc.setDispo(rec_marc.get(i).getDispo());
                    marc.setDzeit(rec_marc.get(i).getDzeit());
                    marc.setEisbe(rec_marc.get(i).getEisbe());
                    marc.setFevor(rec_marc.get(i).getFevor());
                    marc.setGewei(rec_marc.get(i).getGewei());
                    marc.setGroes(rec_marc.get(i).getGroes());
                    marc.setLgfsb(rec_marc.get(i).getLgfsb());
                    marc.setLgpro(rec_marc.get(i).getLgpro());
                    marc.setLvorm(rec_marc.get(i).getLvorm());
                    marc.setMabst(rec_marc.get(i).getMabst());
                    marc.setMaktx(rec_marc.get(i).getMaktx());
                    marc.setMatkl(rec_marc.get(i).getMatkl());
                    marc.setMatnr(rec_marc.get(i).getMatnr());
                    marc.setMeins(rec_marc.get(i).getMeins());
                    marc.setMmsta(rec_marc.get(i).getMmsta());
                    marc.setMstae(rec_marc.get(i).getMstae());
                    marc.setMtart(rec_marc.get(i).getMtart());
                    marc.setNtgew(rec_marc.get(i).getNtgew());
                    marc.setRgekz(rec_marc.get(i).getRgekz());
                    marc.setTumrez(rec_marc.get(i).getTumrez());
                    marc.setWerks(rec_marc.get(i).getWerks());
                    marc.setXumrez(rec_marc.get(i).getXumrez());

                    //修改或者新增
                    int sum = marcService.isExit(marc.getMatnr());
                    if (sum > 0){
                        marcService.updateByMatnr(marc);
                    }
                    else{
                        marcService.insertByMatnr(marc);
                    }

                }

            }

            if  (rec_t435t != null){
                if (rec_t435t.size() > 0){
                    for (int i = 0;i<rec_t435t.size();i++){
                        T435t t435t = new T435t();
                        t435t.setVlsch(rec_t435t.get(i).getVlsch());
                        t435t.setTxt(rec_t435t.get(i).getTxt());

                        int sum = t435tService.isExit(t435t.getVlsch());
                        if (sum > 0){
                            t435tService.updateByVlsch(t435t);
                        }else{
                            t435tService.insertByVlsch(t435t);
                        }
                    }
                }
            }


            if (rec_resbs.size() > 0){
                String aufpl = rec_resbs.get(0).getAufpl();
                resbService.deleteByAufpl(aufpl);
//                for (int i = 0;i<rec_resbs.size();i++){
//                    Resb resb = new Resb();
//                    resb.setAplzl(rec_resbs.get(i).getAplzl());
//                    resb.setAufpl(rec_resbs.get(i).getAufpl());
//                    resb.setBdmng(rec_resbs.get(i).getBdmng());
//                    resb.setLgort(rec_resbs.get(i).getLgort());
//                    resb.setMaktx(rec_resbs.get(i).getMaktx());
//                    resb.setMatnr(rec_resbs.get(i).getMatnr());
//                    resb.setMeins(rec_resbs.get(i).getMeins());
//                    resb.setPosnr(rec_resbs.get(i).getPosnr());
//                    resb.setPostp(rec_resbs.get(i).getPostp());
//                    resb.setRgekz(rec_resbs.get(i).getRgekz());
//                    resb.setRsnum(rec_resbs.get(i).getRsnum());
//                    resb.setRspos(rec_resbs.get(i).getRspos());
//                    resb.setVornr(rec_resbs.get(i).getVornr());
//                    resb.setXloek(rec_resbs.get(i).getXloek());
//                    resb.setWerks(rec_resbs.get(i).getWerks());
//
//                    int sum = resbService.isExit(resb.getRsnum(),resb.getRspos());
//                    if (sum > 0){
//                        resb.setLastUpdatedDate(new Date());
//                        int num = resbService.updateByRsnum(resb);
//                    }else{
//                        resb.setCreationDate(new Date());
//                        int num = resbService.insertByRsnum(resb);
//                    }
//
//                }

                for (int i = 0;i<rec_resbs.size();i++){
                    Resb resb = new Resb();
                    resb.setAplzl(rec_resbs.get(i).getAplzl());
                    resb.setAufpl(rec_resbs.get(i).getAufpl());
                    resb.setBdmng(rec_resbs.get(i).getBdmng());
                    resb.setLgort(rec_resbs.get(i).getLgort());
                    resb.setMaktx(rec_resbs.get(i).getMaktx());
                    resb.setMatnr(rec_resbs.get(i).getMatnr());
                    resb.setMeins(rec_resbs.get(i).getMeins());
                    resb.setPosnr(rec_resbs.get(i).getPosnr());
                    resb.setPostp(rec_resbs.get(i).getPostp());
                    resb.setRgekz(rec_resbs.get(i).getRgekz());
                    resb.setRsnum(rec_resbs.get(i).getRsnum());
                    resb.setRspos(rec_resbs.get(i).getRspos());
                    resb.setVornr(rec_resbs.get(i).getVornr());
                    resb.setXloek(rec_resbs.get(i).getXloek());
                    resb.setWerks(rec_resbs.get(i).getWerks());


                        resb.setCreationDate(new Date());
                        int num = resbService.insertByRsnum(resb);


                }
            }
            rt.setFlag("S");
            rt.setMessage("AFKO:" + l_aufnr + ",AFVC:" + l_aufpl);
            rt.setSyncdate(sdf.format(new Date()));
            return rt;
        }
        catch (Exception e)
        {
            System.out.println(e);
            rt.setFlag("E");
            rt.setMessage(e.toString());
            rt.setSyncdate(sdf.format(new Date()));
        }
        return rt;
    }
}
