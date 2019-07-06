package yj.core.appidconf.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.appidconf.dto.Appidconf;
import yj.core.appidconf.mapper.AppidconfMapper;
import yj.core.appidconf.service.IAppidconfService;

import java.util.List;

@Service
@Transactional
public class AppidconfServiceImpl extends BaseServiceImpl<Appidconf> implements IAppidconfService{
    @Autowired
    private  AppidconfMapper appidconfMapper;


    @Override
    public String updateOrInsert(List<Appidconf> list) {
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Appidconf appidconf = list.get(i);
                int num = appidconfMapper.isExit(appidconf.getAppid());
                if (num == 1){
                    appidconfMapper.update(appidconf);
                }else{
                    appidconfMapper.insert(appidconf);
                }
            }
        }
        return null;
    }

    @Override
    public Appidconf selectByAppid(String appid) {
        return appidconfMapper.selectByAppid(appid);
    }
}