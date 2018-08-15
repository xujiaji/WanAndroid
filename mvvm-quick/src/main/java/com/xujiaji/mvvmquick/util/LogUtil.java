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

import android.util.Log;

import com.xujiaji.mvvmquick.BuildConfig;

import java.util.Locale;

public class LogUtil {
    public static boolean LOGV = BuildConfig.DEBUG;
    public static boolean LOGD = BuildConfig.DEBUG;
    public static boolean LOGI = BuildConfig.DEBUG;
    public static boolean LOGW = BuildConfig.DEBUG;
    public static boolean LOGE = BuildConfig.DEBUG;

    // 直接使用Log
    public static void v(String tag, String mess) {
        if (LOGV) {
            Log.v(tag, mess);
        }
    }

    public static void d(String tag, String mess) {
        if (LOGD) {
            Log.d(tag, mess);
        }
    }

    public static void i(String tag, String mess) {
        if (LOGI) {
            Log.i(tag, mess);
        }
    }

    public static void w(String tag, String mess) {
        if (LOGW) {
            Log.w(tag, mess);
        }
    }

    public static void e(String tag, String mess) {
        if (LOGE) {
            Log.e(tag, mess);
        }
    }

    /**
     * 获取到调用者的类名
     *
     * @return 调用者的类名
     */
    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    //不需要再在类中定义TAG，直接打印日志信息
    public static void v1(String mess) {
        if (LOGV) {
            Log.v(getTag(), mess);
        }
    }

    public static void d1(String mess) {
        if (LOGD) {
            Log.d(getTag(), mess);
        }
    }

    public static void i1(String mess) {
        if (LOGI) {
            Log.i(getTag(), mess);
        }
    }

    public static void w1(String mess) {
        if (LOGW) {
            Log.w(getTag(), mess);
        }
    }

    public static void e1(String mess) {
        if (LOGE) {
            Log.e(getTag(), mess);
        }
    }

    /**
     * 获取线程ID，方法名和输出信息
     *
     * @param msg
     * @return
     */
    private static String buildMessage(String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String caller = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                caller = trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread()
                .getId(), caller, msg);
    }

    //不需要再在类中定义TAG，打印线程ID，方法名和输出信息
    public static void v2(String mess) {
        if (LOGV) {
            Log.v(getTag(), buildMessage(mess));
        }
    }

    public static void d2(String mess) {
        if (LOGD) {
            Log.d(getTag(), buildMessage(mess));
        }
    }

    public static void i2(String mess) {
        if (LOGI) {
            Log.i(getTag(), buildMessage(mess));
        }
    }

    public static void w2(String mess) {
        if (LOGW) {
            Log.w(getTag(), buildMessage(mess));
        }
    }

    public static void e2(String mess) {
        if (LOGE) {
            Log.e(getTag(), buildMessage(mess));
        }
    }


    //不需要再在类中定义TAG，打印类名,方法名,行号等.并定位行
    public static void v3(String mess) {
        if (LOGV) {
            Log.v(getTag(), getMsgFormat(mess));
        }
    }

    public static void d3(String mess) {
        if (LOGD) {
            Log.d(getTag(), getMsgFormat(mess));
        }
    }

    public static void i3(String mess) {
        if (LOGI) {
            Log.i(getTag(), getMsgFormat(mess));
        }
    }

    public static void w3(String mess) {
        if (LOGW) {
            Log.w(getTag(), getMsgFormat(mess));
        }
    }

    public static void e3(String mess) {
        if (LOGE) {
            Log.e(getTag(), getMsgFormat(mess));
        }
    }

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行
     *
     * @return
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtil.class.getName())) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }

    /**
     * 输出格式定义
     *
     * @param msg
     * @return
     */
    private static String getMsgFormat(String msg) {
        return msg + " \n--- " + getFunctionName();
    }
}