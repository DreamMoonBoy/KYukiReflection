---
pageClass: code-page
---

# KVariousClass <span class="symbol">- class</span>

```kotlin:no-line-numbers
class KVariousClass(private vararg val name: String)
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 这是一个不确定性 `KClass` 类名装载器，通过 `name` 装载 `KClass` 名称数组。

## get <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun get(loader: ClassLoader? = null, initialize: Boolean): KClass<*>
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 获取匹配的实体类。

使用当前 `loader` 装载目标 `Class`。

## getOrNull <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun getOrNull(loader: ClassLoader? = null, initialize: Boolean): KClass<*>?
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 获取匹配的实体类。

使用当前 `loader` 装载目标 `KClass`。

匹配不到 `KClass` 会返回 `null`，不会抛出异常。