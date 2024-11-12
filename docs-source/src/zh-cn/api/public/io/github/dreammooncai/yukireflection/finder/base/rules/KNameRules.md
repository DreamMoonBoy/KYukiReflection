---
pageClass: code-page
---

# NameRules <span class="symbol">- class</span>

```kotlin:no-line-numbers
class NameRules private constructor()
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 这是一个模糊 `KClass`、`KCallable` 名称条件实现类。

可对 R8 混淆后的 `KClass`、`KCallable` 进行更加详细的定位。

## String.isSynthetic <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isSynthetic(index: Int): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否为匿名类的主类调用对象。

## String.isOnlySymbols <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isOnlySymbols(): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否只有符号。

## String.isOnlyLetters <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isOnlyLetters(): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否只有字母。

## String.isOnlyNumbers <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isOnlyNumbers(): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否只有数字。

## String.isOnlyLettersNumbers <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isOnlyLettersNumbers(): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否只有字母或数字。

## String.isOnlyLowercase <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isOnlyLowercase(): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否只有小写字母。

在没有其它条件的情况下设置此条件允许判断对象存在字母以外的字符。

## String.isOnlyUppercase <span class="symbol">- i-ext-method</span>

```kotlin:no-line-numbers
fun String.isOnlyUppercase(): Boolean
```

**变更记录**

`v1.0.0` `添加`

**功能描述**

> 是否只有大写字母。

在没有其它条件的情况下设置此条件允许判断对象存在字母以外的字符。