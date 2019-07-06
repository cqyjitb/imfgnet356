package com.hand.hap.system.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.Shortcut;

import java.util.List;

public interface ShortcutMapper extends Mapper<Shortcut> {

    List<Shortcut> selectMyShortcutFunction(Long userId);

}