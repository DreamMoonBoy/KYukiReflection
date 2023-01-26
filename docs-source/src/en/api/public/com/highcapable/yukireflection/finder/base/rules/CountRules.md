---
pageClass: code-page
---

::: warning

The English translation of this page has not been completed, you are welcome to contribute translations to us.

You can use the **Chrome Translation Plugin** to translate entire pages for reference.

:::

# CountRules <span class="symbol">- class</span>

```kotlin:no-line-numbers
class CountRules private constructor()
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 这是一个模糊 `Class`、`Member` 数组 (下标) 个数条件实现类。

可对 R8 混淆后的 `Class`、`Member` 进行更加详细的定位。

## Int.isZero <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun Int.isZero(): Boolean
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 是否为 0。

## Int.moreThan <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun Int.moreThan(count: Int): Boolean
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 大于 `count`。

## Int.lessThan <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun Int.lessThan(count: Int): Boolean
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 小于 `count`。

## Int.inInterval <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun Int.inInterval(countRange: IntRange): Boolean
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 在 `countRange` 区间 A ≤ this ≤ B。