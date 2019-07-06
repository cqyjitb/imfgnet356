package yj.core.zrwklist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zrwklist.dto.Zrwklist;

import java.util.List;

public interface ZrwklistMapper extends Mapper<Zrwklist> {
    int insertItem(Zrwklist list);

    /**
     * 机加返工返修单报表查询 918100064
     * @param dto
     * @return
     */
    List<Zrwklist> selectZrwklist(Zrwklist dto);
}