package cn.xunhang.modules.basicmanage.dao;

import cn.xunhang.modules.basicmanage.baseMapper.SuperMapper;
import cn.xunhang.modules.basicmanage.entity.Informations;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface InformationsDao extends SuperMapper<Informations> {

    Informations get(String id);

    Page<Informations> queryList(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Informations record);

    int updateInfo(Informations record);

    int remove(String id);

    void batchRemove(String[] ids);

}
