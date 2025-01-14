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
package io.github.dreammooncai.yukireflection.type.factory

import io.github.dreammooncai.yukireflection.bean.KGenericClass
import io.github.dreammooncai.yukireflection.core.KTypeBuild
import io.github.dreammooncai.yukireflection.finder.base.rules.KModifierRules
import io.github.dreammooncai.yukireflection.finder.classes.KClassFinder
import io.github.dreammooncai.yukireflection.finder.callable.KConstructorFinder
import io.github.dreammooncai.yukireflection.finder.callable.KFunctionFinder
import io.github.dreammooncai.yukireflection.finder.callable.KPropertyFinder
import io.github.dreammooncai.yukireflection.finder.base.rules.KCountRules
import io.github.dreammooncai.yukireflection.finder.base.rules.KNameRules
import io.github.dreammooncai.yukireflection.finder.base.rules.KObjectRules
import io.github.dreammooncai.yukireflection.finder.signature.KFunctionSignatureFinder
import io.github.dreammooncai.yukireflection.finder.signature.KPropertySignatureFinder
import kotlin.reflect.KParameter
import kotlin.reflect.KType

/** 定义 [ClassLoader] 装载实例方法体类型 */
internal typealias KClassLoaderInitializer = () -> ClassLoader?

/** 定义 [KTypeBuild] 方法体类型 */
internal typealias KTypeBuildConditions = KTypeBuild.() -> Unit

/** 定义 [KClassFinder] 方法体类型 */
internal typealias KClassConditions = KClassFinder.() -> Unit

/** 定义 [KPropertyFinder] 方法体类型 */
internal typealias KPropertyConditions = KPropertyFinder.() -> Unit

/** 定义 [KFunctionFinder] 方法体类型 */
internal typealias KFunctionConditions = KFunctionFinder.() -> Unit

/** 定义 [KFunctionSignatureFinder] 方法体类型 */
internal typealias KFunctionSignatureConditions = KFunctionSignatureFinder.() -> Unit

/** 定义 [KPropertySignatureFinder] 方法体类型 */
internal typealias KPropertySignatureConditions = KPropertySignatureFinder.() -> Unit

/** 定义 [KConstructorFinder] 方法体类型 */
internal typealias KConstructorConditions = KConstructorFinder.() -> Unit

/** 定义 [KNameRules] 方法体类型 */
internal typealias KNameConditions = KNameRules.(String) -> Boolean

/** 定义 [KCountRules] 方法体类型 */
internal typealias KCountConditions = KCountRules.(Int) -> Boolean

/** 定义 [KModifierRules] 方法体类型 */
internal typealias KModifierConditions = KModifierRules.() -> Boolean

/** 定义 [KObjectRules] 方法体类型 */
internal typealias KTypeConditions = KObjectRules.(KType) -> Boolean

/** 定义 [KObjectRules] 方法体类型 */
internal typealias KParameterConditions = KObjectRules.(List<KParameter>) -> Boolean

/** 定义 [KObjectRules] 方法体类型 */
internal typealias KNamesConditions = KObjectRules.(List<String?>) -> Boolean

/** 定义 [KGenericClass] 方法体类型 */
internal typealias KGenericClassDomain = KGenericClass.() -> Unit