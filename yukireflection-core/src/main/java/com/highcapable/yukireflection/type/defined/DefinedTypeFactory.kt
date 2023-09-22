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
 * This file is created by fankes on 2022/4/3.
 * This file is Modified by fankes on 2023/1/21.
 */
package com.highcapable.yukireflection.type.defined

import com.highcapable.yukireflection.factory.classOf

/**
 * 未定义类型实例
 *
 * 请使用 [UndefinedType] 来调用它
 */
internal class UndefinedClass private constructor()

/**
 * 模糊类型实例
 *
 * 请使用 [VagueType] 来调用它
 */
class VagueClass private constructor()

/**
 * 得到未定义类型
 * @return [Class]<[UndefinedClass]>
 */
internal val UndefinedType get() = classOf<UndefinedClass>()

/**
 * 得到模糊类型
 * @return [Class]<[VagueClass]>
 */
val VagueType get() = classOf<VagueClass>()