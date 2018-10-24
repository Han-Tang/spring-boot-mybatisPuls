package cn.xunhang.modules.basicmanage.service.impl;

import cn.xunhang.modules.basicmanage.entity.Customer;
import cn.xunhang.modules.basicmanage.dao.CustomerDao;
import cn.xunhang.modules.basicmanage.service.CustomerService;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyj
 * @since 2018-08-13
 */
@Service
public class CustomerServiceImpl extends InfoBaseServiceImpl<Customer,CustomerDao> implements CustomerService {


}
