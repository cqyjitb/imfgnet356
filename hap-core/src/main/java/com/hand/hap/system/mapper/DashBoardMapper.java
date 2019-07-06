package com.hand.hap.system.mapper;

import java.util.List;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.DashBoard;

/**
 * 
 * @author zhizheng.yang@hand-china.com
 */
public interface DashBoardMapper extends Mapper<DashBoard> {

    List<DashBoard> selectDashBoards(DashBoard dashBoard);

}
