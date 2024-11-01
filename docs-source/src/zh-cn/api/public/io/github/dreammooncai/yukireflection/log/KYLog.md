---
pageClass: code-page
---

# KYLog <span class="symbol">- object</span>

```kotlin:no-line-numbers
object KYLog
```

**变更记录**

`v1.0.0` `新增`

**功能描述**

> 全局 Log 管理类。

## Configs <span class="symbol">- object</span>

```kotlin:no-line-numbers
object Configs
```

**变更记录**

`v1.0.3` `新增`

**功能描述**

> 配置 `KYLog`。

### tag <span class="symbol">- field</span>

```kotlin:no-line-numbers
var tag: String
```

**变更记录**

`v1.0.3` `新增`

**功能描述**

> 这是一个调试日志的全局标识。

默认文案为 `KYukiReflection`。

你可以修改为你自己的文案。

### isEnable <span class="symbol">- field</span>

```kotlin:no-line-numbers
var isEnable: Boolean
```

**变更记录**

`v1.0.3` `新增`

**功能描述**

> 是否启用调试日志的输出功能。

关闭后将会停用 `KYukiReflection` 对全部日志的输出。

当 `isEnable` 关闭后 `KYukiReflection.Configs.isDebug` 也将同时关闭。