package yj.core.stpo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.stpo.dto.Stpo;
import yj.core.stpo.service.IStpoService;

@Service
@Transactional
public class StpoServiceImpl extends BaseServiceImpl<Stpo> implements IStpoService {

}