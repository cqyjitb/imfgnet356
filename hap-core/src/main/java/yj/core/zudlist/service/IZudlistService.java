package yj.core.zudlist.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface IZudlistService extends IBaseService<Zudlist>, ProxySelf<IZudlistService> {
    int insertItem(List<Zudlist> list);
    //List<Zudlist> selectZudlist(IRequest requestContext, Zudlist dto, int page, int pageSize);

    /**
     *  更新不合格品审理单1 行信息 917110140
     * @param zudlist
     * @return
     */
    int updateItem(Zudlist zudlist);

    /**
     *  根据表单号查询 status = 0 的记录 917110140
     * @param zudnum
     * @return
     */
    List<Zudlist> selectByZudnumForUnprocess(String zudnum);
    List<Zudlist> selectZudlist(IRequest requestContext, Zudlist dto);

    /**
     *  根据单号 行号查询行记录 917110140
     * @param zudnum
     * @param item
     * @return
     */
    Zudlist selectByIdAndItem(String zudnum, String item);

    List<Zudlist> selectForPassrateReportgf(String lineId, String matnr, String datestart, String dateend);

    List<Zudlist> selectForPassrateReportlf(String lineId, String matnr, String datestart, String dateend);

    List<Zudlist> selectForBaltuChart(String lineId, String matnr, String datastart, String dateend);
}