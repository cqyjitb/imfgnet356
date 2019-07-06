package yj.core.imfpmcheckdoc.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.imfpmcheckdoc.dto.Checkdoc;
import yj.core.imfpmcheckdoc.mapper.CheckdocMapper;
import yj.core.imfpmcheckdoc.service.ICheckdocService;

@Service
@Transactional
public class CheckdocServiceImpl extends BaseServiceImpl<Checkdoc> implements ICheckdocService {

    @Autowired
    private CheckdocMapper checkdocMapper;



}