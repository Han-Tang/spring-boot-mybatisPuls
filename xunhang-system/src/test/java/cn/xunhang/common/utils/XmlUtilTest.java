package cn.xunhang.common.utils;

import cn.xunhang.common.base.Tree;
import cn.xunhang.modules.basicmanage.entity.InfoProductBom;
import cn.xunhang.modules.basicmanage.service.InfoProductBomService;
import cn.xunhang.modules.basicmanage.service.impl.InfoProductBomServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlUtilTest {

    @Autowired
    private InfoProductBomService infoProductBomService;

    /**
     * 解析xml
     */
    @Test
    public void analysis() {
        String xmlContent = "<note> " +
                "<to>George</to> " +
                "<from>John</from> " +
                "<heading>Reminder</heading> " +
                "<body>Don't forget the meeting!</body>" +
                "</note>";

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<bookstore>"+
                "	<book id = \"1\">"+
                "		<name>Java程序教程</name>"+
                "		<author>Tony</author>"+
                "		<year>2018</year>"+
                "		<price>90</price>"+
                "	</book>"+
                "	<book >"+
                "		<name>安徒生童话故事</name>"+
                "		<year>2000</year>"+
                "		<language>English</language>"+
                "		<price/>"+
                "		<version></version>"+
                "	</book>"+
                "</bookstore>";

        try {
            File file = new File("src/test/resources/xmlTest.xml");
            Map xmlMap = XmlUtil.parseXML(file);
            Object obj = ((Map)((Map)((Map)xmlMap.get("ERPProject")).get("ProductCollection")).get("ProductList")).get("lev0");
            List<Map> list = new ArrayList<Map>();
            if(obj instanceof List){
                list = (List)obj;
            }else if(obj instanceof Map){
                list.add((Map)obj);
            }

            for (Map map : list){
                List<InfoProductBom> boms = new ArrayList<>();
                int i = 1;
                xmlToBoms(map,boms,i);
                System.out.println("bom大小>>>>>>>"+boms.size());
            }

            System.out.println("1111111111");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 生成xml
     */
    @Test
    public void generate() {

        System.out.println("1111111111");
    }

    /**
     * 查询
     */
    @Test
    public void queryList() {
        Map map = new HashMap();
        InfoProductBomServiceImpl bo = new InfoProductBomServiceImpl();
        List<Tree<InfoProductBom>> menuList = bo.queryList(map);
        System.out.println("1111111111");
    }



    /**
     * map转bom
     */
    private void xmlToBoms(Map map, List<InfoProductBom> list, int i){
        String str = "lev"+i;
        Object obj = map.get(str);

        InfoProductBom bom = new InfoProductBom();
        bom.setLev(map.get("Lev").toString());
        bom.setRef(map.get("Ref").toString());
        bom.setDes(map.get("Des").toString());

        list.add(bom);

        if(obj != null){
            ++i;
            if(obj instanceof List){
                List<Map> lis  = (List)obj;
                for (Map mp : lis){
                    xmlToBoms(mp,list,i);
                }

            }else if(obj instanceof Map){
                xmlToBoms((Map)obj,list,i);
            }
        }
    }

}
