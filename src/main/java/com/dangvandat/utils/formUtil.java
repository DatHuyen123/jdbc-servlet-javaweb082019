package com.dangvandat.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public class formUtil {

    public static <T> T toModel(Class<T> zClass, HttpServletRequest request){
        T object = null;
        try{
            object = zClass.newInstance();
            BeanUtils.populate(object , request.getParameterMap());
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
