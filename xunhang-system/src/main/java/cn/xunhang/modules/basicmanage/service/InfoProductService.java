package cn.xunhang.modules.basicmanage.service;


import cn.xunhang.modules.basicmanage.dao.InfoProductDao;
import cn.xunhang.modules.basicmanage.entity.InfoProduct;
import cn.xunhang.modules.basicmanage.service.infoBase.InfoBaseService;
import cn.xunhang.modules.basicmanage.vo.InfoProductVo;
import com.baomidou.mybatisplus.plugins.Page;


import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzc
 * @since 2018-07-30
 */
public interface InfoProductService extends InfoBaseService<InfoProduct,InfoProductDao> {

//    String insertInfo(InfoProduct infoProduct);

//    void updateInfo(Map<String, Object> params);

    Page<InfoProductVo> queryList(Map<String, Object> params);

    Map<String,Object> queryDetails(Map<String, Object> params);

    void deleteInfo(List<String> ids);

}
