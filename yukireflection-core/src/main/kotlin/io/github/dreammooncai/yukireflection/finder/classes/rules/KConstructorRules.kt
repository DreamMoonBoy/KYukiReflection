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
 * This file is created by fankes on 2022/9/12.
 * This file is modified by fankes on 2023/1/25.
 */
@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.dreammooncai.yukireflection.finder.classes.rules

import io.github.dreammooncai.yukireflection.finder.base.KBaseFinder
import io.github.dreammooncai.yukireflection.finder.classes.rules.base.KBaseRules
import io.github.dreammooncai.yukireflection.finder.classes.rules.result.KCallableRulesResult
import io.github.dreammooncai.yukireflection.finder.callable.data.KConstructorRulesData
import io.github.dreammooncai.yukireflection.type.factory.KModifierConditions
import io.github.dreammooncai.yukireflection.type.factory.KParameterConditions
import io.github.dreammooncai.yukireflection.type.defined.UndefinedKotlin
import io.github.dreammooncai.yukireflection.type.defined.VagueKotlin
import io.github.dreammooncai.yukireflection.type.factory.KCountConditions
import io.github.dreammooncai.yukireflection.type.factory.KNamesConditions
import kotlin.reflect.*
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KTypeParameter

/**
 * Constructor[KFunction] 查找条件实现类
 * @param rulesData 当前查找条件规则数据
 */
class KConstructorRules internal constructor(private val rulesData: KConstructorRulesData) : KBaseRules() {

    /**
     * 设置 Constructor[KFunction] 参数个数
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
     * 设置 Constructor[KFunction] 标识符筛选条件
     *
     * - 可不设置筛选条件
     * @param conditions 条件方法体
     */
    fun modifiers(conditions: KModifierConditions) {
        rulesData.modifiers = conditions
    }

    /** 设置 Constructor[KFunction] 空参数、无参数 */
    fun emptyParam() {
        rulesData.paramCount = 0
    }

    /**
     * 设置 Constructor[KFunction] 参数
     *
     * 如果同时使用了 [paramCount] 则 [paramType] 的数量必须与 [paramCount] 完全匹配
     *
     * 如果 Constructor[KFunction] 中存在一些无意义又很长的类型 - 你可以使用 [VagueKotlin] 来替代它
     *
     * 例如下面这个参数结构 ↓
     *
     * ```java
     * Foo(String var1, boolean var2, com.demo.Test var3, int var4)
     * ```
     *
     * 此时就可以简单地写作 ↓
     *
     * ```kotlin
     * param(StringKClass, BooleanKClass, VagueKotlin, IntKClass)
     * ```
     *
     * - 无参 Constructor[KFunction] 请使用 [emptyParam] 设置查找条件
     *
     * - 有参 Constructor[KFunction] 必须使用此方法设定参数或使用 [paramCount] 指定个数
     * @param paramType 参数类型数组 - 只能是 [Class]/[KClassifier]/[KClass]/[KTypeParameter]、[KGenericClass]、[KType]、[KParameter]、[KParameter.Kind]、[String]、[KVariousClass]
     */
    fun param(vararg paramType: Any) {
        if (paramType.isEmpty()) error("paramTypes is empty, please use emptyParam() instead")
        rulesData.paramTypes =
            mutableListOf<Any>().apply { paramType.forEach { add(it.compat(tag = KBaseFinder.TAG_CONSTRUCTOR) ?: UndefinedKotlin) } }.toTypedArray()
    }

    /**
     * 设置 Constructor[KFunction] 参数条件
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * param { it[1] == StringKClass || it[2].name == "java.lang.String" }
     * ```
     *
     * - 无参 Constructor[KFunction] 请使用 [emptyParam] 设置查找条件
     *
     * - 有参 Constructor[KFunction] 必须使用此方法设定参数或使用 [paramCount] 指定个数
     * @param conditions 条件方法体
     */
    fun param(conditions: KParameterConditions) {
        rulesData.paramTypesConditions = conditions
    }

    /**
     * 设置 Constructor[KFunction] 参数名称
     *
     * 如果 Constructor[KFunction] 中存在一些不太清楚的参数名称 - 你可以使用 [VagueKotlin].name 或者 空字符串 或者 "null" 来替代它
     *
     * 例如下面这个参数结构 ↓
     *
     * ```java
     * Foo(String count, boolean un$abc, com.demo.Test ends)
     * ```
     *
     * 此时就可以简单地写作 ↓
     *
     * ```kotlin
     * paramName("count","","ends")
     * ```
     *
     * @param paramName 参数名称数组
     */
    fun paramName(vararg paramName: String) {
        if (paramName.isEmpty()) error("paramTypes is empty, please use emptyParam() instead")
        rulesData.paramNames = paramName
    }


    /**
     * 设置 Constructor[KFunction] 参数名称条件
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * paramName { it.isNull() }
     * ```
     * @param conditions 条件方法体
     */
    fun paramName(conditions: KNamesConditions) {
        rulesData.paramNamesConditions = conditions
    }

    /**
     * 设置 Constructor[KFunction] 参数个数范围
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
     * 设置 Constructor[KFunction] 参数个数条件
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
    fun paramCount(conditions: KCountConditions) {
        rulesData.paramCountConditions = conditions
    }

    /**
     * 返回结果实现类
     * @return [KCallableRulesResult]
     */
    internal fun build() = KCallableRulesResult(rulesData)
}