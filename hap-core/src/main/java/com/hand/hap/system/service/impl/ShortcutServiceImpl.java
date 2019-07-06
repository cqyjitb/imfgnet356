package com.hand.hap.system.service.impl;

import com.hand.hap.system.mapper.ShortcutMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.hap.system.dto.Shortcut;
import com.hand.hap.system.service.IShortcutService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShortcutServiceImpl extends BaseServiceImpl<Shortcut> implements IShortcutService {

    @Autowired
    ShortcutMapper shortcutMapper;

    @Override
    public List<Shortcut> selectMyShortcutFunction(Long userId) {
        return shortcutMapper.selectMyShortcutFunction(userId);
    }
}