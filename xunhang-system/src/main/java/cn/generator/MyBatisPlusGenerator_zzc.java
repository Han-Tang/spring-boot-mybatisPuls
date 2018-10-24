package cn.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus代码生成器
 *
 * @author theodo
 * @Date 2017/10/21 12:38
 */
public class MyBatisPlusGenerator_zzc {

    public static void main(String[] args) {

        String module_pre = "modules";
        String moduleName = "my";
        String[] biao = {"test"};


        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F:\\自动生成代码(自定义)");//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("zzc");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sDao");
        gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.SQL_SERVER);
        dsc.setTypeConvert(new SqlServerTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc.setUsername("sa");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:sqlserver://192.168.1.98:1433;DatabaseName=taixing");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[]{"pf_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(biao); // 需要生成的表

        strategy.setSuperControllerClass("cn.xunhang.common.base.BaseController");
        // 生成 RestController 风格
        strategy.setRestControllerStyle(true);

        mpg.setStrategy(strategy);

        // 包配置
        // 注意不同的模块生成时要修改对应模块包名
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity("cn.xunhang."+module_pre+"."+moduleName+".entity");
        pc.setMapper("cn.xunhang."+module_pre+"."+moduleName+".dao");
        pc.setXml("mapper."+module_pre+"."+moduleName);
        pc.setService("cn.xunhang."+module_pre+"."+moduleName+".service");
        pc.setServiceImpl("cn.xunhang."+module_pre+"."+moduleName+".service.impl");
        pc.setController("cn.xunhang."+module_pre+"."+moduleName+".controller");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates_zzc/controller.java.vm");
        tc.setEntity("/templates_zzc/entity.java.vm");
        tc.setMapper("/templates_zzc/mapper.java.vm");
        tc.setXml("/templates_zzc/mapper.xml.vm");
        tc.setService("/templates_zzc/service.java.vm");
        tc.setServiceImpl("/templates_zzc/serviceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));

///////////////////////////////////////
///////////////////////////////////////

        AutoGenerator mpg222 = new AutoGenerator();

        // 全局配置
        GlobalConfig gc222 = new GlobalConfig();
        gc222.setOutputDir("F:\\自动生成代码(自定义)");//这里写你自己的java目录
        gc222.setFileOverride(true);//是否覆盖
        gc222.setActiveRecord(false);
        gc222.setEnableCache(false);// XML 二级缓存
        gc222.setBaseResultMap(true);// XML ResultMap
        gc222.setBaseColumnList(false);// XML columList
        gc222.setAuthor("zzc");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc222.setMapperName("SuperMapper");
        gc222.setServiceName("InfoBaseService");
        gc222.setServiceImplName("InfoBaseServiceImpl");
        gc222.setControllerName("InfoBaseController");
        gc222.setXmlName("没用");
        mpg222.setGlobalConfig(gc222);

        // 数据源配置
        DataSourceConfig dsc222 = new DataSourceConfig();
        dsc222.setDbType(DbType.SQL_SERVER);
        dsc222.setTypeConvert(new SqlServerTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                return super.processTypeConvert(fieldType);
            }
        });
        dsc222.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc222.setUsername("sa");
        dsc222.setPassword("123456");
        dsc222.setUrl("jdbc:sqlserver://192.168.1.98:1433;DatabaseName=taixing");
        mpg222.setDataSource(dsc222);

        // 策略配置
        StrategyConfig strategy222 = new StrategyConfig();
        //strategy.setTablePrefix(new String[]{"pf_"});// 此处可以修改为您的表前缀
        strategy222.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy222.setInclude(biao); // 需要生成的表

        strategy222.setSuperControllerClass("cn.xunhang.common.base.BaseController");
        // 生成 RestController 风格
        strategy222.setRestControllerStyle(true);

        mpg222.setStrategy(strategy222);



        // 包配置
        // 注意不同的模块生成时要修改对应模块包名
        PackageConfig pc222 = new PackageConfig();
        pc222.setParent(null);
        pc222.setEntity("cn.xunhang."+module_pre+"."+moduleName+".entity.infoBase");//
        pc222.setMapper("cn.xunhang."+module_pre+"."+moduleName+".baseMapper");//
        pc222.setService("cn.xunhang."+module_pre+"."+moduleName+".service.infoBase");//
        pc222.setServiceImpl("cn.xunhang."+module_pre+"."+moduleName+".service.infoBase.impl");//
        pc222.setController("cn.xunhang."+module_pre+"."+moduleName+".controller.infoBase");//
        pc222.setXml("没用");
        mpg222.setPackageInfo(pc222);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg222 = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg222.setCfg(cfg222);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc222 = new TemplateConfig();
        tc222.setController("/templates_zzc/controller_infobase.java.vm");
        tc222.setEntity("/templates_zzc/entity_infobase.java.vm");
        tc222.setMapper("/templates_zzc/mapper_infobase.java.vm");
        tc222.setService("/templates_zzc/service_infobase.java.vm");
        tc222.setServiceImpl("/templates_zzc/serviceImpl_infobase.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg222.setTemplate(tc222);

        // 执行生成
        mpg222.execute();

        // 打印注入设置
        System.err.println(mpg222.getCfg().getMap().get("abc"));

    }

}