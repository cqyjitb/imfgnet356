package com.hand.hap.generator.service;

import com.hand.hap.generator.dto.GeneratorInfo;

import java.util.List;

/**
 * Created by jialong.zuo@hand-china.com on 2016/10/24.
 */
public interface IHapGeneratorService {
    public List<String> showTables();

    public int generatorFile(GeneratorInfo info);

}
