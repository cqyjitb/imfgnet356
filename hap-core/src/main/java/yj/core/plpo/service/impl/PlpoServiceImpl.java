package yj.core.plpo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.plpo.dto.Plpo;
import yj.core.plpo.service.IPlpoService;

@Service
@Transactional
public class PlpoServiceImpl extends BaseServiceImpl<Plpo> implements IPlpoService {

}