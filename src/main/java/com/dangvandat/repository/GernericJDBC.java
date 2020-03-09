package com.dangvandat.repository;

import com.dangvandat.paging.Pageble;

import java.util.List;
import java.util.Map;

public interface GernericJDBC<T> {
	/*List<T> query(String sql , Object... parameters);
	
	void update(String sql, Object...parameters);

	Long insert(String sql, Object...parameters);*/


	Long insert(Object object);

    void update(Object object);

    void delete(long id);

    void deleteByProperty(String where);

    <T> T findById(long id);

    List<T> findAll(Map<String , Object> properties , Pageble pageble, Object... where);

    int countByProperty(Map<String , Object> properties , Object... where);
}
