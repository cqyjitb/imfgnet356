package com.hand.hap.system.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.system.dto.Hotkey;

import java.util.List;

public interface IHotkeyService extends IBaseService<Hotkey>, ProxySelf<IHotkeyService>{

    /**
     * 获取首选项展示的热键数据
     *
     * @return
     */
     List<Hotkey> preferenceQuery(IRequest iRequest);

}