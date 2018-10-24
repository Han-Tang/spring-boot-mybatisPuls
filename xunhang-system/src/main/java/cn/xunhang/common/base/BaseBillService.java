package cn.xunhang.common.base;

import cn.xunhang.vo.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

public interface BaseBillService<T1 extends BaseDO<T1>, M1 extends BaseMapper<T1>,T2 extends BaseDO<T2>, M2 extends BaseMapper<T2>> {
    /**
     * 新增
     * @param params
     */
    void insertInfo(Param<T1,T2> params);

    /**
     * 编辑
     * @param params
     */
    void updateInfo(Param<T1,T2> params);

    /**
     * 批量删除
     * @param params
     */
    void deleteInfo(Param<T1,T2> params);

    /**
     * 不带分页查询列表
     * @param entityWrapper
     * @return
     */
    List<T1> queryList(EntityWrapper<T1> entityWrapper);

    /**
     * 带分页查询
     * @param page
     * @param entityWrapper
     * @return
     */
    Page<T1> queryPage(Page<T1> page, EntityWrapper<T1> entityWrapper);

    /**
     * 查询单条记录
     * @param params
     * @return
     */
    T1 queryDetail(Map<String, Object> params);

    /**
     * 不带分页查询列表
     * @param entityWrapper
     * @return
     */
    List<T2> queryDtlList(EntityWrapper<T2> entityWrapper);

    /**
     * 带分页查询
     * @param page
     * @param entityWrapper
     * @return
     */
    Page<T2> queryDtlPage(Page<T2> page, EntityWrapper<T2> entityWrapper);

    /**
     * 查询单条记录
     * @param params
     * @return
     */
    T2 queryDtlDetail(Map<String, Object> params);

    /**
     * 查询主表条件单条数据
     * @param wrapper
     * @return
     */
    T1 selectByParams(Wrapper<T1> wrapper);

    /**
     * 查询主表条件列表
     * @param wrapper
     * @return
     */
    List<T1> selectAllByParams(Wrapper<T1> wrapper);

    /**
     * 查询从表条件单条数据
     * @param wrapper
     * @return
     */
    T2 selectDtlByParams(Wrapper<T2> wrapper);

    /**
     * 查询从表条件所有数据
     * @param wrapper
     * @return
     */
    List<T2> selectAllDtlByParams(Wrapper<T2> wrapper);

    /**
     * 删除从表条件数据
     * @param wrapper
     * @return
     */
    Integer deleteDtl(Wrapper<T2> wrapper);

}
