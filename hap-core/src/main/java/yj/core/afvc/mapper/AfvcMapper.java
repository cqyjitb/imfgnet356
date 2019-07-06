package yj.core.afvc.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.afvc.dto.Afvc;

import java.util.List;

public  interface AfvcMapper
        extends Mapper<Afvc>
{
    public abstract int updateSync(Afvc paramAfvc);

    public abstract int insertSync(Afvc paramAfvc);

    public abstract int selectReturnNum(Afvc paramAfvc);

    public abstract List<Afvc> selectByAufpl(String paramString);

    List<Afvc> selectByAufnr(String aufnr);

    /**
     *  根据 aufpl 删除数据 917110140
     * @param aufpl
     * @return
     */
    int deleteByAufpl(String aufpl);

    /**
     * 工作中心查询 LOV_AFVC  918100064
     * @param arbpl
     * @return
     */
    List<Afvc> selectByArbpl(@Param("arbpl") String arbpl);

    /**
     *  根据派工单 查询工作中心 917110140
     * @param zpgdbar
     * @return
     */
    Afvc selectByZpgdbar(String zpgdbar);


}
