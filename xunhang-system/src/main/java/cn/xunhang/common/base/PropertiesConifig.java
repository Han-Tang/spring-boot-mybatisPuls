package cn.xunhang.common.base;


import cn.xunhang.common.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

/**
 * Created by dly on 2016/8/10.
 */
public class PropertiesConifig {
    private static Properties properties;
    static {
        System.out.println("配置文件读取");
        try{
            properties=new Properties();
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("common.properties");
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
            PropertyConfigurator.configure(properties);
        }catch (Exception e){
            System.out.println("配置文件读取错误");
        }
    }

    public static String getFileRoot() {
        return properties.getProperty("fileRoot");
    }


//    public static Date getDatingGetFilmsStartDate(){
//        String datingGetFilmsStartDate = properties.getProperty("datingGetFilmsStartDate");
//        if (StringUtils.isNotBlank(datingGetFilmsStartDate)){
//            return DateUtil.parseDate(datingGetFilmsStartDate,"yyyy-MM-dd");
//        }
//        return null;
//    }


}
