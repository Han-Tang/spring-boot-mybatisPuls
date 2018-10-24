package cn.xunhang.modules.basicmanage.service.impl;

import cn.xunhang.modules.basicmanage.dao.InformationsDao;
import cn.xunhang.modules.basicmanage.entity.Informations;
import cn.xunhang.modules.basicmanage.service.InformationsService;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class InformationsServiceImpl  extends InfoBaseServiceImpl<Informations,InformationsDao> implements InformationsService {

}
