/*
 * #{copyright}#
 */

package com.hand.hap.system.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.dto.Language;

import java.util.List;

/**
 * @author shengyang.zhou@hand-china.com
 */
public interface ILanguageService extends IBaseService<Language>, ProxySelf<ILanguageService> {

    /**
     * 更新Language
     * @param request requestContext
     * @param list dto list
     * @return 结果列表
     */
    List<Language> submit(IRequest request, @StdWho List<Language> list);

    /**
     *删除Language
     * @param list dto list
     * @return 影响行数
     */
    int remove(List<Language> list);
}
