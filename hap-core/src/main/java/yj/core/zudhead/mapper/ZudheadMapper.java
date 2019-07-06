package yj.core.zudhead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zudhead.dto.Zudhead;

public interface ZudheadMapper extends Mapper<Zudhead> {
    String selectMaxNo(String curdat);
    int insertHead(Zudhead zudhead);
    /**
     *  根据单号查询不合格品审理单1 表头 917110140
     * @param zudnum
     * @return
     */
    Zudhead selectByZudnum(String zudnum);

    /**
     *  更新不合格品审理单1 表头状态 917110140
     * @param head
     * @return
     */
    int updateHead(Zudhead head);
}