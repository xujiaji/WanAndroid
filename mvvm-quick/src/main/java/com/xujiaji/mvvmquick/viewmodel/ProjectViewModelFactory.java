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

package com.xujiaji.mvvmquick.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;

/**
 * author: xujiaji
 * created on: 2018/6/12 13:22
 * description:
 */
public class ProjectViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<?>, Callable<Lazy<? extends ViewModel>>> creators;

    @Inject
    public ProjectViewModelFactory(Map<Class<?>, Callable<Lazy<? extends ViewModel>>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Callable<Lazy<? extends ViewModel>> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<?>, Callable<Lazy<? extends ViewModel>>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
