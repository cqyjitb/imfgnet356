/*
 * #{copyright}#
 */
package com.hand.hap.attachment.mapper;

import java.util.List;

import com.hand.hap.attachment.dto.AttachCategory;
import com.hand.hap.attachment.dto.Attachment;
import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiaohua on 16/2/1.
 * 
 * @author hua.xiao@hand-china.com
 */
public interface AttachCategoryMapper extends Mapper<AttachCategory> {

    /**
     * 根据参数查找AttachCategory.
     * 
     * @param attachCategory
     *            AttachCategory对象
     * @return AttachCategory 插入的AttachCategory对象
     */
    AttachCategory selectCategory(AttachCategory attachCategory);


    int countSourceType(String sourceType);

    /**
     * 查询父节点目录.
     * 
     * @param idList
     * @return List
     */
    List<AttachCategory> selectAllParentCategory(List<Long> idList);

    /*
     * 查询所有节点
     */
    List<AttachCategory> queryTree(AttachCategory attachCategory);

    /* 验证type和size */
    List<AttachCategory> querySize(@Param("sourceType") String sourceType, @Param("sourceKey") String sourceKey);
}
