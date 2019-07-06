package com.hand.hap.account.service;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.core.exception.BaseException;

import java.util.List;

/**
 * 用户信息服务接口.
 *
 * @author Zhaoqi
 */
public interface IUserInfoService extends ProxySelf<IUserInfoService> {

    /**
     * 创建新用户.
     *
     * @param request 统一上下文
     * @param user    用户信息
     * @return 用户
     * @throws UserException 抛出创建用户失败异常
     * @throws Exception     抛出短信接口异常
     */
    User create(IRequest request, @StdWho User user) throws UserException, Exception;

    /**
     * 更新用户信息.
     *
     * @param request 统一上下文
     * @param user    用户信息
     * @return 用户
     * @throws BaseException 抛出短信接口异常
     */
    User update(IRequest request, @StdWho User user) throws BaseException;

    /**
     * 条件查询用户信息.
     *
     * @param request  统一上下文
     * @param user     包含传入的用户查找依据
     * @param page     页码
     * @param pageSize 每页显示数量
     * @return 用户集合
     * @throws UserException 抛出未找到用户的业务异常
     */
    List<User> getUsers(IRequest request, User user, int page, int pageSize)
            throws UserException;

    /**
     * 忘记密码,接收用户ID查询用户详细信息.
     *
     * @param request 统一上下文
     * @param userId  用户ID
     * @return 用户
     * @throws UserException 抛出未找到用户的业务异常
     */
    User selectUserByPrimaryKey(IRequest request, Long userId) throws UserException;

    /**
     * 忘记密码,接收用户登录名查询用户详细信息.
     *
     * @param request   统一上下文
     * @param loginName 用户登录名
     * @return 用户
     * @throws UserException 抛出未找到用户的业务异常
     */
    User selectUserByName(IRequest request, String loginName) throws UserException;

}
