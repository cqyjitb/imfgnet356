package com.hand.hap.flexfield.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.flexfield.dto.FlexModel;

import java.util.List;

public interface IFlexModelService extends IBaseService<FlexModel>, ProxySelf<IFlexModelService> {

    /**删除弹性域模型
     * @param models 需要删除的FlexModel
     */
    void deleteFlexModel(List<FlexModel> models);

}