package com.hand.hap.security.oauth.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.security.oauth.dto.TokenLogs;

/**
 * @author qixiangyu
 */
public interface ITokenLogsService extends IBaseService<TokenLogs>, ProxySelf<ITokenLogsService> {

    /**
     * 失效Token.
     *
     * @param token token
     * @return 影响行数
     */
    int revokeToken(String token);
}