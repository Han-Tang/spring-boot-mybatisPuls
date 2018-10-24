package cn.xunhang.common.interceptor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;


/**
 *  * 拦截器作用：给各实体对象在增加、修改时，自动添加操作属性信息。
 *  
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class })})
public class OpeInfoInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable
    {

        Object[] args = invocation.getArgs();

        System.out.println("-----------参数拦截---------------------------------------------------");
        System.out.println("02 当前线程ID:" + Thread.currentThread().getId());
        //遍历处理所有参数，update方法有两个参数，参见Executor类中的update()方法。
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            String className = arg.getClass().getName();
            System.out.println(i + " 参数类型：" + className);

        //第一个参数处理。根据它判断是否给“操作属性”赋值。
            //如果是第一个参数 MappedStatement
            if (arg instanceof MappedStatement) {
                MappedStatement ms = (MappedStatement) arg;
                SqlCommandType sqlCommandType = ms.getSqlCommandType();
                System.out.println("操作类型：" + sqlCommandType);
                //如果是“增加”或“更新”操作，则继续进行默认操作信息赋值。否则，则退出
                if (sqlCommandType == SqlCommandType.INSERT || sqlCommandType == SqlCommandType.UPDATE) {
                    continue;
                } else {
                    break;
                }
            }

            //第二个参数处理。（只有第二个程序才能跑到这）
            //如果是map，有两种情况：（1）使用@Param多参数传入，由Mybatis包装成map。（2）原始传入Map
            if (arg instanceof Map) {
                System.out.println("这是一个包装过的类型!");
                Map map = (Map) arg;
                for (Object obj : map.values()) {
                    setProperty(obj);
                }
            } else {//原始参数传入
                setProperty(arg);
            }

        }

        return invocation.proceed();

    }

    /**
     *    * 为对象的操作属性赋值
     *    * @param obj
     *    
     */
    private void setProperty(Object obj)
    {
        try {
//TODO: 根据需要，将相关属性赋上默认值
            BeanUtils.setProperty(obj, "spec", "张三");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties)
    {
        System.out.println("123");
    }

    private String getSqlByInvocation(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return boundSql.getSql();
    }

}