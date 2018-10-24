package cn.xunhang.modules.store.baseMapper;


import cn.xunhang.common.base.SuperEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 演示 mapper 父类，注意这个类不要让 mp 扫描到！！
 */
public interface SuperMapper<T1 extends SuperEntity<T1>> extends BaseMapper<T1> {

    // 这里可以放一些公共的方法
}
