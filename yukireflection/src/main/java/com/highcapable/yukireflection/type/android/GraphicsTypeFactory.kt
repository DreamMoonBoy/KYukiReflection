/*
 * YukiReflection - An efficient Reflection API for the Android platform built in Kotlin.
 * Copyright (C) 2019-2023 HighCapable
 * https://github.com/fankes/YukiReflection
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is Created by fankes on 2022/2/13.
 * This file is Modified by fankes on 2023/1/21.
 */
@file:Suppress("unused", "KDocUnresolvedReference")

package com.highcapable.yukireflection.type.android

import android.graphics.*
import android.graphics.drawable.*
import android.os.Build
import android.text.*
import android.util.Size
import android.util.SizeF
import com.highcapable.yukireflection.factory.classOf

/**
 * 获得 [Typeface] 类型
 * @return [Class]<[Typeface]>
 */
val TypefaceClass get() = classOf<Typeface>()

/**
 * 获得 [Bitmap] 类型
 * @return [Class]<[Bitmap]>
 */
val BitmapClass get() = classOf<Bitmap>()

/**
 * 获得 [Icon] 类型
 *
 * - ❗在 Android M (23) 及以上系统加入
 * @return [Class]<[Icon]> or null
 */
val IconClass get() = if (Build.VERSION.SDK_INT >= 23) classOf<Icon>() else null

/**
 * 获得 [Outline] 类型
 * @return [Class]<[Outline]>
 */
val OutlineClass get() = classOf<Outline>()

/**
 * 获得 [Drawable] 类型
 * @return [Class]<[Drawable]>
 */
val DrawableClass get() = classOf<Drawable>()

/**
 * 获得 [GradientDrawable] 类型
 * @return [Class]<[GradientDrawable]>
 */
val GradientDrawableClass get() = classOf<GradientDrawable>()

/**
 * 获得 [ColorDrawable] 类型
 * @return [Class]<[ColorDrawable]>
 */
val ColorDrawableClass get() = classOf<ColorDrawable>()

/**
 * 获得 [BitmapDrawable] 类型
 * @return [Class]<[BitmapDrawable]>
 */
val BitmapDrawableClass get() = classOf<BitmapDrawable>()

/**
 * 获得 [Size] 类型
 * @return [Class]<[Size]>
 */
val SizeClass get() = classOf<Size>()

/**
 * 获得 [SizeF] 类型
 * @return [Class]<[SizeF]>
 */
val SizeFClass get() = classOf<SizeF>()

/**
 * 获得 [Rect] 类型
 * @return [Class]<[Rect]>
 */
val RectClass get() = classOf<Rect>()

/**
 * 获得 [RectF] 类型
 * @return [Class]<[RectF]>
 */
val RectFClass get() = classOf<RectF>()

/**
 * 获得 [NinePatch] 类型
 * @return [Class]<[NinePatch]>
 */
val NinePatchClass get() = classOf<NinePatch>()

/**
 * 获得 [Paint] 类型
 * @return [Class]<[Paint]>
 */
val PaintClass get() = classOf<Paint>()

/**
 * 获得 [TextPaint] 类型
 * @return [Class]<[TextPaint]>
 */
val TextPaintClass get() = classOf<TextPaint>()

/**
 * 获得 [Canvas] 类型
 * @return [Class]<[Canvas]>
 */
val CanvasClass get() = classOf<Canvas>()

/**
 * 获得 [Point] 类型
 * @return [Class]<[Point]>
 */
val PointClass get() = classOf<Point>()

/**
 * 获得 [PointF] 类型
 * @return [Class]<[PointF]>
 */
val PointFClass get() = classOf<PointF>()

/**
 * 获得 [Matrix] 类型
 * @return [Class]<[Matrix]>
 */
val MatrixClass get() = classOf<Matrix>()

/**
 * 获得 [ColorMatrix] 类型
 * @return [Class]<[ColorMatrix]>
 */
val ColorMatrixClass get() = classOf<ColorMatrix>()

/**
 * 获得 [ColorMatrixColorFilter] 类型
 * @return [Class]<[ColorMatrixColorFilter]>
 */
val ColorMatrixColorFilterClass get() = classOf<ColorMatrixColorFilter>()

/**
 * 获得 [TextUtils] 类型
 * @return [Class]<[TextUtils]>
 */
val TextUtilsClass get() = classOf<TextUtils>()

/**
 * 获得 [Editable] 类型
 * @return [Class]<[Editable]>
 */
val EditableClass get() = classOf<Editable>()

/**
 * 获得 [TextWatcher] 类型
 * @return [Class]<[TextWatcher]>
 */
val TextWatcherClass get() = classOf<TextWatcher>()

/**
 * 获得 [Editable.Factory] 类型
 * @return [Class]<[Editable.Factory]>
 */
val Editable_FactoryClass get() = classOf<Editable.Factory>()

/**
 * 获得 [GetChars] 类型
 * @return [Class]<[GetChars]>
 */
val GetCharsClass get() = classOf<GetChars>()

/**
 * 获得 [Spannable] 类型
 * @return [Class]<[Spannable]>
 */
val SpannableClass get() = classOf<Spannable>()

/**
 * 获得 [SpannableStringBuilder] 类型
 * @return [Class]<[SpannableStringBuilder]>
 */
val SpannableStringBuilderClass get() = classOf<SpannableStringBuilder>()

/**
 * 获得 [BitmapFactory] 类型
 * @return [Class]<[BitmapFactory]>
 */
val BitmapFactoryClass get() = classOf<BitmapFactory>()

/**
 * 获得 [BitmapFactory.Options] 类型
 * @return [Class]<[BitmapFactory.Options]>
 */
val BitmapFactory_OptionsClass get() = classOf<BitmapFactory.Options>()