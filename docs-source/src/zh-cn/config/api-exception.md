---
pageClass: hidden-anchor-page
---

# API 异常处理

> 异常是在开发过程经常遇到的主要问题，这里介绍了 `KYukiReflection` 在使用过程中可能遇到的常见异常以及处理方式。

这里的异常说明只会同步最新的 API 版本，较旧的 API 版本的异常将不会再进行说明，请始终保持 API 版本为最新。

## 非阻断异常

> 这些异常不会导致 APP 停止运行 (FC)，但是会在控制台打印 `E` 级别的日志，也可能会停止继续执行相关功能。

###### exception

::: danger loggerE

KFunction/KConstructor/KProperty match type "**TYPE**" not allowed

:::

**异常原因**

在查找方法、构造方法以及变量时设置了不允许的参数类型。

> 示例如下

```kotlin
// 查找一个方法
function {
    // 设置了无效的类型举例
    param(false, 1, 0)
    // 设置了无效的类型举例
    returnType = false
}

// 查找一个变量
property {
    // 设置了无效的类型举例
    type = false
}
```

**解决方案**

在查找中 `param`、`returnType`、`type` 中仅接受 `Class/KClassifier/KClass/KTypeParameter`、`KGenericClass`、`KType`、`KParameter`、`KParameter.Kind`、`String`、`KVariousClass` 类型的传值，不可传入参数实例。

> 示例如下

```kotlin
// 查找一个方法
function {
    // ✅ 正确的使用方法举例
    param(BooleanKClass, IntKClass, IntKClass)
    // ✅ 正确的使用方法举例
    returnType = BooleanKClass
    // ✅ 以下方案也是正确的
    returnType = "java.lang.Boolean"
}

// 查找一个变量
property {
    // ✅ 正确的使用方法举例
    type = BooleanKClass
}
```

###### exception

::: danger loggerE

NoSuchFunction/NoSuchConstructor/NoSuchProperty happend in \[**NAME**\]

:::

**异常原因**

在查找方法、构造方法以及变量时并未找到目标方法、构造方法以及变量。

**解决方案**

请确认你的查找条件是否能正确匹配到目标 `KClass` 中的指定方法、构造方法以及变量。

###### exception

::: danger loggerE

Trying **COUNT** times and all failure by RemedyPlan

:::

**异常原因**

使用 `RemedyPlan` 重新查找方法、构造方法、变量时依然没有找到方法、构造方法、变量。

**解决方案**

请确认你设置的 `RemedyPlan` 参数以及当前 APP 内存在的 `KClass`，再试一次。

###### exception

::: danger loggerE

Can't find this KFunction/KConstructor/KProperty in \[**CLASS**\]: **CONTENT** Generated by KYukiReflection#KReflectionTool

:::

**异常原因**

通过指定条件找不到需要查找的方法、构造方法以及变量。

> 示例如下

```kotlin
TargetClass.function {
    name = "test"
    param(BooleanKClass)
}
```

**解决方案**

这是一个安全异常，请检查你设置的条件，使用相关工具查看所在 `KClass` 中的字节码对象特征，并再试一次。

###### exception

::: danger loggerE

The number of VagueKotlin must be at least less than the count of paramTypes

:::

**异常原因**

在 `KFunction`、`KFunction Constructor` 查找条件中错误地使用了 `VagueKotlin`。

> 示例如下

```kotlin
TargetClass.function {
    name = "test"
    // <情景1>
    param(VagueKotlin)
    // <情景2>
    param(VagueKotlin, VagueKotlin ...)
}
```

**解决方案**

`VagueKotlin` 不能在方法、构造方法参数中完全填充，若存在这样的需求请使用 `paramCount`。

###### exception

::: danger loggerE

Property match type class is not found

:::

**异常原因**

在查找变量时所设置的查找条件中 `type` 的 `KClass` 实例未被找到。

> 示例如下

```kotlin
property {
    name = "test"
    // 假设这里设置的 type 的 KClass 并不存在
    type = "com.example.TestClass"
}
```

**解决方案**

请检查查找条件中 `type` 的 `KClass` 是否存在，然后再试一次。

###### exception

::: danger loggerE

Function match returnType class is not found

:::

**异常原因**

在查找方法时所设置的查找条件中 `returnType` 的 `KClass` 实例未被找到。

> 示例如下

```kotlin
function {
    name = "test"
    // 假设这里设置的 returnType 的 KClass 并不存在
    returnType = "com.example.TestClass"
}
```

**解决方案**

请检查查找条件中 `returnType` 的 `KClass` 是否存在，然后再试一次。

###### exception

::: danger loggerE

KFunction/KConstructor match paramType\[**INDEX**\] class is not found

:::

**异常原因**

在查找方法、构造方法时所设置的查找条件中 `param` 的 `index` 号下标的 `KClass` 实例未被找到。

```kotlin
function {
    name = "test"
    // 假设这里设置的 1 号下标的 KClass 并不存在
    param(StringKClass, "com.example.TestClass", BooleanKClass)
}
```

**解决方案**

请检查查找条件中 `param` 的 `index` 号下标的 `KClass` 是否存在，然后再试一次。

## 阻断异常

> 这些异常会直接导致 APP 停止运行 (FC)，同时会在控制台打印 `E` 级别的日志。

###### exception

::: danger NoClassDefFoundError

Can't find this Class in \[**CLASSLOADER**\]: **CONTENT** Generated by KYukiReflection#KReflectionTool

:::

**异常原因**

通过 `String.toKClass(...)` 或 `kclassOf<...>()` 找不到需要查找的 `Class` 对象。

> 示例如下

```kotlin
"com.demo.Test".toKClass()
```

**解决方案**

请检查当前字符串或实体匹配到的 `KClass` 是否存在于当前 `ClassLoader`，并再试一次。

###### exception

::: danger IllegalStateException 

VariousClass match failed of those **CLASSES**

:::

**异常原因**

在使用 `VariousClass` 创建不确定的 `KClass` 对象时全部的 `KClass` 都没有被找到。

**解决方案**

检查当前 APP 内是否存在其中能够匹配的 `KClass` 后，再试一次。

###### exception

::: danger IllegalStateException 

paramTypes is empty, please use emptyParam() instead

:::

**异常原因**

在查找方法、构造方法时保留了空的 `param` 方法。

> 示例如下

```kotlin
function {
    name = "test"
    // 括号内没有填写任何参数
    param()
}
```

**解决方案**

若要标识此方法、构造方法没有参数，你可以有如下设置方法。

第一种，设置 `emptyParam` (推荐)

> 示例如下

```kotlin
function {
    name = "test"
    emptyParam()
}
```

第二种，设置 `paramCount = 0`

> 示例如下

```kotlin
function {
    name = "test"
    paramCount = 0
}
```

###### exception

::: danger IllegalStateException 

Target Class type cannot cast to **TYPE**

:::

**异常原因**

使用 `KClass.toKClass`、`KClass.toKClassOrNull`、`KGenericClass.argument` 方法将字符串类名转换为目标 `KClass` 时声明了错误的类型。

以下使用 `KClass.toKClass` 方法来进行示例。

> 示例如下

```kotlin
// 假设目标类型是 Activity 但是被错误地转换为了 WrongClass 类型
val clazz = "android.app.Activity".toKClass<WrongClass>()
```

**解决方案**

> 示例如下

```kotlin
// <解决方案 1> 填写正确的类型
val clazz1 = "android.app.Activity".toKClass<Activity>()
// <解决方案 2> 不填写泛型声明
val clazz2 = "android.app.Activity".toKClass()
```

请确保执行方法后声明的泛型是指定的目标 `KClass` 类型，在不确定目标类型的情况下你可以不需要填写泛型声明。