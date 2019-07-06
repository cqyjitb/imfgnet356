package com.hand.hap.system.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.Hotkey;

import java.util.List;

public interface HotkeyMapper extends Mapper<Hotkey>{

    List<Hotkey> queryAll();
}
