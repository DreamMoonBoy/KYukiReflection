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
 * This file is created by fankes on 2022/9/4.
 * This file is modified by fankes on 2023/1/21.
 */
package com.highcapable.yukireflection.finder.members.data

import com.highcapable.yukireflection.finder.type.factory.NameConditions
import com.highcapable.yukireflection.finder.type.factory.ObjectConditions
import java.lang.reflect.Field

/**
 * [Field] 规则查找数据类
 * @param name 名称
 * @param nameConditions 名称规则
 * @param type 类型
 * @param typeConditions 类型条件
 */
internal class FieldRulesData internal constructor(
    var name: String = "",
    var nameConditions: NameConditions? = null,
    var type: Any? = null,
    var typeConditions: ObjectConditions? = null
) : MemberRulesData() {

    override val templates
        get() = arrayOf(
            name.takeIf { it.isNotBlank() }?.let { "name:[$it]" } ?: "",
            nameConditions?.let { "nameConditions:[existed]" } ?: "",
            type?.let { "type:[$it]" } ?: "",
            typeConditions?.let { "typeConditions:[existed]" } ?: "", *super.templates
        )

    override val objectName get() = "Field"

    override val isInitialize
        get() = super.isInitializeOfSuper || name.isNotBlank() || nameConditions != null || type != null || typeConditions != null

    override fun toString() = "[$name][$nameConditions][$type][$typeConditions]" + super.toString()
}