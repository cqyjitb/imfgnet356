package com.hand.hap.testext.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import com.hand.hap.testext.dto.UserDemo;
import com.hand.hap.testext.service.IUserDemoService;

@Service
@Transactional
public class UserDemoServiceImpl extends BaseServiceImpl<UserDemo> implements IUserDemoService {

}