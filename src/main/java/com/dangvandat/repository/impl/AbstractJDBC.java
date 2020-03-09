package com.dangvandat.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.dangvandat.annotation.Column;
import com.dangvandat.annotation.Table;
import com.dangvandat.mapper.ResultSetMapper;
import com.dangvandat.paging.Pageble;
import com.dangvandat.paging.Sorter;
import com.dangvandat.repository.GernericJDBC;
import org.apache.commons.lang.StringUtils;

public class AbstractJDBC<T> implements GernericJDBC<T> {
	
	private Class<T> zClass;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterType = (ParameterizedType) type; 
		zClass = (Class<T>) parameterType.getActualTypeArguments()[0];
	}
	
	private Connection getConnection() {
		try {
		    String classForname = "com.mysql.cj.jdbc.Driver";
			Class.forName(classForname);
			String URL = resourceBundle.getString("url");
			String username = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(URL , username , password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	/*@Override
	public List<T> query(String sql, Object... parameters) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		Connection conn = getConnection();
		ResultSet resultSet = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			if(conn != null) {				
				for(int i = 0 ; i <parameters.length;i++) {
					statement.setObject((i+1), parameters[i]);
				}
				return resultSetMapper.mapRow(resultSet, this.zClass);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			
		}finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());	
                }
            }
        }
		return null;
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql);
			Class.forName("com.mysql.jdbc.Driver");
			if(conn != null) {
					
				//set parameter
				
					for(int i = 0 ; i <parameters.length;i++) {
						statement.setObject((i+1), parameters[i]);
					}				
					statement.executeUpdate();
					conn.commit();
				}
			}
		catch(ClassNotFoundException | SQLException ex) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(statement != null) {
					statement.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			Class.forName("com.mysql.jdbc.Driver");
			if(conn != null) {

				//set parameter

				for(int i = 0 ; i <parameters.length;i++) {
					statement.setObject((i+1), parameters[i]);
				}


				int row = statement.executeUpdate();
				rs = statement.getGeneratedKeys();
				conn.commit();
				if(row > 0) {
					while(rs.next()) {
						Long id = rs.getLong(1);
						return id;
					}
				}
			}
		}
		catch(ClassNotFoundException | SQLException ex) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(statement != null) {
					statement.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}*/

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createInsert();
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			String classForname = "com.mysql.cj.jdbc.Driver";
			Class.forName(classForname);
			if(conn != null) {
                Class<?> zClass = object.getClass();
                int i = 0;
                //set parameter
                for(Field field : zClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    statement.setObject((i+1), field.get(object));
                    i++;
                }
                //set parameter parent class
                Class<?> parentClass = zClass.getSuperclass();
                i++;
                while(parentClass != null){
                    for(Field field : parentClass.getDeclaredFields()){
                        field.setAccessible(true);
                        statement.setObject(i, field.get(object));
                        i++;
                    }
                    parentClass = parentClass.getSuperclass();
                }
                int row = statement.executeUpdate();
                rs = statement.getGeneratedKeys();
                conn.commit();
                if(row > 0) {
                    while(rs.next()) {
                        return rs.getLong(1);
                    }
                }
			}
		}
		catch(ClassNotFoundException | SQLException | IllegalAccessException ex) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
		}
		return null;
	}

	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSqlUpdate();
			statement = conn.prepareStatement(sql);
			String classForname = "com.mysql.cj.jdbc.Driver";
			Class.forName(classForname);
			if(conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				//set parameter

				for(int i = 0 ; i <fields.length ; i++) {
					fields[i].setAccessible(true);
					statement.setObject((i+1), fields[i].get(object));
				}

				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				Object id = null;
				while(parentClass != null){
				    Field[] fieldsParentClass = parentClass.getDeclaredFields();
					for(int i = 0 ; i < fieldsParentClass.length;i++) {
                        fieldsParentClass[i].setAccessible(true);
						if(!fieldsParentClass[i].getName().equals("id")){
							statement.setObject(indexParent, fieldsParentClass[i].get(object));
							indexParent += 1;
						}else{
							id = fieldsParentClass[i].get(object);
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(indexParent, id);
				statement.executeUpdate();
				conn.commit();
			}
		}
		catch(ClassNotFoundException | SQLException | IllegalAccessException ex) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
		}
	}

	@Override
	public void delete(long id) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String classForname = "com.mysql.cj.jdbc.Driver";
            Class.forName(classForname);
            String tableName = "";
            if(zClass.isAnnotationPresent(Table.class)){
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }

            String sql = "DELETE FROM " +tableName+ " WHERE id = ?";
            statement = conn.prepareStatement(sql);

            if(conn != null) {
                statement.setObject(1, id);
                statement.executeUpdate();
                conn.commit();
            }
        }
        catch(ClassNotFoundException | SQLException ex) {
            if(conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
	}

    @Override
    public void deleteByProperty(String where) {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String classForname = "com.mysql.cj.jdbc.Driver";
            Class.forName(classForname);
            String tableName = "";
            if(zClass.isAnnotationPresent(Table.class)){
                Table table = zClass.getAnnotation(Table.class);
                tableName = table.name();
            }

            String sql = "DELETE FROM " +tableName+ " " + where;
            statement = conn.createStatement();
            if(conn != null){
                statement.execute(sql);
                conn.commit();
            }
        }
        catch(ClassNotFoundException | SQLException ex) {
            if(conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            try {
                if(conn != null) {
                    conn.close();
                }
                if(statement != null) {
                    statement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
	public <T> T findById(long id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
        String tableName = "";

        if(zClass.isAnnotationPresent(Table.class)){
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }

		String sql = "SELECT * FROM  " + tableName + " WHERE id = ?";
		try {
		    conn = getConnection();
		    statement = conn.prepareStatement(sql);
            statement.setObject(1, id);
			resultSet = statement.executeQuery();
			if(conn != null) {
				return resultSetMapper.mapRow(resultSet, this.zClass).get(0);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
		}
		return null;
	}

    @Override
    public List<T> findAll(Map<String , Object> properties , Pageble pageble, Object... where) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();

        StringBuilder sql = createSqlFindAll(properties);
        if(where != null && where.length > 0){
            sql.append(where[0]);
        }
        if(pageble != null){
            if(pageble.getSorter() != null
                    && StringUtils.isNotBlank(pageble.getSorter().getSortName())
                    && StringUtils.isNotBlank(pageble.getSorter().getSortBy())){
                Sorter sorter = pageble.getSorter();
                sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy()+"");
            }
            if(pageble.getOffSet() != null && pageble.getLimit() != null){
                sql.append(" LIMIT "+pageble.getOffSet()+", "+pageble.getLimit()+"");
            }
        }
        try {
            conn = getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql.toString());
            if(conn != null && resultSet != null) {
                return resultSetMapper.mapRow(resultSet, this.zClass);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(conn != null) {
                    conn.close();
                }
                if(statement != null) {
                    statement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public int countByProperty(Map<String , Object> properties , Object... where) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();

        StringBuilder sql = createSqlCountByProperty(properties);
        if(where != null && where.length > 0){
            sql.append(where[0]);
        }
        try {
            conn = getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql.toString());
            if(conn != null && resultSet != null) {
                while(resultSet.next()){
                    return resultSet.getInt("COUNT(*)");
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(conn != null) {
                    conn.close();
                }
                if(statement != null) {
                    statement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private StringBuilder createSqlCountByProperty(Map<String, Object> properties) {
        String tableName = "";
        if(zClass.isAnnotationPresent(Table.class)){
            tableName = zClass.getAnnotation(Table.class).name();
        }
        StringBuilder result = new StringBuilder("SELECT COUNT(*) FROM "+tableName+" A WHERE 1=1");
        if(properties != null && properties.size() > 0){
            String[] params = new String[properties.size()];
            Object[] value = new Object[properties.size()];
            int i = 0;
            for(Map.Entry<? , ?> item : properties.entrySet()){
                params[i] = (String) item.getKey();
                value[i] = item.getValue();
                i++;
            }
            for(int i1 = 0 ; i1 < params.length ; i1++){
                if(value[i1] != null){
                    if(value[i1] instanceof String){
                        result.append(" AND LOWER("+params[i1]+") LIKE '%"+value[i1].toString().toLowerCase()+"%'");
                    }else if(value[i1] instanceof Integer){
                        result.append(" AND "+params[i1]+" = "+value[i1]+" ");
                    }else if(value[i1] instanceof Long){
                        result.append(" AND "+params[i1]+" = "+value[i1]+" ");
                    }
                }
            }
        }
        return result;
    }

    private StringBuilder createSqlFindAll(Map<String, Object> properties) {
        String tableName = "";
        if(zClass.isAnnotationPresent(Table.class)){
            Table table = zClass.getAnnotation(Table.class);
            tableName = table.name();
        }
	    StringBuilder result = new StringBuilder("SELECT * FROM "+tableName+" A WHERE 1=1");
	    if(properties != null && properties.size() > 0){
            String[] params = new String[properties.size()];
            Object[] value = new Object[properties.size()];
            int i = 0;
            for(Map.Entry<? , ?> item : properties.entrySet()){
                params[i] = (String) item.getKey();
                value[i] = item.getValue();
                i++;
            }
            for(int i1 = 0 ; i1 < params.length ; i1++){
                if(value[i1] != null){
                    if(value[i1] instanceof String){
                        result.append(" AND LOWER("+params[i1]+") LIKE '%"+value[i1].toString().toLowerCase()+"%'");
                    }else if(value[i1] instanceof Integer){
                        result.append(" AND "+params[i1]+" = "+value[i1]+" ");
                    }else if(value[i1] instanceof Long){
                        result.append(" AND "+params[i1]+" = "+value[i1]+" ");
                    }
                }
            }
        }
	    return result;
    }


    private String createSqlUpdate(){
        String tableName = "";
        if(zClass.isAnnotationPresent(Table.class)){
            tableName = zClass.getAnnotation(Table.class).name();
        }
        StringBuilder sets = new StringBuilder("");
        String where = "";
        sets.append(stringSetSqlUpdate(zClass));

        //parent class
        Class parentClass = zClass.getSuperclass();
        while(parentClass != null){
            sets.append(stringSetSqlUpdate(parentClass));
            for(Field field : parentClass.getDeclaredFields()){
                if(field.getAnnotation(Column.class).name().equals("id")){
                    where = " WHERE "+field.getAnnotation(Column.class).name()+" = ?";
                }
            }
            parentClass = parentClass.getSuperclass();
        }
        String sql = "UPDATE " + tableName + " SET " + sets.toString().substring(0,sets.length()-2) + where;
        return sql;
    }

    private String stringSetSqlUpdate(Class zClass){
        String sets = "";
        for(Field field : zClass.getDeclaredFields()){
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                if (!column.name().equals("id")) {
                    sets += column.name() + " = ?, ";
                }
            }
        }
        return sets;
    }

    private String createInsert() {
        String tableName = "";
        if(zClass.isAnnotationPresent(Table.class)){
            tableName = zClass.getAnnotation(Table.class).name();
        }
        StringBuilder fields = new StringBuilder("");
        StringBuilder params = new StringBuilder("");

        //check childen class
        for(Field field : zClass.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(Column.class)){
                fields.append(field.getAnnotation(Column.class).name() + ",");
                params.append("?,");
            }
        }
        //check parent Class
        Class<?> parentClass = zClass.getSuperclass();
        while (parentClass != null){
            for(Field field : parentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.isAnnotationPresent(Column.class)){
                    fields.append(field.getAnnotation(Column.class).name()+ ",");
                    params.append("?,");
                }
            }
            parentClass = parentClass.getSuperclass();
        }
        String sql = "INSERT INTO "+tableName+"("+fields.toString().substring(0,fields.length()-1)+") VALUES("+params.toString().substring(0,params.length()-1)+")";
        return sql;
    }

}
