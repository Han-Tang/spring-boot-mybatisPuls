package cn.xunhang.common.excel;

import cn.xunhang.system.entity.SysUser;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static org.junit.Assert.*;

public class ExcelUtilTest {

    @Test
    public void exportXls() throws IOException {
        List<SysUser> list = new ArrayList<>();
//        List<Map<String,Object>> list = new ArrayList<>();
        SysUser u = new SysUser();
        u.setUsername("11111");
        Map<String,Object> map2 =new LinkedHashMap<String, Object>();
        map2.put("username", "测试是否是中文长度不能自动宽度.测试是否是中文长度不能自动宽度.");
        map2.put("age", null);
        map2.put("sex", null);
        map2.put("birthday",null);

        Map<String,Object> map3 =new LinkedHashMap<String, Object>();
        map3.put("username", "张三");
        map3.put("age", 12);
        map3.put("sex", "男");
        map3.put("birthday",new Date());

        list.add(u);
//        list.add(map2);
//        list.add(map3);

        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("name","姓名");
        map1.put("age","年龄");
        map1.put("birthday","出生日期");
        map1.put("sex","性别");

        File f= new File("test.xls");
        OutputStream out = new FileOutputStream(f);
        ExcelUtil.getInstance().exportExcel(out,list, SysUser.class);
    }
}