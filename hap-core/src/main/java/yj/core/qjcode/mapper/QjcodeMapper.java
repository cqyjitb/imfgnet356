package yj.core.qjcode.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.qjcode.dto.Qjcode;

import java.util.List;

public interface QjcodeMapper extends Mapper<Qjcode> {
    List<Qjcode> selectcode();
    Qjcode selectById(int id);
}