package cn.xunhang.common.base.impl;

import cn.xunhang.common.base.BaseDO;
import cn.xunhang.common.base.BaseService;
import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.exception.RRException;
import cn.xunhang.common.utils.ShiroUtils;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T extends BaseDO<T>,M extends BaseMapper<T>> extends ServiceImpl<M,T> implements BaseService<T,M> {


    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void insertInfo(T t) {
        try{
            if (t == null){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
            }
            Integer result = baseMapper.insert(t);
            if(result == 0){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增不成功，请重新刷新页面");
            }
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            throw new RRException("数据重复，请重新操作。",100);
        }
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void updateInfo(T t) {

        if (t == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
        }
        t.setUpdateBy(ShiroUtils.getUserId());
        t.setUpdateName(ShiroUtils.getUser().getName());
        t.setUpdateDate(new Date());
        Integer result = baseMapper.updateById(t);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"更新不成功，请重新刷新页面");
        }
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void deleteInfo(String[] ids) {

        for (String id : ids){
            T entity = baseMapper.selectById(id);

            deleteByT(entity);
        }
    }

    @Override
    public void deleteByT(T t) {

        if (t == null) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据不存在");
        }
        if (t.getDeleted()) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据已被删除");
        }
        t.setDeleted(true);
        Integer result = baseMapper.updateById(t);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"删除不成功，请重新刷新页面");
        }

    }

    @Override
    public List<T> queryList(EntityWrapper<T> entityWrapper) {

        List<T> list = baseMapper.selectList(entityWrapper);
        return list;
    }

    @Override
    public Page<T> queryPage(Page<T> page, EntityWrapper<T> entityWrapper) {

        List<T> list = baseMapper.selectPage(page,entityWrapper);
        page.setRecords(list);
        return page;
    }

    @Override
    public T queryDetail(Map<String, Object> params) {

        String id = (String) params.get("id");
        T entity = baseMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据不存在");
        }
        if (entity.getDeleted()) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据已被删除");
        }
        return entity;
    }

    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

}
