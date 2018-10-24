package cn.xunhang.system.service;

import cn.xunhang.system.dao.BankAccountDao;
import cn.xunhang.system.entity.BankAccount;
import cn.xunhang.system.service.infoBase.InfoBaseService;

/**
 * <p>
 * 针对具体编号生成的树结构 服务类
 * </p>
 *
 * @author tyj
 * @since 2018-09-13
 */
public interface BankAccountService extends InfoBaseService<BankAccount,BankAccountDao> {


}
