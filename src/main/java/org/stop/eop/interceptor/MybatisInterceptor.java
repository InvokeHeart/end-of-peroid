package org.stop.eop.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.stop.eop.util.ThreadContextHolder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class MybatisInterceptor implements Interceptor {
    private static final Log log = LogFactory.getLog(MybatisInterceptor.class);

    //3
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Integer build = ThreadContextHolder.get();
        log.debug("build: " + build);
        if (invocation.getMethod().getName().equals("")){

        }
        if (Objects.nonNull(build)) {
            // MetaObject.forObject()
            Object target = invocation.getTarget();
            StatementHandler statementHandler = (StatementHandler) target;
            BoundSql boundSql = statementHandler.getBoundSql();
            Field sql = boundSql.getClass().getDeclaredField("sql");
            sql.setAccessible(true);
            String oldSql = ((String) sql.get(boundSql));
            log.debug("old sql: " + oldSql);
            String newSql = oldSql + " where build = " + build;
            sql.set(boundSql, newSql);
            log.debug("new sql: " + sql.get(boundSql));
        }
        return invocation.proceed();
    }

    //2
    /*
    @Override
    public Object plugin(Object target) {
        System.out.println("target.." + target);
        return Interceptor.super.plugin(target);
    }
    */


    //1
    @Override
    public void setProperties(Properties properties) {
        // Interceptor.super.setProperties(properties);
        System.out.println(properties);
    }
}
