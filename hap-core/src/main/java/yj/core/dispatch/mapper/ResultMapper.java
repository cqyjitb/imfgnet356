package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Result;
import yj.core.webservice.dto.DTPP001ReturnResult;

public interface ResultMapper extends Mapper<Result> {

    int queryResultById(Long id);//根据ID查询数据confirmation_result

    int insertResult(Result result);//插入一条数据到confirmation_result

    public DTPP001ReturnResult inputDispatch(InputLog input);

    public void nextId(InputLog input);

    void updateReveseByInputId(Long inputId);

    Result selectByInputId(Long inputId);

    int updateRsnumAndRspos(Result result);
}