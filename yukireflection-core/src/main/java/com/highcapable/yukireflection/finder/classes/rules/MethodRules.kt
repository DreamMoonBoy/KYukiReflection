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

package com.highcapable.yukireflection.finder.classes.rules

import com.highcapable.yukireflection.bean.VariousClass
import com.highcapable.yukireflection.finder.classes.rules.base.BaseRules
import com.highcapable.yukireflection.finder.classes.rules.result.MemberRulesResult
import com.highcapable.yukireflection.finder.members.data.MethodRulesData
import com.highcapable.yukireflection.finder.type.factory.*
import com.highcapable.yukireflection.type.defined.UndefinedType
import com.highcapable.yukireflection.type.defined.VagueType
import java.lang.reflect.Method

/**
 * [Method] 查找条件实现类
 * @param rulesData 当前查找条件规则数据
 */
class MethodRules internal constructor(@PublishedApi internal val rulesData: MethodRulesData) : BaseRules() {

    /**
     * 设置 [Method] 名称
     * @return [String]
     */
    var name
        get() = rulesData.name
        set(value) {
            rulesData.name = value
        }

    /**
     * 设置 [Method] 参数个数
     *
     * 你可以不使用 [param] 指定参数类型而是仅使用此变量指定参数个数
     *
     * 若参数个数小于零则忽略并使用 [param]
     * @return [Int]
     */
    var paramCount
        get() = rulesData.paramCount
        set(value) {
            rulesData.paramCount = value
        }

    /**
     * 设置 [Method] 返回值
     *
     * - ❗只能是 [Class]、[String]、[VariousClass]
     *
     * - 可不填写返回值
     * @return [Any] or null
     */
    var returnType
        get() = rulesData.returnType
        set(value) {
            rulesData.returnType = value.compat(tag = "Method")
        }

    /**
     * 设置 [Method] 标识符筛选条件
     *
     * - 可不设置筛选条件
     * @param conditions 条件方法体
     */
    fun modifiers(conditions: ModifierConditions) {
        rulesData.modifiers = conditions
    }

    /** 设置 [Method] 空参数、无参数 */
    fun emptyParam() {
        rulesData.paramCount = 0
    }

    /**
     * 设置 [Method] 参数
     *
     * 如果同时使用了 [paramCount] 则 [paramType] 的数量必须与 [paramCount] 完全匹配
     *
     * 如果 [Method] 中存在一些无意义又很长的类型 - 你可以使用 [VagueType] 来替代它
     *
     * 例如下面这个参数结构 ↓
     *
     * ```java
     * void foo(String var1, boolean var2, com.demo.Test var3, int var4)
     * ```
     *
     * 此时就可以简单地写作 ↓
     *
     * ```kotlin
     * param(StringType, BooleanType, VagueType, IntType)
     * ```
     *
     * - ❗无参 [Method] 请使用 [emptyParam] 设置查找条件
     *
     * - ❗有参 [Method] 必须使用此方法设定参数或使用 [paramCount] 指定个数
     * @param paramType 参数类型数组 - ❗只能是 [Class]、[String]、[VariousClass]
     */
    fun param(vararg paramType: Any) {
        if (paramType.isEmpty()) error("paramTypes is empty, please use emptyParam() instead")
        rulesData.paramTypes =
            arrayListOf<Class<*>>().apply { paramType.forEach { add(it.compat(tag = "Method") ?: UndefinedType) } }.toTypedArray()
    }

    /**
     * 设置 [Method] 参数条件
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * param { it[1] == StringClass || it[2].name == "java.lang.String" }
     * ```
     *
     * - ❗无参 [Method] 请使用 [emptyParam] 设置查找条件
     *
     * - ❗有参 [Method] 必须使用此方法设定参数或使用 [paramCount] 指定个数
     * @param conditions 条件方法体
     */
    fun param(conditions: ObjectsConditions) {
        rulesData.paramTypesConditions = conditions
    }

    /**
     * 设置 [Method] 名称条件
     * @param conditions 条件方法体
     */
    fun name(conditions: NameConditions) {
        rulesData.nameConditions = conditions
    }

    /**
     * 设置 [Method] 参数个数范围
     *
     * 你可以不使用 [param] 指定参数类型而是仅使用此方法指定参数个数范围
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * paramCount(1..5)
     * ```
     * @param numRange 个数范围
     */
    fun paramCount(numRange: IntRange) {
        rulesData.paramCountRange = numRange
    }

    /**
     * 设置 [Method] 参数个数条件
     *
     * 你可以不使用 [param] 指定参数类型而是仅使用此方法指定参数个数条件
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * paramCount { it >= 5 || it.isZero() }
     * ```
     * @param conditions 条件方法体
     */
    fun paramCount(conditions: CountConditions) {
        rulesData.paramCountConditions = conditions
    }

    /**
     * 设置 [Method] 返回值条件
     *
     * - 可不填写返回值
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * returnType { it == StringClass || it.name == "java.lang.String" }
     * ```
     * @param conditions 条件方法体
     */
    fun returnType(conditions: ObjectConditions) {
        rulesData.returnTypeConditions = conditions
    }

    /**
     * 返回结果实现类
     * @return [MemberRulesResult]
     */
    @PublishedApi
    internal fun build() = MemberRulesResult(rulesData)
}