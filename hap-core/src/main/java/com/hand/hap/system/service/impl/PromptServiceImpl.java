package com.hand.hap.system.service.impl;

import com.hand.hap.cache.CacheDelete;
import com.hand.hap.cache.CacheSet;
import com.hand.hap.cache.impl.HashStringRedisCacheGroup;
import com.hand.hap.core.BaseConstants;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.core.i18n.CacheMessageSource;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.message.components.DefaultPromptListener;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.system.dto.Prompt;
import com.hand.hap.system.service.IPromptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述维护.
 *
 * @author njq.niu@hand-china.com
 * @date 2016/6/9.
 */
@Service
public class PromptServiceImpl extends BaseServiceImpl<Prompt> implements IPromptService {

    @Autowired
    private IMessagePublisher messagePublisher;

    @Autowired
    @Qualifier("promptCache")
    private HashStringRedisCacheGroup<String> promptCache;

    @Override
    public List<Prompt> submit(IRequest request, List<Prompt> list) {
        self().batchUpdate(request, list);
        for (Prompt prompt : list) {
            notifyCache(prompt);
            notifyPromptCache(prompt);
        }
        return list;
    }

    @Override
    public String getPromptDescription(String locale, String promptCode) {
        String description = promptCache.getGroup(locale).getValue(promptCode);
        if (description == null) {
            Prompt prompt = new Prompt();
            prompt.setLang(locale);
            prompt.setPromptCode(promptCode);
            Criteria criteria = new Criteria(prompt);
            criteria.where(Prompt.FIELD_LANG, Prompt.FIELD_PROMPT_CODE);
            criteria.select(Prompt.FIELD_DESCRIPTION);
            List<Prompt> prompts = super.selectOptions(null,prompt,criteria);
            if(CollectionUtils.isNotEmpty(prompts)){
                description = prompts.get(0).getDescription();
                promptCache.getGroup(locale).setValue(promptCode,description);
            }
        }
        return description;
    }

    @Override
    @CacheSet(cache = BaseConstants.CACHE_PROMPT)
    public Prompt insertSelective(IRequest request, @StdWho Prompt prompt) {
        super.insertSelective(request, prompt);
        return prompt;
    }

    @Override
    @CacheDelete(cache = BaseConstants.CACHE_PROMPT)
    public int deleteByPrimaryKey(Prompt prompt) {
        return super.deleteByPrimaryKey(prompt);
    }

    @Override
    @CacheSet(cache = BaseConstants.CACHE_PROMPT)
    public Prompt updateByPrimaryKeySelective(IRequest request, @StdWho Prompt prompt) {
        return super.updateByPrimaryKeySelective(request, prompt);
    }

    @Override
    @CacheSet(cache = BaseConstants.CACHE_PROMPT)
    public Prompt updateByPrimaryKey(IRequest request, Prompt prompt) {
        return super.updateByPrimaryKey(request, prompt);
    }

    private void notifyCache(Prompt prompt) {
        messagePublisher.publish(DefaultPromptListener.CACHE_PROMPT, prompt.getPromptCode());
    }

    private void notifyPromptCache(Prompt prompt) {
        messagePublisher.publish(CacheMessageSource.CACHE_PROMPT_ALL, prompt);
    }
}
