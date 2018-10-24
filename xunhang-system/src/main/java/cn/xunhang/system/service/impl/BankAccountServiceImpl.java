package cn.xunhang.system.service.impl;

import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.system.entity.BankAccount;
import cn.xunhang.system.dao.BankAccountDao;
import cn.xunhang.system.service.BankAccountService;
import cn.xunhang.system.service.infoBase.impl.InfoBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 针对具体编号生成的树结构 服务实现类
 * </p>
 *
 * @author tyj
 * @since 2018-09-13
 */
@Service
public class BankAccountServiceImpl extends InfoBaseServiceImpl<BankAccount,BankAccountDao> implements BankAccountService {


}
