---
pageClass: code-page
---

::: warning

The English translation of this page has not been completed, you are welcome to contribute translations to us.

You can use the **Chrome Translation Plugin** to translate entire pages for reference.

:::

# MemberRulesResult <span class="symbol">- class</span>

```kotlin:no-line-numbers
class MemberRulesResult internal constructor(private val rulesData: MemberRulesData)
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 当前 `Member` 查找条件结果实现类。

## none <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun none(): MemberRulesResult
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 设置当前 `Member` 在查找条件中个数为 `0`。

## count <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun count(num: Int): MemberRulesResult
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 设置当前 `Member` 在查找条件中需要全部匹配的个数。

## count <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun count(numRange: IntRange): MemberRulesResult
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 设置当前 `Member` 在查找条件中需要全部匹配的个数范围。

## count <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun count(conditions: CountConditions): MemberRulesResult
```

**Change Records**

`v1.0.0` `first`

**Function Illustrate**

> 设置当前 `Member` 在查找条件中需要全部匹配的个数条件。