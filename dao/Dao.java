package com.hrms.dao;

import java.util.List;



public interface Dao<T> {
	//增加
	void insert(String sql, Object ... args);
	
	//修改
	void update(String sql, Object ... args);
	
	//单条查询
	T query(String sql, Object ... args);
	
	//多条查询
	List<T> queryForList(String sql, Object ... args);
	
	//执行一个属性或值的查询操作, 例如查询某一条记录的一个字段, 或查询某个统计信息, 返回要查询的值
	<V> V getSingleVal(String sql, Object ... args);
	
	//批量操作
	void batch(String sql, Object[] ... args);	
}
