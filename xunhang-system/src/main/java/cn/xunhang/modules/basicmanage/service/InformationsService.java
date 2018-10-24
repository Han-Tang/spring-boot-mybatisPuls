package cn.xunhang.modules.basicmanage.service;

import cn.xunhang.modules.basicmanage.dao.InformationsDao;
import cn.xunhang.modules.basicmanage.entity.Informations;
import cn.xunhang.modules.basicmanage.service.infoBase.InfoBaseService;
import org.springframework.stereotype.Service;


@Service
public interface InformationsService extends InfoBaseService<Informations,InformationsDao> {


}
