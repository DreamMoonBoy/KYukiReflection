/*
 * YukiReflection - An efficient Reflection API for Java and Android built in Kotlin
 * Copyright (C) 2019-2023 HighCapable
 * https://github.com/fankes/YukiReflection
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is created by fankes on 2022/9/20.
 * This file is modified by fankes on 2023/1/21.
 */
@file:Suppress("unused", "UNCHECKED_CAST")

package com.highcapable.yukireflection.bean

import java.lang.reflect.ParameterizedType

/**
 * 当前 [Class] 的泛型父类操作对象
 * @param type 类型声明实例
 */
class GenericClass internal constructor(@PublishedApi internal val type: ParameterizedType) {

    /**
     * 获得泛型参数数组下标的 [Class] 实例
     * @param index 数组下标 - 默认 0
     * @return [Class]
     */
    fun argument(index: Int = 0) = type.actualTypeArguments[index] as Class<*>

    /**
     * 获得泛型参数数组下标的 [Class] 实例
     * @param index 数组下标 - 默认 0
     * @return [Class]<[T]>
     * @throws IllegalStateException 如果 [Class] 的类型不为 [T]
     */
    @JvmName("argument_Generics")
    inline fun <reified T> argument(index: Int = 0) =
        type.actualTypeArguments[index] as? Class<T> ?: error("Target Class type cannot cast to ${T::class.java}")
}