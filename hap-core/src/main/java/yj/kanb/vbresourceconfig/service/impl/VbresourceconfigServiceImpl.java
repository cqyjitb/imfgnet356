package yj.kanb.vbresourceconfig.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbresourceconfig.dto.Vbresourceconfig;
import yj.kanb.vbresourceconfig.service.IVbresourceconfigService;

@Service
@Transactional
public class VbresourceconfigServiceImpl extends BaseServiceImpl<Vbresourceconfig> implements IVbresourceconfigService {
}
