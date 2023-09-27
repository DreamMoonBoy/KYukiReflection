# 功能介绍

> 这里包含了 `YukiReflection` 全部核心功能的用法示例。

## Class 扩展

> 这里是 **Class** 对象自身相关的扩展功能。

### 对象转换

假设我们要得到一个不能直接调用的 `Class`，通常情况下，我们可以使用标准的反射 API 去查找这个 `Class`。

> 示例如下

```kotlin
// 默认 ClassLoader 环境下的 Class
var instance = Class.forName("com.demo.Test")
// 指定 ClassLoader 环境下的 Class
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
var instance = customClassLoader?.loadClass("com.demo.Test")
```

这种写法大概不是很友好，此时 `YukiReflection` 就为你提供了一个可在任意地方使用的语法糖。

以上写法换做 `YukiReflection` 可写作如下形式。

> 示例如下

```kotlin
// 直接得到这个 Class
var instance = "com.demo.Test".toClass()
// 自定义 Class 所在的 ClassLoader
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
var instance = "com.demo.Test".toClass(customClassLoader)
```

如果当前 `Class` 并不存在，使用上述方法会抛出异常，如果你不确定 `Class` 是否存在，可以参考下面的解决方案。

> 示例如下

```kotlin
// 直接得到这个 Class
// 得不到时结果会为 null 但不会抛出异常
var instance = "com.demo.Test".toClassOrNull()
// 自定义 Class 所在的 ClassLoader
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
// 得不到时结果会为 null 但不会抛出异常
var instance = "com.demo.Test".toClassOrNull(customClassLoader)
```

我们还可以通过映射来得到一个存在的 `Class` 对象。

> 示例如下

```kotlin
// 假设这个 Class 是能够被直接得到的
var instance = classOf<Test>()
// 我们同样可以自定义 Class 所在的 ClassLoader，这对于 stub 来说非常有效
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
var instance = classOf<Test>(customClassLoader)
```

::: tip

更多功能请参考 [classOf](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#classof-method)、[String.toClass](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#string-toclass-ext-method)、[String.toClassOrNull](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#string-toclassornull-ext-method) 方法。

:::

### 存在判断

假设我们要判断一个 `Class` 是否存在，通常情况下，我们可以使用标准的反射 API 去查找这个 `Class` 通过异常来判断是否存在。

> 示例如下

```kotlin
// 默认 ClassLoader 环境下的 Class
var isExist = try {
    Class.forName("com.demo.Test")
    true
} catch (_: Throwable) {
    false
}
// 指定 ClassLoader 环境下的 Class
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
var isExist = try {
    customClassLoader?.loadClass("com.demo.Test")
    true
} catch (_: Throwable) {
    false
}
```

这种写法大概不是很友好，此时 `YukiReflection` 就为你提供了一个可在任意地方使用的语法糖。

以上写法换做 `YukiReflection` 可写作如下形式。

> 示例如下

```kotlin
// 判断这个 Class 是否存在
var isExist = "com.demo.Test".hasClass()
// 自定义 Class 所在的 ClassLoader
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
var isExist = "com.demo.Test".hasClass(customClassLoader)
```

::: tip

更多功能请参考 [String.hasClass](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#string-hasclass-ext-method) 方法。

:::

### 模糊查找&ensp;<Badge type="tip" text="Beta" vertical="middle" />

在 R8 等工具混淆后的当前 APP **Dex** 中的 `Class` 名称将会难以分辨，且不确定其正确位置，不能直接通过 [对象转换](#对象转换) 来得到。

此时就有了 `DexClassFinder`，它的作用是通过需要查找的 `Class` 中的字节码特征来确定这个 `Class` 的实例。

::: warning

**此功能仅适用于 Android 平台。**

目前 **DexClassFinder** 的功能尚在试验阶段，由于仅通过 Java 层实现查找功能，在当前 APP **Class** 过多时性能可能不能达到最佳水平，如果发生查找不到、定位有误的问题欢迎向我们反馈。

由于是反射层面的 API，目前它只能通过**类与成员**的特征来定位指定的 **Class**，不能通过指定字节码中的字符串和方法内容特征来进行定位。

查找 **Class** 的速度取决于当前设备的性能，目前主流的移动端处理器在 **10~15w** 数量的 **Class** 中条件不算复杂的情况下大概在 **3~10s** 区间，条件稍微复杂的情况下最快速度能达到 **25s** 以内，匹配到的同类型 **Class** 越多速度越慢。

:::

::: danger

在 **YukiHookAPI** 发布 2.x.x 版本后，此功能将被标记为作废，且将会直接从 **YukiReflection** 中移除。

我们欢迎各位开发者开始使用 [DexKit](https://github.com/LuckyPray/DexKit)，它是一个使用 C++ 实现的 **Dex** 高性能运行时解析库，在性能方面比 Java 层更加高效与优秀，目前尚在开发阶段，欢迎提出宝贵建议。

:::

#### 开始使用

下面是一个简单的用法示例。

假设下面这个 `Class` 是我们想要得到的，其中的名称经过了混淆，在每个版本可能都不一样。

> 示例如下

```java:no-line-numbers
package com.demo;

public class a extends Activity implements Serializable {

    public a(String var1) {
        // ...
    }

    private String a;

    private String b;

    private boolean a;

    protected void onCreate(Bundle var1) {
        // ...
    }

    private static void a(String var1) {
        // ...
    }

    private String a(boolean var1, String var2) {
        // ...
    }

    private void a() {
        // ...
    }

    public void a(boolean var1, a var2, b var3, String var4) {
        // ...
    }
}
```

此时，我们想得到这个 `Class`，可以直接使用 `ClassLoader.searchClass` 方法。

下方演示的条件中每一个都是可选的，条件越复杂定位越精确，同时性能也会越差。

> 示例如下

```kotlin
searchClass {
    // 从指定的包名范围开始查找，实际使用时，你可以同时指定多个包名范围
    from("com.demo")
    // 指定当前 Class 的 getSimpleName 的结果，你可以直接对这个字符串进行逻辑判断
    // 这里我们不确定它的名称是不是 a，可以只判断字符串长度
    simpleName { it.length == 1 }
    // 指定继承的父类对象，如果是存在的 stub，可以直接用泛型表示
    extends<Activity>()
    // 指定继承的父类对象，可以直接写为完整类名，你还可以同时指定多个
    extends("android.app.Activity")
    // 指定实现的接口，如果是存在的 stub，可以直接用泛型表示
    implements<Serializable>()
    // 指定实现的接口，可以直接写为完整类名，你还可以同时指定多个
    implements("java.io.Serializable")
    // 指定构造方法的类型与样式，以及在当前类中存在的个数 count
    constructor { param(StringClass) }.count(num = 1)
    // 指定变量的类型与样式，以及在当前类中存在的个数 count
    field { type = StringClass }.count(num = 2)
    // 指定变量的类型与样式，以及在当前类中存在的个数 count
    field { type = BooleanType }.count(num = 1)
    // 直接指定所有变量在当前类中存在的个数 count
    field().count(num = 3)
    // 如果你认为变量的个数是不确定的，还可以使用如下自定义条件
    field().count(1..3)
    field().count { it >= 3 }
    // 指定方法的类型与样式，以及在当前类中存在的个数 count
    method {
        name = "onCreate"
        param(BundleClass)
    }.count(num = 1)
    // 指定方法的类型与样式，同时指定修饰符，以及在当前类中存在的个数 count
    method {
        modifiers { isStatic && isPrivate }
        param(StringClass)
        returnType = UnitType
    }.count(num = 1)
    // 指定方法的类型与样式，同时指定修饰符，以及在当前类中存在的个数 count
    method {
        modifiers { isPrivate && isStatic.not() }
        param(BooleanType, StringClass)
        returnType = StringClass
    }.count(num = 1)
    // 指定方法的类型与样式，同时指定修饰符，以及在当前类中存在的个数 count
    method {
        modifiers { isPrivate && isStatic.not() }
        emptyParam()
        returnType = UnitType
    }.count(num = 1)
    // 指定方法的类型与样式，同时指定修饰符和模糊类型 VagueType，以及在当前类中存在的个数 count
    method {
        modifiers { isPrivate && isStatic.not() }
        param(BooleanType, VagueType, VagueType, StringClass)
        returnType = UnitType
    }.count(num = 1)
    // 直接指定所有方法在当前类中存在的个数 count
    method().count(num = 5)
    // 如果你认为方法的个数是不确定的，还可以使用如下自定义条件
    method().count(1..5)
    method().count { it >= 5 }
    // 直接指定所有成员 (Member) 在当前类中存在的个数 count
    // 成员包括：Field (变量)、Method (方法)、Constructor (构造方法)
    member().count(num = 9)
    // 所有成员中一定存在一个 static 修饰符，可以这样加入此条件
    member {
        modifiers { isStatic }
    }
}.get() // 得到这个 Class 本身的实例，找不到会返回 null
```

::: tip

上述用法中对于 **Field**、**Method**、**Constructor** 的条件用法与 [Member 扩展](#member-扩展) 中的相关用法是一致的，仅有小部分区别。

更多功能请参考 [MemberRules](../api/public/com/highcapable/yukireflection/finder/classes/rules/MemberRules)、[FieldRules](../api/public/com/highcapable/yukireflection/finder/classes/rules/FieldRules)、[MethodRules](../api/public/com/highcapable/yukireflection/finder/classes/rules/MethodRules)、[ConstructorRules](../api/public/com/highcapable/yukireflection/finder/classes/rules/ConstructorRules)。

:::

#### 异步查找

默认情况下 `DexClassFinder` 会使用同步方式查找 `Class`，会阻塞当前线程直到找到或找不到发生异常为止，若查找消耗的时间过长，可能会导致当前 APP 发生 **ANR** 问题。

针对上述问题，我们可以启用异步，只需要加入参数 `async = true`，这将不需要你再次启动一个线程，API 已帮你处理好相关问题。

::: warning

若要使用此功能，你需要在方法参数首位传入当前 APP 的 **Context**。

对于异步情况下你需要使用 **wait** 方法来得到结果，**get** 方法将不再起作用。

:::

> 示例如下

```kotlin
val context: Context // 假设这就是当前 APP 的 Context
searchClass(context, async = true) {
    // ...
}.wait { class1 ->
    // 得到异步结果
}
searchClass(context, async = true) {
    // ...
}.wait { class2 ->
    // 得到异步结果
}
```

这样我们的查找过程就是异步运行了，它将不会阻塞主线程，每个查找都将在单独的线程同时进行，可达到并行任务的效果。

#### 本地缓存

由于每次重新打开当前 APP 都会重新进行查找，在当前 APP 版本不变的情况下这是一种重复性能浪费。

此时我们可以通过指定 `name` 参数来对当前 APP 版本的查找结果进行本地缓存，下一次将直接从本地缓存中读取查找到的类名。

本地缓存使用的是 `SharedPreferences`，它将被保存到当前 APP 的数据目录中，在当前 APP 版本更新后会重新进行缓存。

启用本地缓存后，将同时设置 `async = true`，你可以不需要再手动进行设置。

::: warning

若要使用此功能，你需要在方法参数首位传入当前 APP 的 **Context**。

:::

> 示例如下

```kotlin
val context: Context // 假设这就是当前 APP 的 Context
searchClass(context, name = "com.demo.class1") {
    // ...
}.wait { class1 ->
    // 得到异步结果
}
searchClass(context, name = "com.demo.class2") {
    // ...
}.wait { class2 ->
    // 得到异步结果
}
```

如果你想手动清除本地缓存，可以使用如下方法清除当前 APP 版本的缓存。

> 示例如下

```kotlin
val context: Context // 假设这就是当前 APP 的 Context
DexClassFinder.clearCache(context)
```

你还可以清除指定版本的 APP 缓存。

> 示例如下

```kotlin
val context: Context // 假设这就是当前 APP 的 Context
DexClassFinder.clearCache(context, versionName = "1.0", versionCode = 1)
```

#### 多重查找

如果你需要使用固定的条件同时查找一组 `Class`，那么你只需要使用 `all` 或 `waitAll` 方法来得到结果。

```kotlin
// 同步查找，使用 all 得到条件全部查找到的结果
searchClass {
    // ...
}.all().forEach { clazz ->
    // 得到每个结果
}
// 同步查找，使用 all { ... } 遍历每个结果
searchClass {
    // ...
}.all { clazz ->
    // 得到每个结果
}
// 异步查找，使用 waitAll 得到条件全部查找到的结果
val context: Context // 假设这就是当前 APP 的 Context
searchClass(context, async = true) {
    // ...
}.waitAll { classes ->
    classes.forEach {
        // 得到每个结果
    }
}
```

::: tip

更多功能请参考 [ClassLoader.searchClass](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#classloader-searchclass-ext-method) 方法。

:::

## Member 扩展

> 这里是 **Class** 字节码成员变量 **Field**、**Method**、**Constructor** 相关的扩展功能。

::: tip

**Member** 是 **Field**、**Method**、**Constructor** 的接口描述对象，它在 Java 反射中为 **Class** 中字节码成员的总称。

:::

假设有一个这样的 `Class`。

> 示例如下

```java:no-line-numbers
package com.demo;

public class BaseTest {

    public BaseTest() {
        // ...
    }

    public BaseTest(boolean isInit) {
        // ...
    }

    private void doBaseTask(String taskName) {
        // ...
    }
}
```

```java:no-line-numbers
package com.demo;

public class Test extends BaseTest {

    public Test() {
        // ...
    }

    public Test(boolean isInit) {
        // ...
    }

    private static TAG = "Test";

    private BaseTest baseInstance;

    private String a;

    private boolean a;

    private boolean isTaskRunning = false;

    private static void init() {
        // ...
    }

    private void doTask(String taskName) {
        // ...
    }

    private void release(String taskName, Function<boolean, String> task, boolean isFinish) {
        // ...
    }

    private void stop() {
        // ...
    }

    private String getName() {
        // ...
    }

    private void b() {
        // ...
    }

    private void b(String a) {
        // ...
    }
}
```

### 查找与反射调用

假设我们要得到 `Test`(以下统称“当前 `Class`”)的 `doTask` 方法并执行，通常情况下，我们可以使用标准的反射 API 去查找这个方法。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用反射 API 调用并执行
Test::class.java
    .getDeclaredMethod("doTask", String::class.java)
    .apply { isAccessible = true }
    .invoke(instance, "task_name")
```

这种写法大概不是很友好，此时 `YukiReflection` 就为你提供了一个可在任意地方使用的语法糖。

以上写法换做 `YukiReflection` 可写作如下形式。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "doTask"
    param(StringClass)
}.get(instance).call("task_name")
```

::: tip

更多功能请参考 [MethodFinder](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder)。

:::

同样地，我们需要得到 `isTaskRunning` 变量也可以写作如下形式。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.field {
    name = "isTaskRunning"
    type = BooleanType
}.get(instance).any() // any 为 Field 的任意类型实例化对象
```

::: tip

更多功能请参考 [FieldFinder](../api/public/com/highcapable/yukireflection/finder/members/FieldFinder)。

:::

也许你还想得到当前 `Class` 的构造方法，同样可以实现。

> 示例如下

```kotlin
Test::class.java.constructor {
    param(BooleanType)
}.get().call(true) // 可创建一个新的实例
```

若想得到的是 `Class` 的无参构造方法，可写作如下形式。

> 示例如下

```kotlin
Test::class.java.constructor().get().call() // 可创建一个新的实例
```

::: tip

更多功能请参考 [ConstructorFinder](../api/public/com/highcapable/yukireflection/finder/members/ConstructorFinder)。

:::

### 可选的查找条件

假设我们要得到 `Class` 中的 `getName` 方法，可以使用如下实现。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "getName"
    emptyParam()
    returnType = StringClass
}.get(instance).string() // 得到方法的结果
```

通过观察发现，这个 `Class` 中只有一个名为 `getName` 的方法，那我们可不可以再简单一点呢？

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "getName"
    emptyParam()
}.get(instance).string() // 得到方法的结果
```

是的，对于确切不会变化的方法，你可以精简查找条件。

在只使用 `get` 或 `wait` 方法得到结果时 `YukiReflection` **会默认按照字节码顺序匹配第一个查找到的结果**。

问题又来了，这个 `Class` 中有一个 `release` 方法，但是它的方法参数很长，而且部分类型可能无法直接得到。

通常情况下我们会使用 `param(...)` 来查找这个方法，但是有没有更简单的方法呢。

此时，在确定方法唯一性后，你可以使用 `paramCount` 来查找到这个方法。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "release"
    // 此时我们不必确定方法参数具体类型，写个数就好
    paramCount = 3
}.get(instance) // 得到这个方法
```

上述示例虽然能够匹配成功，但是不精确，此时你还可以使用 `VagueType` 来填充你不想填写的方法参数类型。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "release"
    // 使用 VagueType 来填充不想填写的类型，同时保证其它类型能够匹配
    param(StringClass, VagueType, BooleanType)
}.get(instance) // 得到这个方法
```

如果你并不确定每一个参数的类型，你可以通过 `param { ... }` 方法来创建一个条件方法体。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "release"
    // 得到 it (Class) 方法参数类型数组实例来仅判断已知的类型和它的位置
    param { it[0] == StringClass && it[2] == BooleanType }
}.get(instance) // 得到这个方法
```

::: tip

使用 **param { ... }** 创建一个条件方法体，其中的变量 **it** 即当前方法参数的 **Class** 类型数组实例，此时你就可以自由使用 **Class** 中的所有对象及其方法。

方法体末尾条件需要返回一个 **Boolean**，即最终的条件判断结果。

更多功能请参考 [FieldFinder.type](../api/public/com/highcapable/yukireflection/finder/members/FieldFinder#type-method-1)、[MethodFinder.param](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#param-method-1)、[MethodFinder.returnType](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#returntype-method-1)、[ConstructorFinder.param](../api/public/com/highcapable/yukireflection/finder/members/ConstructorFinder#param-method-1) 方法。

:::

### 在父类查找

你会注意到 `Test` 继承于 `BaseTest`，现在我们想得到 `BaseTest` 的 `doBaseTask` 方法，在不知道父类名称的情况下，要怎么做呢？

参照上面的查找条件，我们只需要在查找条件中加入一个 `superClass` 即可实现这个功能。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "doBaseTask"
    param(StringClass)
    // 只需要添加这个条件
    superClass()
}.get(instance).call("task_name")
```

这个时候我们就可以在父类中取到这个方法了。

`superClass` 有一个参数为 `isOnlySuperClass`，设置为 `true` 后，可以跳过当前 `Class` 仅查找当前 `Class` 的父类。

由于我们现在已知 `doBaseTask` 方法只存在于父类，可以加上这个条件节省查找时间。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "doBaseTask"
    param(StringClass)
    // 加入一个查找条件
    superClass(isOnlySuperClass = true)
}.get(instance).call("task_name")
```

这个时候我们同样可以得到父类中的这个方法。

`superClass` 一旦设置就会自动循环向后查找全部继承的父类中是否有这个方法，直到查找到目标没有父类(继承关系为 `java.lang.Object`)为止。

::: tip

更多功能请参考 [MethodFinder.superClass](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#superclass-method)、[ConstructorFinder.superClass](../api/public/com/highcapable/yukireflection/finder/members/ConstructorFinder#superclass-method)、[FieldFinder.superClass](../api/public/com/highcapable/yukireflection/finder/members/FieldFinder#superclass-method) 方法。

:::

::: danger

当前查找的 **Method** 除非指定 **superClass** 条件，否则只能查找到当前 **Class** 的 **Method**，这是 Java 反射 API 的默认行为。

:::

### 模糊查找

如果我们想查找一个方法名称，但是又不确定它在每个版本中是否发生变化，此时我们就可以使用模糊查找功能。

假设我们要得到 `Class` 中的 `doTask` 方法，可以使用如下实现。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name {
        // 设置名称不区分大小写
        it.equals("dotask", isIgnoreCase = true)
    }
    param(StringClass)
}.get(instance).call("task_name")
```

已知当前 `Class` 中仅有一个 `doTask` 方法，我们还可以判断方法名称仅包含其中指定的字符。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name {
        // 仅包含 oTas
        it.contains("oTas")
    }
    param(StringClass)
}.get(instance).call("task_name")
```

我们还可以根据首尾字符串进行判断。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name {
        // 开头包含 do，结尾包含 Task
        it.startsWith("do") && it.endsWith("Task")
    }
    param(StringClass)
}.get(instance).call("task_name")
```

通过观察发现这个方法名称中只包含字母，我们还可以再增加一个精确的查找条件。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name {
        // 开头包含 do，结尾包含 Task，仅包含字母
        it.startsWith("do") && it.endsWith("Task") && it.isOnlyLetters()
    }
    param(StringClass)
}.get(instance).call("task_name")
```

::: tip

使用 **name { ... }** 创建一个条件方法体，其中的变量 **it** 即当前名称的字符串，此时你就可以在 **NameRules** 的扩展方法中自由使用其中的功能。

方法体末尾条件需要返回一个 **Boolean**，即最终的条件判断结果。

更多功能请参考 [FieldFinder.name](../api/public/com/highcapable/yukireflection/finder/members/FieldFinder#name-method-1)、[MethodFinder.name](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#name-method-1) 方法以及 [NameRules](../api/public/com/highcapable/yukireflection/finder/base/rules/NameRules)。

:::

### 多重查找

有些时候，我们可能需要查找一个 `Class` 中具有相同特征的一组方法、构造方法、变量，此时，我们就可以利用相对条件匹配来完成。

在查找条件结果的基础上，我们只需要把 `get` 换为 `all` 即可得到匹配条件的全部字节码。

假设这次我们要得到 `Class` 中方法参数个数范围在 `1..3` 的全部方法，可以使用如下实现。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    paramCount(1..3)
}.all(instance).forEach { instance ->
    // 调用执行每个方法
    instance.call(...)
}
```

上述示例可完美匹配到如下 3 个方法。

`private void doTask(String taskName)`

`private void release(String taskName, Function<boolean, String> task, boolean isFinish)`

`private void b(String a)`

如果你想更加自由地定义参数个数范围的条件，可以使用如下实现。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    paramCount { it < 3 }
}.all(instance).forEach { instance ->
    // 调用执行每个方法
    instance.call(...)
}
```

上述示例可完美匹配到如下 6 个方法。

`private static void init()`

`private void doTask(String taskName)`

`private void stop(String a)`

`private void getName(String a)`

`private void b()`

`private void b(String a)`

通过观察 `Class` 中有两个名称为 `b` 的方法，可以使用如下实现。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "b"
}.all(instance).forEach { instance ->
    // 调用执行每个方法
    instance.call(...)
}
```

上述示例可完美匹配到如下 2 个方法。

`private void b()`

`private void b(String a)`

::: tip

使用 **paramCount { ... }** 创建一个条件方法体，其中的变量 **it** 即当前参数个数的整数，此时你就可以在 **CountRules** 的扩展方法中自由使用其中的功能。

方法体末尾条件需要返回一个 **Boolean**，即最终的条件判断结果。

更多功能请参考 [MethodFinder.paramCount](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#paramcount-method-2)、[ConstructorFinder.paramCount](../api/public/com/highcapable/yukireflection/finder/members/ConstructorFinder#paramcount-method-2) 方法以及 [CountRules](../api/public/com/highcapable/yukireflection/finder/base/rules/CountRules)。

:::

### 静态字节码

有些方法和变量在 `Class` 中是静态的实现，这个时候，我们不需要传入实例就可以调用它们。

假设我们这次要得到静态变量 `TAG` 的内容。

> 示例如下

```kotlin
Test::class.java.field {
    name = "TAG"
    type = StringClass
}.get().string() // Field 的类型是字符串，可直接进行 cast
```

假设 `Class` 中存在同名的非静态 `TAG` 变量，这个时候怎么办呢？

加入一个筛选条件即可。

> 示例如下

```kotlin
Test::class.java.field {
    name = "TAG"
    type = StringClass
    // 标识查找的这个变量需要是静态
    modifiers { isStatic }
}.get().string() // Field 的类型是字符串，可直接进行 cast
```

我们还可以调用名为 `init` 的静态方法。

> 示例如下

```kotlin
Test::class.java.method {
    name = "init"
    emptyParam()
}.get().call()
```

同样地，你可以标识它是一个静态。

> 示例如下

```kotlin
Test::class.java.method {
    name = "init"
    emptyParam()
    // 标识查找的这个方法需要是静态
    modifiers { isStatic }
}.get().call()
```

::: tip

使用 **modifiers { ... }** 创建一个条件方法体，此时你就可以在 **ModifierRules** 中自由使用其中的功能。

方法体末尾条件需要返回一个 **Boolean**，即最终的条件判断结果。

更多功能请参考 [FieldFinder.modifiers](../api/public/com/highcapable/yukireflection/finder/members/FieldFinder#modifiers-method)、[MethodFinder.modifiers](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#modifiers-method)、[ConstructorFinder.modifiers](../api/public/com/highcapable/yukireflection/finder/members/ConstructorFinder#modifiers-method) 方法以及 [ModifierRules](../api/public/com/highcapable/yukireflection/finder/base/rules/ModifierRules)。

:::

### 混淆的字节码

你可能已经注意到了，这里给出的示例 `Class` 中有两个混淆的变量名称，它们都是 `a`，这个时候我们要怎么得到它们呢？

有两种方案。

第一种方案，确定变量的名称和类型。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.field {
    name = "a"
    type = BooleanType
}.get(instance).any() // 得到名称为 a 类型为 Boolean 的变量
```

第二种方案，确定变量的类型所在的位置。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.field {
    type(BooleanType).index().first()
}.get(instance).any() // 得到第一个类型为 Boolean 的变量
```

以上两种情况均可得到对应的变量 `private boolean a`。

同样地，这个 `Class` 中也有两个混淆的方法名称，它们都是 `b`。

你也可以有两种方案来得到它们。

第一种方案，确定方法的名称和方法参数。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "b"
    param(StringClass)
}.get(instance).call("test_string") // 得到名称为 b 方法参数为 [String] 的方法
```

第二种方案，确定方法的参数所在的位置。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    param(StringClass).index().first()
}.get(instance).call("test_string") // 得到第一个方法参数为 [String] 的方法
```

由于观察到这个方法在 `Class` 的最后一个，那我们还有一个备选方案。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    order().index().last()
}.get(instance).call("test_string") // 得到当前 Class 的最后一个方法
```

::: warning

请尽量避免使用 **order** 来筛选字节码的下标，它们可能是不确定的，除非你确定它在这个 **Class** 中的位置一定不会变。

:::

### 直接调用

上面介绍的调用字节码的方法都需要使用 `get(instance)` 才能调用对应的方法，有没有简单一点的办法呢？

此时，你可以在任意实例上使用 `current` 方法来创建一个调用空间。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 假设这个 Class 是不能被直接得到的
instance.current {
    // 执行 doTask 方法
    method {
        name = "doTask"
        param(StringClass)
    }.call("task_name")
    // 执行 stop 方法
    method {
        name = "stop"
        emptyParam()
    }.call()
    // 得到 name
    val name = method { name = "getName" }.string()
}
```

我们还可以用 `superClass` 调用当前 `Class` 父类的方法。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 假设这个 Class 是不能被直接得到的
instance.current {
    // 执行父类的 doBaseTask 方法
    superClass().method {
        name = "doBaseTask"
        param(StringClass)
    }.call("task_name")
}
```

如果你不喜欢使用一个大括号的调用域来创建当前实例的命名空间，你可以直接使用 `current()` 方法。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例，这个 Class 是不能被直接得到的
val instance = Test()
// 执行 doTask 方法
instance
    .current()
    .method {
        name = "doTask"
        param(StringClass)
    }.call("task_name")
// 执行 stop 方法
instance
    .current()
    .method {
        name = "stop"
        emptyParam()
    }.call()
// 得到 name
val name = instance.current().method { name = "getName" }.string()
```

同样地，它们之间可以连续调用，但<u>**不允许内联调用**</u>。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 假设这个 Class 是不能被直接得到的
instance.current {
    method {
        name = "doTask"
        param(StringClass)
    }.call("task_name")
}.current()
    .method {
        name = "stop"
        emptyParam()
    }.call()
// 注意，因为 current() 返回的是 CurrentClass 自身对象，所以不能像下面这样调用
instance.current().current()
```

针对 `Field` 实例，还有一个便捷的方法，可以直接获取 `Field` 所在实例的对象。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 假设这个 Class 是不能被直接得到的
instance.current {
    // <方案1>
    field {
        name = "baseInstance"
    }.current {
        method {
            name = "doBaseTask"
            param(StringClass)
        }.call("task_name")
    }
    // <方案2>
    field {
        name = "baseInstance"
    }.current()
        ?.method {
            name = "doBaseTask"
            param(StringClass)
        }?.call("task_name")
}
```

::: warning

上述 **current** 方法相当于帮你调用了 **CurrentClass** 中的 **field { ... }.any()?.current()** 方法。

若不存在 **CurrentClass** 调用域，你需要使用 **field { ... }.get(instance).current()** 来进行调用。

:::

问题又来了，我想使用反射的方式创建如下的实例并调用其中的方法，该怎么做呢？

> 示例如下

```kotlin
Test(true).doTask("task_name")
```

通常情况下，我们可以使用标准的反射 API 来调用。

> 示例如下

```kotlin
"com.demo.Test".toClass()
    .getDeclaredConstructor(Boolean::class.java)
    .apply { isAccessible = true }
    .newInstance(true)
    .apply {
        javaClass
            .getDeclaredMethod("doTask", String::class.java)
            .apply { isAccessible = true }
            .invoke(this, "task_name")
    }
```

但是感觉这种做法好麻烦，有没有更简洁的调用方法呢？

这个时候，我们还可以借助 `buildOf` 方法来创建一个实例。

> 示例如下

```kotlin
"com.demo.Test".toClass().buildOf(true) { param(BooleanType) }?.current {
    method {
        name = "doTask"
        param(StringClass)
    }.call("task_name")
}
```

若你希望 `buildOf` 方法返回当前实例的类型，你可以在其中加入类型泛型声明，而无需使用 `as` 来 `cast` 目标类型。

这种情况多用于实例本身的构造方法是私有的，但是里面的方法是公有的，这样我们只需要对其构造方法进行反射创建即可。

> 示例如下

```kotlin
// 假设这个 Class 是能够直接被得到的
val test = Test::class.java.buildOf<Test>(true) { param(BooleanType) }
test.doTask("task_name")
```

::: tip

更多功能请参考 [CurrentClass](../api/public/com/highcapable/yukireflection/bean/CurrentClass) 以及 [Class.buildOf](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#class-buildof-ext-method) 方法。

:::

### 再次查找

假设有三个不同版本的 `Class`，它们都是这个 APP 不同版本相同的 `Class`。

这里面同样都有一个方法 `doTask`，假设它们的功能是一样的。

> 版本 A 示例如下

```java:no-line-numbers
public class Test {

    public void doTask() {
        // ...
    }
}
```

> 版本 B 示例如下

```java:no-line-numbers
public class Test {

    public void doTask(String taskName) {
        // ...
    }
}
```

> 版本 C 示例如下

```java:no-line-numbers
public class Test {

    public void doTask(String taskName, int type) {
        // ...
    }
}
```

我们需要在不同的版本中得到这个相同功能的 `doTask` 方法，要怎么做呢？

此时，你可以使用 `RemedyPlan` 完成你的需求。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "doTask"
    emptyParam()
}.remedys {
    method {
        name = "doTask"
        param(StringClass)
    }.onFind {
        // 可在这里实现找到的逻辑
    }
    method {
        name = "doTask"
        param(StringClass, IntType)
    }.onFind {
        // 可在这里实现找到的逻辑
    }
}.wait(instance) {
    // 得到方法的结果
}
```

::: danger

使用了 **RemedyPlan** 的方法查找结果不能再使用 **get** 的方式得到方法实例，应当使用 **wait** 方法。

:::

另外，你还可以在使用 [多重查找](#多重查找) 的情况下继续使用 `RemedyPlan`。

> 示例如下

```kotlin
// 假设这就是这个 Class 的实例
val instance = Test()
// 使用 YukiReflection 调用并执行
Test::class.java.method {
    name = "doTask"
    emptyParam()
}.remedys {
    method {
        name = "doTask"
        paramCount(0..1)
    }.onFind {
        // 可在这里实现找到的逻辑
    }
    method {
        name = "doTask"
        paramCount(1..2)
    }.onFind {
        // 可在这里实现找到的逻辑
    }
}.waitAll(instance) {
    // 得到方法的结果
}
```

::: tip

更多功能请参考 [MethodFinder.RemedyPlan](../api/public/com/highcapable/yukireflection/finder/members/MethodFinder#remedyplan-class)、[ConstructorFinder.RemedyPlan](../api/public/com/highcapable/yukireflection/finder/members/ConstructorFinder#remedyplan-class)、[FieldFinder.RemedyPlan](../api/public/com/highcapable/yukireflection/finder/members/FieldFinder#remedyplan-class)。

:::

### 相对匹配

假设当前 APP 中不同版本中存在功能相同的 `Class` 但仅有 `Class` 的名称不一样。

> 版本 A 示例如下

```java:no-line-numbers
public class ATest {

    public static void doTask() {
        // ...
    }
}
```

> 版本 B 示例如下

```java:no-line-numbers
public class BTest {

    public static void doTask() {
        // ...
    }
}
```

这个时候我们想在每个版本都调用这个 `Class` 里的 `doTask` 方法该怎么做呢？

通常做法是判断 `Class` 是否存在。

> 示例如下

```kotlin
// 首先查找到这个 Class
val currentClass = 
    if("com.demo.ATest".hasClass()) "com.demo.ATest".toClass() else "com.demo.BTest".toClass()
// 然后再查找这个方法并调用
currentClass.method {
    name = "doTask"
    emptyParam()
}.get().call()
```

感觉这种方案非常的不优雅且繁琐，那么此时 `YukiReflection` 就为你提供了一个非常方便的 `VariousClass` 专门来解决这个问题。

现在，你可以直接使用以下方式获取到这个 `Class`。

> 示例如下

```kotlin
VariousClass("com.demo.ATest", "com.demo.BTest").get().method {
    name = "doTask"
    emptyParam()
}.get().call()
```

若当前 `Class` 在指定的 `ClassLoader` 中存在，你可以在 `get` 中填入你的 `ClassLoader`。

> 示例如下

```kotlin
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
VariousClass("com.demo.ATest", "com.demo.BTest").get(customClassLoader).method {
    name = "doTask"
    emptyParam()
}.get().call()
```

若你不确定所有的 `Class` 一定会被匹配到，你可以使用 `getOrNull` 方法。

> 示例如下

```kotlin
val customClassLoader: ClassLoader? = ... // 假设这个就是你的 ClassLoader
VariousClass("com.demo.ATest", "com.demo.BTest").getOrNull(customClassLoader)?.method {
    name = "doTask"
    emptyParam()
}?.get()?.call()
```

::: tip

更多功能请参考 [VariousClass](../api/public/com/highcapable/yukireflection/bean/VariousClass)。

:::

### 调用泛型

在反射过程中，我们可能会遇到泛型问题，在泛型的反射处理上，`YukiReflection` 同样提供了一个可在任意地方使用的语法糖。

例如我们有如下的泛型类。

> 示例如下

```kotlin
class TestGeneric<T, R> (t: T, r: R) {

    fun foo() {
        // ...
    }
}
```

当我们想在当前 `Class` 中获得泛型 `T` 或 `R` 的 `Class` 实例，只需要如下实现。

> 示例如下

```kotlin
class TestGeneric<T, R> (t: T, r: R) {

    fun foo() {
        // 获得当前实例的操作对象
        // 获得 T 的 Class 实例，在参数第 0 位，默认值可以不写
        val tClass = current().generic()?.argument()
        // 获得 R 的 Class 实例，在参数第 1 位
        val rClass = current().generic()?.argument(index = 1)
        // 你还可以使用如下写法
        current().generic {
             // 获得 T 的 Class 实例，在参数第 0 位，默认值可以不写
            val tClass = argument()
            // 获得 R 的 Class 实例，在参数第 1 位
            val rClass = argument(index = 1)
        }
    }
}
```

当我们想在外部调用这个 `Class` 时，就可以有如下实现。

> 示例如下

```kotlin
// 假设这个就是 T 的 Class
class TI {

    fun foo() {
        // ...
    }
}
// 假设这个就是 T 的实例
val tInstance: TI? = ...
// 获得 T 的 Class 实例，在参数第 0 位，默认值可以不写，并获得其中的方法 foo 并调用
TestGeneric::class.java.generic()?.argument()?.method {
    name = "foo"
    emptyParam()
}?.get(tInstance)?.invoke<TI>()
```

::: tip

更多功能请参考 [CurrentClass.generic](../api/public/com/highcapable/yukireflection/bean/CurrentClass#generic-method)、[Class.generic](../api/public/com/highcapable/yukireflection/factory/ReflectionFactory#class-generic-ext-method) 方法以及 [GenericClass](../api/public/com/highcapable/yukireflection/bean/GenericClass)。

:::

### 注意误区

> 这里列举了使用时可能会遇到的误区部分，可供参考。

#### 限制性查找条件

::: danger

在查找条件中，除了 **order** 你只能使用一次 **index** 功能。

:::

> 示例如下

```kotlin
method {
    name = "test"
    param(BooleanType).index(num = 2)
    // 错误的使用方法，请仅保留一个 index 方法
    returnType(StringClass).index(num = 1)
}
```

以下查找条件的使用是没有任何问题的。

> 示例如下

```kotlin
method {
    name = "test"
    param(BooleanType).index(num = 2)
    order().index(num = 1)
}
```

#### 必要的查找条件

::: danger

在普通方法查找条件中，<u>**即使是无参的方法也需要设置查找条件**</u>。

:::

假设我们有如下的 `Class`。

> 示例如下

```java:no-line-numbers
public class TestFoo {

    public void foo(String string) {
        // ...
    }

    public void foo() {
        // ...
    }
}
```

我们要得到其中的 `public void foo()` 方法，可以写作如下形式。

> 示例如下

```kotlin
TestFoo::class.java.method {
    name = "foo"
}
```

但是，上面的例子<u>**是错误的**</u>。

你会发现这个 `Class` 中有两个 `foo` 方法，其中一个带有方法参数。

由于上述例子没有设置 `param` 的查找条件，得到的结果将会是匹配名称且匹配字节码顺序的第一个方法 `public void foo(String string)`，而不是我们需要的最后一个方法。

这是一个**经常会出现的错误**，**没有方法参数就会丢失方法参数查找条件**的使用问题。

正确的使用方法如下。

> 示例如下

```kotlin
TestFoo::class.java.method {
    name = "foo"
    // ✅ 正确的使用方法，添加详细的筛选条件
    emptyParam()
}
```

至此，上述的示例将可以完美地匹配到 `public void foo()` 方法。

#### 可简写查找条件

> 在构造方法查找条件中，<u>**无参的构造方法可以不需要填写查找条件**</u>。

假设我们有如下的 `Class`。

> 示例如下

```java:no-line-numbers
public class TestFoo {

    public TestFoo() {
        // ...
    }
}
```

我们要得到其中的 `public TestFoo()` 构造方法，可以写作如下形式。

> 示例如下

```kotlin
TestFoo::class.java.constructor { emptyParam() }
```

上面的例子可以成功获取到 `public TestFoo()` 构造方法，但是感觉有一些繁琐。

与普通方法不同，由于构造方法不需要考虑 `name` 名称，当构造方法没有参数的时候，我们可以省略 `emptyParam` 参数。

> 示例如下

```kotlin
TestFoo::class.java.constructor()
```

#### 字节码类型

::: danger

在字节码调用结果中，**cast** 方法只能指定字节码对应的类型。

:::

例如我们想得到一个 `Boolean` 类型的变量，把他转换为 `String`。

以下是错误的使用方法。

> 示例如下

```kotlin
field {
    name = "test"
    type = BooleanType
}.get().string() // 错误的使用方法，必须 cast 为字节码目标类型
```

以下是正确的使用方法。

> 示例如下

```kotlin
field {
    name = "test"
    type = BooleanType
}.get().boolean().toString() // ✅ 正确的使用方法，得到类型后再进行转换
```

## 常用类型扩展

在查找方法、变量的时候我们通常需要指定所查找的类型。

> 示例如下

```kotlin
field {
    name = "test"
    type = Boolean::class.javaPrimitiveType
}
```

在 Kotlin 中表达出 `Boolean::class.javaPrimitiveType` 这个类型的写法很长，感觉并不方便。

因此，`YukiReflection` 为开发者封装了常见的类型调用，其中包含了 Android 的相关类型和 Java 的常见类型与**原始类型关键字**。

这个时候上面的类型就可以写作如下形式了。

> 示例如下

```kotlin
field {
    name = "test"
    type = BooleanType
}
```

在 Java 常见类型中的**原始类型 (或基本类型) 关键字**都已被封装为 **类型 + Type** 的方式，例如 `IntType`、`FloatType` (它们的字节码类型为 `int`、`float`)。

相应地，数组类型也有方便的使用方法，假设我们要获得 `String[]` 类型的数组。

需要写做 `java.lang.reflect.Array.newInstance(String::class.java, 0).javaClass` 才能得到这个类型。

感觉是不是很麻烦，这个时候我们可以使用方法 `ArrayClass(StringClass)` 来得到这个类型。

同时由于 `String` 是常见类型，所以还可以直接使用 `StringArrayClass` 来得到这个类型。

一些常见需求中查找的方法，都有其对应的封装类型以供使用，格式为 **类型 + Class**。

以下是 Java 中一些特例类型在 `YukiReflection` 中的封装名称。

- `void` → `UnitType`

- `java.lang.Void` → `UnitClass`

- `java.lang.Object` → `AnyClass`

- `java.lang.Integer` → `IntClass`

- `java.lang.Character` → `CharClass`

::: warning

以 **类型 + Type** 封装类型会且仅会表示为 Java **原始类型关键字**，由于 Kotlin 中不存在**原始类型**这个概念，所以它们都会被定义为 **KClass**。

Java 中共有 9 个**原始类型关键字**，其中 8 个为**原始类型**，分别为 **boolean**、**char**、**byte**、**short**、**int**、**float**、**long**、**double**，其中 **void** 类型是一个特例。

同时它们都有 Java 自身对应的封装类型，例如 **java.lang.Boolean**、**java.lang.Integer**，这些类型是<u>**不相等的**</u>，请注意区分。

同样地，数组也有对应的封装类型，它们也需要与 Java **原始类型关键字** 进行区分。

例如 **byte[]** 的封装类型为 **ByteArrayType** 或 **ArrayClass(ByteType)**，而 **Byte[]** 的封装类型为 **ByteArrayClass** 或 **ArrayClass(ByteClass)**，这些类型也是<u>**不相等的**</u>。

:::

::: tip

更多类型可查看 [ComponentTypeFactory](../api/public/com/highcapable/yukireflection/type/android/ComponentTypeFactory)、[GraphicsTypeFactory](../api/public/com/highcapable/yukireflection/type/android/GraphicsTypeFactory)、[ViewTypeFactory](../api/public/com/highcapable/yukireflection/type/android/ViewTypeFactory)、[VariableTypeFactory](../api/public/com/highcapable/yukireflection/type/java/VariableTypeFactory)。 

:::

同时，欢迎你能贡献更多的常用类型。