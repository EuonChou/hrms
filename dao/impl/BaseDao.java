package com.hrms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hrms.dao.Dao;
import com.hrms.db.DBConnection;
import com.hrms.utils.ReflectionUtils;
import com.hrms.web.ConnectionContext;

public class BaseDao<T> implements Dao<T>{
	private QueryRunner queryRunner = new QueryRunner();	

	private Class<T> clazz; 

	public BaseDao(){
		clazz = ReflectionUtils.getSuperClassGenericType(this.getClass());
	}
	
	@Override
	public void insert(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			//得到连接
			conn = ConnectionContext.getInstance().get();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//设置参数
			if(args!=null){
				for(int i=0; i<args.length; i++){
					ps.setObject(i+1, args[i]);
				}
			}			
			//执行更新操作
			ps.executeUpdate();			
		}catch(Exception e){
			
		}finally{
			//ps不需要关闭么？
			DBConnection.release(conn);
		}		
	}

	@Override
	public void update(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try{
			conn = ConnectionContext.getInstance().get();
			queryRunner.update(conn, sql, args);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public T query(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		T t = null;
		
		try {
			conn = ConnectionContext.getInstance().get();
			//BeanHandler接受Result对象并转换为T类型？
			t =  queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
		} catch (Exception e) {
			// TODO: handle exception
			DBConnection.release(conn);
		}
		
		return t;
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			conn = ConnectionContext.getInstance().get();
			return queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	
	@Override
	public <V> V getSingleVal(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = ConnectionContext.getInstance().get();
			return (V)queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void batch(String sql, Object[] ... args) {
		// TODO Auto-generated method stub
		Connection conn;
		try{
			conn = ConnectionContext.getInstance().get();
			queryRunner.batch(conn, sql, args);		
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

}
