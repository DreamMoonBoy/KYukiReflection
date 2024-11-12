---
pageClass: code-page
---

# ObjectRules <span class="symbol">- class</span>

```kotlin:no-line-numbers
class ObjectRules private constructor(private val instance: Any)
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 这是一个任意对象条件实现类。

可对 R8 混淆后的 `KClass`、`KCallable` 进行更加详细的定位。