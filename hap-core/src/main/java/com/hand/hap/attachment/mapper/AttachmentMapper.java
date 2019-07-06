/*
 * #{copyright}#
 */
package com.hand.hap.attachment.mapper;

import java.util.Map;

import com.hand.hap.attachment.dto.Attachment;
import com.hand.hap.mybatis.common.Mapper;

/**
 * Created by xiaohua on 16/2/1.
 * @author hua.xiao@hand-china.com
 */
public interface  AttachmentMapper extends Mapper<Attachment> {


    /**
     * 根据Attachment对象查找单个Attachment.
     * 
     * @param attachment Attachment对象
     * @return  Attachment Attachment对象
     */
    Attachment selectAttachment(Attachment attachment);
    

    /**
     * 更新来源主键.
     * 
     * @param param 参数
     */
    int upgradeSourceKey(Map<String, Object> param);

}
