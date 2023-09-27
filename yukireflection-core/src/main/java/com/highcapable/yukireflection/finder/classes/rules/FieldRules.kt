/*
 * YukiReflection - An efficient Reflection API for Java and Android built in Kotlin.
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
 * This file is modified by fankes on 2023/1/25.
 */
package com.highcapable.yukireflection.finder.classes.rules

import com.highcapable.yukireflection.bean.VariousClass
import com.highcapable.yukireflection.finder.classes.rules.base.BaseRules
import com.highcapable.yukireflection.finder.classes.rules.result.MemberRulesResult
import com.highcapable.yukireflection.finder.members.data.FieldRulesData
import com.highcapable.yukireflection.finder.type.factory.ModifierConditions
import com.highcapable.yukireflection.finder.type.factory.NameConditions
import com.highcapable.yukireflection.finder.type.factory.ObjectConditions
import java.lang.reflect.Field

/**
 * [Field] 查找条件实现类
 * @param rulesData 当前查找条件规则数据
 */
class FieldRules internal constructor(private val rulesData: FieldRulesData) : BaseRules() {

    /**
     * 设置 [Field] 名称
     * @return [String]
     */
    var name
        get() = rulesData.name
        set(value) {
            rulesData.name = value
        }

    /**
     * 设置 [Field] 类型
     *
     * - ❗只能是 [Class]、[String]、[VariousClass]
     *
     * - 可不填写类型
     * @return [Any] or null
     */
    var type
        get() = rulesData.type
        set(value) {
            rulesData.type = value?.compat(tag = "Field")
        }

    /**
     * 设置 [Field] 标识符筛选条件
     *
     * - 可不设置筛选条件
     * @param conditions 条件方法体
     */
    fun modifiers(conditions: ModifierConditions) {
        rulesData.modifiers = conditions
    }

    /**
     * 设置 [Field] 名称条件
     * @param conditions 条件方法体
     */
    fun name(conditions: NameConditions) {
        rulesData.nameConditions = conditions
    }

    /**
     * 设置 [Field] 类型条件
     *
     * - 可不填写类型
     *
     * 使用示例如下 ↓
     *
     * ```kotlin
     * type { it == StringClass || it.name == "java.lang.String" }
     * ```
     * @param conditions 条件方法体
     */
    fun type(conditions: ObjectConditions) {
        rulesData.typeConditions = conditions
    }

    /**
     * 返回结果实现类
     * @return [MemberRulesResult]
     */
    internal fun build() = MemberRulesResult(rulesData)
}