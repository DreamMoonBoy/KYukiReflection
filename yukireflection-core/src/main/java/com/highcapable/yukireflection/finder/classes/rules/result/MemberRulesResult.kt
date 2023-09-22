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
 * This file is created by fankes on 2022/9/12.
 * This file is Modified by fankes on 2023/1/25.
 */
@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.highcapable.yukireflection.finder.classes.rules.result

import com.highcapable.yukireflection.finder.members.data.MemberRulesData
import com.highcapable.yukireflection.finder.type.factory.CountConditions
import java.lang.reflect.Member

/**
 * 当前 [Member] 查找条件结果实现类
 * @param rulesData 当前查找条件规则数据
 */
class MemberRulesResult internal constructor(private val rulesData: MemberRulesData) {

    /**
     * 设置当前 [Member] 在查找条件中个数为 0
     * @return [MemberRulesResult] 可继续向下监听
     */
    fun none() = count(num = 0)

    /**
     * 设置当前 [Member] 在查找条件中需要全部匹配的个数
     * @param num 个数
     * @return [MemberRulesResult] 可继续向下监听
     */
    fun count(num: Int): MemberRulesResult {
        rulesData.matchCount = num
        return this
    }

    /**
     * 设置当前 [Member] 在查找条件中需要全部匹配的个数范围
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * count(1..5)
     * ```
     * @param numRange 个数范围
     * @return [MemberRulesResult] 可继续向下监听
     */
    fun count(numRange: IntRange): MemberRulesResult {
        rulesData.matchCountRange = numRange
        return this
    }

    /**
     * 设置当前 [Member] 在查找条件中需要全部匹配的个数条件
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * count { it >= 5 || it.isZero() }
     * ```
     * @param conditions 条件方法体
     * @return [MemberRulesResult] 可继续向下监听
     */
    fun count(conditions: CountConditions): MemberRulesResult {
        rulesData.matchCountConditions = conditions
        return this
    }
}