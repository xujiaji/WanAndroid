/*
 *    Copyright 2018 XuJiaji
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xujiaji.mvvmquick.util;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xujiaji.mvvmquick.base.MQViewModel;
import com.xujiaji.mvvmquick.base.NoneViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author: xujiaji
 * created on: 2018/6/13 14:24
 * description:
 */
public class ClassUtils {
    private static <T> Class<T> getGenericClass(Class<?> klass, Class<?> filterClass) {
        Type type = klass.getGenericSuperclass();
        if (type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types) {
            Class<T> tClass = (Class<T>) t;
            if (filterClass.isAssignableFrom(tClass)) {
                return tClass;
            }
        }
        return null;
    }


    /**
     * 获取泛型ViewModel的class对象
     */
    public static <T> Class<T> getViewModel(Object obj) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, MQViewModel.class);
        if (tClass == null || tClass == MQViewModel.class || tClass == NoneViewModel.class) {
            return null;
        }
        return tClass;
    }

    /**
     * 获取泛型Binding的class对象
     */
    public static <T> T getBinding(@NonNull Object obj, @NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        Class<?> currentClass = obj.getClass();
        Class<T> tClass = getGenericClass(currentClass, ViewDataBinding.class);
        if (tClass == null || tClass == ViewDataBinding.class) {
            return null;
        }
        try {
            Method method = tClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.TYPE);
            Object returnValue = method.invoke(null, inflater, container, false);
            return (T) returnValue;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
