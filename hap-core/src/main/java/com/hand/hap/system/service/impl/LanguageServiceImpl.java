/*
 * #{copyright}#
 */
package com.hand.hap.system.service.impl;

import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.core.impl.LanguageProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hand.hap.core.IRequest;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.system.dto.Language;
import com.hand.hap.system.service.ILanguageService;

import java.util.List;

/**
 * @author shengyang.zhou@hand-china.com
 */
@Service
public class LanguageServiceImpl extends BaseServiceImpl<Language> implements ILanguageService {

    @Autowired
    private IMessagePublisher messagePublisher;

    @Override
    public List<Language> submit(IRequest request, @StdWho List<Language> list){
        self().batchUpdate(request, list);
        notifyCache();
        return  list;
    }

    @Override
    public int remove(List<Language> list) {
        int result = self().batchDelete(list);
        notifyCache();
        return result;
    }

    /**
     * 更新缓存数据
     */
    private void notifyCache() {
        messagePublisher.publish(LanguageProviderImpl.CACHE_LANGUAGE, "language");
    }
}
