package com.custom.sean.common.utils.basics;

import com.custom.sean.common.exception.CheckedException;
import com.custom.sean.common.utils.jpa.BaseRepository;
import com.custom.sean.common.utils.vo.ResultEnum;
import org.springframework.data.domain.Example;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sean on 2017/12/22.
 */
public class BusinessUtils {
    public static List<Set<Long>> getRetainList(Set<Long> existList,Set<Long> newList) {
        //要删除的集合
        Set<Long> allList=new HashSet<>();
        allList.addAll(newList);
        allList.addAll(existList);
        allList.removeAll(newList);
        Set<Long> deleteSet=new HashSet<>();
        deleteSet.addAll(allList);
        //要新增的集合
        allList.addAll(newList);
        allList.removeAll(existList);
        Set<Long> addSet=new HashSet<>();
        addSet.addAll(allList);

        List<Set<Long>> resultList=new ArrayList<>();
        resultList.add(deleteSet);
        resultList.add(addSet);
        return resultList;
    }

    public static String getCode(String name, BaseRepository baseRepository, Class<?> clazz){
        try {
            String code = ChineseCharacterUtil.getLowerCase(name, false);
            int i = 0;
            DecimalFormat f = new DecimalFormat("00");
            boolean exists = true;
            Object object = clazz.newInstance();
            while (exists) {
                Method m = clazz.getMethod("setCode", String.class);
                m.invoke(object, i == 0 ? code : code + f.format(i));
                exists = baseRepository.exists(Example.of(object));
                if (exists) {
                    i++;
                }
            }
            return i == 0 ? code : code + f.format(i);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CheckedException(ResultEnum.CODE_MAX);
        }
    }
}
