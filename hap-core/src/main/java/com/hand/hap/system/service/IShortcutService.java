package com.hand.hap.system.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.system.dto.Shortcut;

import java.util.List;

public interface IShortcutService extends IBaseService<Shortcut>, ProxySelf<IShortcutService> {

    List<Shortcut> selectMyShortcutFunction(Long userId);

}