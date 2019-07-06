package yj.core.pandiantcode.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.pandiantcode.dto.Pandiantcode;

public interface PandiantcodeMapper extends Mapper<Pandiantcode> {

    /**
     *  检查天津盘点权限 917110140
     * @param username
     * @return
     */
    Pandiantcode checkTjcode(String username);

    /**
     *  检查工装制造部盘点权限 917110140
     * @param username
     * @return
     */
    Pandiantcode checkGzcode(String username);

}