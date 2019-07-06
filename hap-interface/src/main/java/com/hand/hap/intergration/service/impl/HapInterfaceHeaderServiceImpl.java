package com.hand.hap.intergration.service.impl;


import java.util.List;
import java.util.UUID;

import com.github.pagehelper.PageHelper;
import com.hand.hap.cache.impl.ApiConfigCache;
import com.hand.hap.core.IRequest;
import com.hand.hap.intergration.dto.HapInterfaceHeader;
import com.hand.hap.intergration.dto.HapInterfaceLine;
import com.hand.hap.intergration.mapper.HapInterfaceHeaderMapper;
import com.hand.hap.intergration.service.IHapAuthenticationService;
import com.hand.hap.intergration.service.IHapInterfaceHeaderService;
import com.hand.hap.intergration.service.IHapInterfaceLineService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jiguang.sun@hand-china.com
 * @date 2016/7/21.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HapInterfaceHeaderServiceImpl extends BaseServiceImpl<HapInterfaceHeader> implements IHapInterfaceHeaderService {

    private final Logger logger = LoggerFactory.getLogger(HapInterfaceHeaderServiceImpl.class);

    @Autowired
    private HapInterfaceHeaderMapper hapInterfaceHeaderMapper;

    @Autowired
    private ApiConfigCache apiCache;


    @Autowired
    private IHapInterfaceLineService lineService;

    @Autowired
    private IHapAuthenticationService authenticationService;

    private static String AUTH_TYPE_OAUTH2 = "OAUTH2";


    @Override
    public List<HapInterfaceHeader> getAllHeader(IRequest requestContext, HapInterfaceHeader interfaceHeader, int page, int pagesize) {

        PageHelper.startPage(page, pagesize);
        return hapInterfaceHeaderMapper.getAllHeader(interfaceHeader);


    }

    @Override
    public List<HapInterfaceHeader> getHeaderAndLineList(IRequest requestContext, HapInterfaceHeader interfaceHeader) {
        List<HapInterfaceHeader> list = hapInterfaceHeaderMapper.getHeaderAndLineList(interfaceHeader);
        if (list.isEmpty() || list.size() < 0) {
            list = hapInterfaceHeaderMapper.getHeaderByHeaderId(interfaceHeader);
        }
        return list;

    }

    @Override
    public HapInterfaceHeader getHeaderAndLine(String sysName, String apiName) {
        logger.info("sysName apiName:{}", sysName + apiName);
        HapInterfaceHeader headerAndLineDTO = apiCache.getValue(sysName + HapInterfaceHeader.CACHE_SEPARATOR + apiName);
        if (headerAndLineDTO == null) {
            HapInterfaceHeader headerAndLineDTO1 = hapInterfaceHeaderMapper.getHeaderAndLineBySysNameAndApiName(sysName, apiName);
            if (headerAndLineDTO1 != null) {
                apiCache.setValue(sysName + HapInterfaceHeader.CACHE_SEPARATOR + apiName, headerAndLineDTO1);
            }
            return headerAndLineDTO1;
        } else {
            return headerAndLineDTO;

        }

    }

    /*
   * 获取所有的header和line数据——> HeaderAndHeaderTlDTO
   * */
    @Override
    public List<HapInterfaceHeader> getAllHeaderAndLine() {
        return hapInterfaceHeaderMapper.getAllHeaderAndLine();
    }


    /*
    * 获取所有的header和line数据——> HeaderAndHeaderTlDTO(分页)
    * */
    @Override
    public List<HapInterfaceHeader> getAllHeaderAndLine(int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return hapInterfaceHeaderMapper.getAllHeaderAndLine();
    }

    @Override
    public List<HapInterfaceHeader> getHeaderByHeaderId(IRequest requestContext, HapInterfaceHeader HapInterfaceHeader) {
        return hapInterfaceHeaderMapper.getHeaderByHeaderId(HapInterfaceHeader);
    }

    @Override
    public HapInterfaceHeader getHeaderAndLineByLineId(HapInterfaceHeader headerAndLineDTO) {
        return hapInterfaceHeaderMapper.getHeaderAndLineBylineId(headerAndLineDTO);
    }

    @Override
    public int updateHeader(IRequest request, HapInterfaceHeader hmsInterfaceHeader) {

        int result = hapInterfaceHeaderMapper.updateByPrimaryKeySelective(hmsInterfaceHeader);
        checkOvn(result, hmsInterfaceHeader);
        if (result > 0) {
            // 修改头，修改后重新加入缓存
            apiCache.reload();
        }
        return result;
    }

    @Override
    public void createInterface(IRequest iRequest, HapInterfaceHeader interfaceHeader) {
        interfaceHeader.setHeaderId(UUID.randomUUID().toString());
        interfaceHeader.setDescription(interfaceHeader.getName());
        HapInterfaceHeader hapInterfaceHeaderNew = self().insertSelective(iRequest, interfaceHeader);
        if (interfaceHeader.getLineList() != null) {
            processInterfaceLines(iRequest, interfaceHeader);
        }
    }

    @Override
    public void updateInterface(IRequest iRequest, HapInterfaceHeader interfaceHeader) {
        interfaceHeader.setDescription(interfaceHeader.getName());
        self().updateHeader(iRequest, interfaceHeader);

        if (interfaceHeader.getLineList() != null) {
            processInterfaceLines(iRequest, interfaceHeader);
        }
        if (AUTH_TYPE_OAUTH2.equalsIgnoreCase(interfaceHeader.getAuthType())) {
            authenticationService.removeToken(interfaceHeader);
        }
    }

    private void processInterfaceLines(IRequest iRequest, HapInterfaceHeader interfaceHeader) {
        for (HapInterfaceLine line : interfaceHeader.getLineList()) {
            line.setLineDescription(line.getLineName());
            if (line.getLineId() == null) {
                line.setHeaderId(interfaceHeader.getHeaderId());
                line.setLineId(UUID.randomUUID().toString());
                lineService.insertLine(iRequest, line);
            } else {
                lineService.updateLine(iRequest, line);
            }
        }
    }

    @Override
    public List<HapInterfaceHeader> batchUpdate(IRequest request, List<HapInterfaceHeader> interfaces) {
        for (HapInterfaceHeader interfaceHeader : interfaces) {
            if (interfaceHeader.getHeaderId() == null) {
                self().createInterface(request, interfaceHeader);
            } else {
                self().updateInterface(request, interfaceHeader);
            }
        }
        return interfaces;
    }

}
