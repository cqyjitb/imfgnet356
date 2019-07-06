package com.hand.hap.account.mapper;

import com.hand.hap.account.dto.SendRetrieve;
import com.hand.hap.mybatis.common.Mapper;

/**
 * 发送次数限制接口.
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface SendRetrieveMapper extends Mapper<SendRetrieve> {

    int insertRecord(SendRetrieve record);

    int query(SendRetrieve sendRetrieve);
}