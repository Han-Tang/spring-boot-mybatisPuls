package cn.xunhang.common.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface BaseService<T extends BaseDO<T>, M extends BaseMapper<T>> extends IService<T> {
    /**
     * 新增
     * @param t
     */
    void insertInfo(T t);

    /**
     * 编辑
     * @param t
     */
    void updateInfo(T t);

    /**
     * 批量删除
     * @param ids
     */
    void deleteInfo(String[] ids);

    /**
     *
     */
    void deleteByT(T t);

    /**
     * 不带分页查询列表
     * @param entityWrapper
     * @return
     */
    List<T> queryList(EntityWrapper<T> entityWrapper);

    /**
     * 带分页查询
     * @param page
     * @param entityWrapper
     * @return
     */
    Page<T> queryPage(Page<T> page, EntityWrapper<T> entityWrapper);

    /**
     * 查询单条记录
     * @param params
     * @return
     */
    T queryDetail(Map<String, Object> params);

}
