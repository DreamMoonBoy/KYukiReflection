/*
 * YukiReflection - An efficient Reflection API for Java and Android built in Kotlin.
 * Copyright (C) 2019-2024 HighCapable
 * https://github.com/HighCapable/YukiReflection
 *
 * Apache License Version 2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file is created by fankes on 2022/9/14.
 * This file is modified by fankes on 2023/1/21.
 */
@file:Suppress("unused")

package com.dream.yukireflection.finder.base.rules

import kotlin.reflect.*

/**
 * 这是一个模糊 [KClass]、[KCallable] 数组 (下标) 个数条件实现类
 *
 * 可对 R8 混淆后的 [KClass]、[KCallable] 进行更加详细的定位
 * @param instance 当前实例对象
 */
class KCountRules private constructor(private val instance: Int) {

    internal companion object {

        /**
         * 创建实例
         * @param instance 实例对象
         * @return [KCountRules]
         */
        internal fun with(instance: Int) = KCountRules(instance)
    }

    /**
     * 是否为 0
     * @return [Boolean]
     */
    fun Int.isZero() = this == 0

    /**
     * 大于 [count]
     * @param count 目标对象
     * @return [Boolean]
     */
    fun Int.moreThan(count: Int) = this > count

    /**
     * 小于 [count]
     * @param count 目标对象
     * @return [Boolean]
     */
    fun Int.lessThan(count: Int) = this < count

    /**
     * 在 [countRange] 区间 A ≤ this ≤ B
     * @param countRange 区间
     * @return [Boolean]
     */
    fun Int.inInterval(countRange: IntRange) = this in countRange

    override fun toString() = "CountRules [$instance]"
}