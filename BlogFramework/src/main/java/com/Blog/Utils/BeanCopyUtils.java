package com.Blog.Utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    //该类无法被new
    private BeanCopyUtils() {}
    public static <T> T CopyBean(Object source,Class<T> clazz) {
        //创建目标对象
        T result= null;
        try {
            result= clazz.newInstance();
            //实现拷贝
            BeanUtils.copyProperties(source,result);
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }
    public  static  <O,T> List<T> CopyBeanList(List<O> list,Class<T> clazz){
        return list.stream()
                .map(o -> CopyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
