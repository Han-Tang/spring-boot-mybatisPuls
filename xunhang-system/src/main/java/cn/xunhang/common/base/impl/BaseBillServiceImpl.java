package cn.xunhang.common.base.impl;

import cn.xunhang.common.base.BaseBillService;
import cn.xunhang.common.base.BaseDO;
import cn.xunhang.common.base.BusinessException;
import cn.xunhang.common.enums.CommonErrorResult;
import cn.xunhang.common.utils.ShiroUtils;
import cn.xunhang.vo.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseBillServiceImpl<T1 extends BaseDO<T1>,M1 extends BaseMapper<T1>,T2 extends BaseDO<T2>,M2 extends BaseMapper<T2>> implements BaseBillService<T1,M1,T2,M2> {
    @Autowired
    private M1 baseHdrMapper;
    @Autowired
    private M2 baseDtlMapper;


    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void insertInfo(Param<T1, T2> params) {
        if (params.order == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
        }
        Integer result = baseHdrMapper.insert(params.order);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增主表不成功，请重新刷新页面");
        }
        for (T2 t2 : params.list){
            int i = baseDtlMapper.insert(t2);
            if(i == 0){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增从表不成功，请重新刷新页面");
            }
        }
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void updateInfo(Param<T1, T2> params) {
        if (params.order == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
        }
        Integer result = baseHdrMapper.updateById(params.order);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"编辑主表不成功，请重新刷新页面");
        }
        for (T2 t2 : params.list){
            int i = baseDtlMapper.insert(t2);
            if(i == 0){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"编辑从表不成功，请重新刷新页面");
            }
        }
    }

    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void deleteInfo(Param<T1, T2> params) {
        if (params.order == null){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"数据出错");
        }
        params.order.setDeleted(true);
        Integer result = baseHdrMapper.updateById(params.order);
        if(result == 0){
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增不成功，请重新刷新页面");
        }
        for (T2 t2 : params.list){
            t2.setDeleted(true);
            int i = baseDtlMapper.updateById(t2);
            if(i == 0){
                throw new BusinessException(CommonErrorResult.BUSINESS_ERROR,"新增不成功，请重新刷新页面");
            }
        }
    }

    @Override
    public List<T1> queryList(EntityWrapper<T1> entityWrapper) {
        return baseHdrMapper.selectList(entityWrapper);
    }

    @Override
    public Page<T1> queryPage(Page<T1> page, EntityWrapper<T1> entityWrapper) {
        List<T1> list = baseHdrMapper.selectPage(page,entityWrapper);
        page.setRecords(list);
        return page;
    }

    @Override
    public T1 queryDetail(Map<String, Object> params) {
        String id = (String) params.get("id");
        T1 entity = baseHdrMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据不存在");
        }
        if (entity.getDeleted()) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据已被删除");
        }
        return entity;
    }

    @Override
    public List<T2> queryDtlList(EntityWrapper<T2> entityWrapper) {
        List<T2> list = baseDtlMapper.selectList(entityWrapper);
        return list;
    }

    @Override
    public Page<T2> queryDtlPage(Page<T2> page, EntityWrapper<T2> entityWrapper) {
        List<T2> list = baseDtlMapper.selectPage(page,entityWrapper);
        page.setRecords(list);
        return page;
    }

    @Override
    public T2 queryDtlDetail(Map<String, Object> params) {
        String id = (String) params.get("id");
        T2 entity = baseDtlMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据不存在");
        }
        if (entity.getDeleted()) {
            throw new BusinessException(CommonErrorResult.BUSINESS_ERROR, "数据已被删除");
        }
        return entity;
    }


    @Override
    public T1 selectByParams(Wrapper<T1> wrapper) {
        return SqlHelper.getObject(this.baseHdrMapper.selectList(wrapper));
    }

    @Override
    public List<T1> selectAllByParams(Wrapper<T1> wrapper) {
        return this.baseHdrMapper.selectList(wrapper);
    }

    @Override
    public T2 selectDtlByParams(Wrapper<T2> wrapper) {
        return SqlHelper.getObject(this.baseDtlMapper.selectList(wrapper));
    }

    @Override
    public List<T2> selectAllDtlByParams(Wrapper<T2> wrapper) {
        return this.baseDtlMapper.selectList(wrapper);
    }

    @Override
    public Integer deleteDtl(Wrapper<T2> wrapper) {
        return this.baseDtlMapper.delete(wrapper);
    }

}
