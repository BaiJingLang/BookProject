package com.bai.book.dao.impl;

import com.bai.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public abstract class BaseDao {
    //使用dbutils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行，Insert\Update\Delete语句
     * 如果返回-1，说明执行失败
     */
    public int update(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个javabean的sql语句
     * @param type 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>  返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 查询返回多个javabean的sql语句
     * @param type 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>  返回类型的泛型
     * @return
     */
    public <T>List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }


    /**
     * 返回一行一列的sql语句
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object ... args){
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
}
