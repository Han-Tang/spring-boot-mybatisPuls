package cn.xunhang.modules.basicmanage.service.impl;

import cn.xunhang.common.base.Tree;
import cn.xunhang.common.utils.BuildTree;
import cn.xunhang.common.utils.XmlUtil;
import cn.xunhang.modules.basicmanage.dao.*;
import cn.xunhang.modules.basicmanage.entity.*;
import cn.xunhang.modules.basicmanage.service.InfoProductBomService;
import cn.xunhang.modules.basicmanage.service.infoBase.impl.InfoBaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tyj 
 * @date 2018-10-22 15:05
 */
@Service
public class InfoProductBomServiceImpl extends InfoBaseServiceImpl<InfoProductBom,InfoProductBomDao> implements InfoProductBomService {


    /**
     * 导入bom
     * @param file
     */
    @Override
    @Transactional
    public void importBom(File file,String orderNum) {
        Map xmlMap = XmlUtil.parseXML(file);
        Object obj = ((Map)((Map)((Map)xmlMap.get("ERPProject")).get("ProductCollection")).get("ProductList")).get("lev0");
        List<Map> list = new ArrayList<Map>();
        if(obj instanceof List){
            list = (List)obj;
        }else if(obj instanceof Map){
            list.add((Map)obj);
        }
        String parentId = "0";
        for (Map map : list){
            List<InfoProductBom> boms = new ArrayList<>();
            int i = 1;
            xmlToBoms(map,boms,i,parentId,orderNum);
            for (InfoProductBom bom : boms){
                insert(bom);
            }
//            insertBatch(boms);
        }
    }

    @Override
    public List<Tree<InfoProductBom>> queryList(Map<String, Object> params) {
        EntityWrapper<InfoProductBom> entityWrapper = new EntityWrapper<InfoProductBom>();
        entityWrapper.eq("orderNum",params.get("orderNum"));
        entityWrapper.orderBy("createDate",false);
        List<InfoProductBom> boms = queryList(entityWrapper);
        List<Tree<InfoProductBom>> trees = getAllTree(boms);
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<InfoProductBom>> list = BuildTree.buildList(trees, "0");
        return list;
    }


    /**
     * 获取所有列表树
     */
    private List<Tree<InfoProductBom>> getAllTree(List<InfoProductBom> list){
        List<Tree<InfoProductBom>> trees = new ArrayList<Tree<InfoProductBom>>();
        for (InfoProductBom bom : list) {
            Tree<InfoProductBom> tree = new Tree<InfoProductBom>();
            tree.setId(bom.getId());
            tree.setParentId(bom.getParentId());
            tree.setText(bom.getDes());

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("des", bom.getDes());
            attributes.put("ref", bom.getLev());
            attributes.put("partOL", bom.getPartOL());
            attributes.put("partOW", bom.getPartOW());
            attributes.put("partOT", bom.getPartOT());
            attributes.put("partL", bom.getPartL());
            attributes.put("partW", bom.getPartW());
            attributes.put("partT", bom.getPartT());

            tree.setAttributes(attributes);

            trees.add(tree);
        }
        return trees;
    }

    /**
     * map转bom
     */
    private void xmlToBoms(Map map, List<InfoProductBom> list, int i,String parentId,String orderNum){

        InfoProductBom bom = new InfoProductBom();
        getXmlValue(map,bom);
        bom.getId();
        bom.setOrderNum(orderNum);
        bom.setParentId(parentId);

        list.add(bom);
        String str = "lev"+i;
        Object obj = map.get(str);
        if(obj != null){
            ++i;
            parentId = bom.getId();
            if(obj instanceof List){
                List<Map> lis  = (List)obj;
                for (Map mp : lis){
                    xmlToBoms(mp,list,i,parentId,orderNum);
                }
            }else if(obj instanceof Map){
                xmlToBoms((Map)obj,list,i,parentId,orderNum);
            }
        }
    }

    private void getXmlValue(Map map,InfoProductBom bom){
        try {
            Field[] fields = InfoProductBom.class.getDeclaredFields();
            for (Field field : fields){
                String s = field.getName();
                s = (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
                BeanUtils.copyProperty(bom,field.getName(),map.get(s));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
