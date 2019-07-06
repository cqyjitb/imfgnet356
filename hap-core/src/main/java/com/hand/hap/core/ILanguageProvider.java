/*
 * #{copyright}#
 */

package com.hand.hap.core;

import java.util.List;

import com.hand.hap.system.dto.Language;

/**
 * 用于获取系统所支持的语言环境.
 * 
 * @author shengyang.zhou@hand-china.com
 */
public interface ILanguageProvider {

    /**
     * 取得系统支持的语言.
     *
     * @return 没有数据的话, 返回空的 list. 不返回null
     */
    List<Language> getSupportedLanguages();
}
