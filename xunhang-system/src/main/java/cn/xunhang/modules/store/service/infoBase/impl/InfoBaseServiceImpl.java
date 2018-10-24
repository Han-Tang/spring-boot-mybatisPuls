package cn.xunhang.modules.store.service.infoBase.impl;


import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.base.SuperEntity;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.modules.store.baseMapper.SuperMapper;
import cn.xunhang.modules.store.service.infoBase.InfoBaseService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzc
 * @since 2018-09-03
 */

public class InfoBaseServiceImpl<T1 extends SuperEntity<T1>,  T2 extends SuperMapper<T1>> extends ServiceImpl<T2, T1> implements InfoBaseService<T1, T2> {


    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public String insertInfo(T1 t1) {

        if (t1 == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
        }
        t1.setCreateDate(new Date());
        Integer result = baseMapper.insert(t1);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }
        return t1.getId();
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void updateInfo(T1 t1) {

        if (t1 == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
        }
        t1.setUpdateDate(new Date());
        Integer result = baseMapper.updateById(t1);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
        }
    }

    @Override
    public Page<T1> queryList(Page<T1> page,EntityWrapper<T1> entityWrapper) {

        List<T1> list = baseMapper.selectPage(page,entityWrapper);
        if(list == null || list.size() == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据不存在");
        }
        page.setRecords(list);
        return page;
    }


    @Override
    public T1 queryDetail(Map<String, Object> params) {

        String id = (String) params.get("id");
        T1 entity = baseMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据不存在");
        }
        if (entity.isDeleted()) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据已被删除");
        }
        return entity;
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void deleteInfo(Map<String, Object> params) {
        List<String> ids = (List<String>)(params.get("id"));
        for (String id : ids){

            T1 entity = baseMapper.selectById(id);
            if (entity == null) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据不存在");
            }
            if (entity.isDeleted()) {
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据已被删除");
            }
            entity.setDeleted(true);
            Integer result = baseMapper.updateById(entity);
            if(result == 0){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"操作不成功，请重新刷新页面");
            }
        }


    }


    public Class<T1> getTClass() {
        Class<T1> tClass = (Class<T1>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }


}
