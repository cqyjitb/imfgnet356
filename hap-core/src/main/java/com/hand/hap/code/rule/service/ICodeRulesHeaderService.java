package com.hand.hap.code.rule.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.code.rule.dto.CodeRulesHeader;

public interface ICodeRulesHeaderService extends IBaseService<CodeRulesHeader>, ProxySelf<ICodeRulesHeaderService> {

    CodeRulesHeader createCodeRule(CodeRulesHeader record);

    CodeRulesHeader updateCodeRule( CodeRulesHeader record);
}