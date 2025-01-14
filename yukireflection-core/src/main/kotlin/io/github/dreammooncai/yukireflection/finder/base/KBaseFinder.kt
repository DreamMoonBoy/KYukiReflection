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
 * This file is created by fankes on 2022/9/4.
 * This file is modified by fankes on 2023/1/21.
 */
package io.github.dreammooncai.yukireflection.finder.base

import io.github.dreammooncai.yukireflection.bean.KGenericClass
import io.github.dreammooncai.yukireflection.bean.KVariousClass
import io.github.dreammooncai.yukireflection.factory.isCase
import io.github.dreammooncai.yukireflection.factory.toKClass
import io.github.dreammooncai.yukireflection.type.defined.UndefinedKotlin
import io.github.dreammooncai.yukireflection.finder.base.data.KBaseRulesData
import kotlin.math.abs
import kotlin.reflect.*

/**
 * 这是 [KClass] 与 [KCallableBaseFinder] 查找类功能的基本类实现
 */
abstract class KBaseFinder {

    /** 当前查找条件规则数据 */
    internal abstract val rulesData: KBaseRulesData

    /**
     * 字节码、数组下标筛选数据类型
     */
    internal enum class IndexConfigType { ORDER, MATCH }

    /**
     * 字节码、数组下标筛选实现类
     * @param type 类型
     */
    inner class IndexTypeCondition internal constructor(private val type: IndexConfigType) {

        /**
         * 设置下标
         *
         * 若 index 小于零则为倒序 - 此时可以使用 [IndexTypeConditionSort.reverse] 方法实现
         *
         * 可使用 [IndexTypeConditionSort.first] 和 [IndexTypeConditionSort.last] 设置首位和末位筛选条件
         * @param num 下标
         */
        fun index(num: Int) = when (type) {
            IndexConfigType.ORDER -> rulesData.orderIndex = Pair(num, true)
            IndexConfigType.MATCH -> rulesData.matchIndex = Pair(num, true)
        }

        /**
         * 得到下标
         * @return [IndexTypeConditionSort]
         */
        fun index() = IndexTypeConditionSort()

        /**
         * 字节码、数组下标排序实现类
         *
         * - 请使用 [index] 方法来获取 [IndexTypeConditionSort]
         */
        inner class IndexTypeConditionSort internal constructor() {

            /** 设置满足条件的第一个*/
            fun first() = index(num = 0)

            /** 设置满足条件的最后一个*/
            fun last() = when (type) {
                IndexConfigType.ORDER -> rulesData.orderIndex = Pair(0, false)
                IndexConfigType.MATCH -> rulesData.matchIndex = Pair(0, false)
            }

            /**
             * 设置倒序下标
             * @param num 下标
             */
            fun reverse(num: Int) = when {
                num < 0 -> index(abs(num))
                num == 0 -> index().last()
                else -> index(-num)
            }
        }
    }

    companion object{
        const val TAG_CLASS = "Class"

        const val TAG_CONSTRUCTOR = "Constructor"

        const val TAG_PROPERTY = "Property"

        const val TAG_FUNCTION = "Function"

        /**
         * 参数标签指此次转换针对参数使用进行转换
         */
        const val TAG_PARAMETER = "Parameter"

        /**
         * 检测当前类型是否被支持
         *
         * @return [Class]/[KClassifier]/[KClass]/[KTypeParameter] or [KTypeProjection]/array([KTypeProjection]) or [KVariance]/array([KVariance]) or [KGenericClass] or [KType] or [KParameter] or [KParameter.Kind] or [String] or [KVariousClass] or error
         */
        internal fun Any?.checkSupportedTypes(tag: String? = null) = when(tag){
            TAG_CONSTRUCTOR,TAG_PARAMETER -> when(this){
                null -> null
                is Class<*>,is KClassifier,is KType,is KParameter,is KParameter.Kind,is String,is KVariousClass,is KGenericClass -> this
                else -> checkArrayGenerics(this)
            }
            null -> when(this){
                    null -> null
                is Class<*>,is KClassifier,is KTypeProjection,is KVariance,is KType,is String,is KVariousClass,is KGenericClass -> this
                    else -> checkArrayGenerics(this)
                }
            else -> when(this) {
                null -> null
                is Class<*>,is KClassifier,is KType,is String,is KVariousClass,is KGenericClass -> this
                else -> checkArrayGenerics(this)
            }
        }

        /**
         * 支持传入的泛型数组类型
         */
        private val genericsClasses = arrayListOf(KTypeProjection::class.java,KVariance::class.java)

        /**
         * 检查是否是泛型数组，如果是返回数组否则 error
         *
         * @param generic 泛型数组
         * @return [List]
         */
        internal fun checkArrayGenerics(generic:Any):List<*>{
            val condition = when(generic){
                is Collection<*> -> generic
                is Array<*> -> generic.toList()
                else -> error("$this match type \"${generic.javaClass}\" not allowed")
            }
            val result = arrayListOf<Any>()
            condition.forEach { any ->
                if (any != null && genericsClasses.any {any.isCase(it.kotlin)}){
                    result += any
                }
            }
            return result
        }
    }

    /**
     * 将目标类型转换为可识别的兼容类型
     * @param tag 当前查找类的标识
     * @param loader 使用的 [ClassLoader]
     * @return [Class]/[KClassifier]/[KClass]/[KTypeParameter] or [KTypeProjection]/array([KTypeProjection]) or [KVariance]/array([KVariance]) or [KType] or [KGenericClass] or null
     */
    internal fun Any?.compat(tag: String, loader: ClassLoader?) = when (val thisRef = this.checkSupportedTypes(tag)) {
        null -> null
        is Class<*> -> thisRef.kotlin
        is KClass<*>,is KTypeProjection,is KType,is KGenericClass -> thisRef
        is String -> runCatching { thisRef.toKClass(loader) }.getOrNull() ?: UndefinedKotlin
        is KVariousClass -> runCatching { thisRef.get(loader) }.getOrNull() ?: UndefinedKotlin
        else -> checkArrayGenerics(thisRef)
    }

    /**
     * 返回结果实现类
     *
     * - 此功能交由方法体自动完成 - 你不应该手动调用此方法
     * @return [BaseResult]
     */
    abstract fun build(): BaseResult

    /**
     * 返回只有异常的结果实现类
     *
     * - 此功能交由方法体自动完成 - 你不应该手动调用此方法
     * @param throwable 异常
     * @return [BaseResult]
     */
    internal abstract fun failure(throwable: Throwable?): BaseResult

    /**
     * 查找结果实现、处理类接口
     *
     * - 此功能交由方法体自动完成 - 你不应该手动继承此接口
     */
    interface BaseResult

    /**
     * 查找结果实例处理接口
     *
     * - 此功能交由方法体自动完成 - 你不应该手动继承此接口
     */
    interface BaseInstance{
        /**
         * 执行以获得此实例的执行结果
         *
         * - 此功能交由方法体自动完成 - 你不应该手动继承此接口
         * @param args 参数列表
         * @return 执行结果
         */
        fun callResult(vararg args:Any?):Any? = null
    }
}