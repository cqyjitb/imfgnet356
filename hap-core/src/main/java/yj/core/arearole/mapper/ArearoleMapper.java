package yj.core.arearole.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.arearole.dto.Arearole;

public interface ArearoleMapper extends Mapper<Arearole> {

    int insertArearole(Arearole arearole);

    int deleteArearole(Arearole arearole);
}