package com.karu.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: create copy domian property
 * @author: StevenWu
 * @create: 2019-06-04 16:10
 **/

public class MyBeanUtils {

    public static String[] getNullPropertyNames(Object source){
        BeanWrapper beanWrapper=new BeanWrapperImpl(source);
        PropertyDescriptor [] pds=beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames=new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }

}
