package com.example.hanas.android.ui.theme

import androidx.compose.ui.graphics.Color

interface ColorScheme {
    // テキスト
    val text: Color
    val secondaryText: Color

    // 背景色
    val background: Color
    val secondaryBackground: Color

    // 影
    val shadow: Color

    // 白
    val white: Color

    // 黒
    val black: Color

    // グレー
    val gray100: Color
    val gray200: Color
    val gray300: Color
    val gray400: Color
    val gray500: Color
    val gray600: Color
    val gray700: Color
    val gray800: Color
    val gray900: Color

    // 黄色
    val lightYellow: Color
    val yellow: Color
    val darkYellow: Color

    // オレンジ
    val lightOrange: Color
    val orange: Color
    val darkOrange: Color

    // 紫
    val lightPurple: Color
    val purple: Color
    val darkPurple: Color

    // 緑
    val lightGreen: Color
    val green: Color
    val darkGreen: Color

    // ピンク
    val lightPink: Color
    val pink: Color
    val darkPink: Color

    // 青
    val lightBlue: Color
    val blue: Color
    val darkBlue: Color

    // 赤
    val lightRed: Color
    val red: Color
    val darkRed: Color
}

abstract class BaseColorScheme : ColorScheme {
    // 白
    override val white: Color = Color(0xffffffff)

    // 黒
    override val black: Color = Color(0xff000000)

    // グレー
    override val gray100 = Color(0xFFF3F3F3)
    override val gray200 = Color(0xFFEEEEEE)
    override val gray300 = Color(0xFFE0E0E0)
    override val gray400 = Color(0xFFBDBDBD)
    override val gray500 = Color(0xFF9E9E9E)
    override val gray600 = Color(0xFF757575)
    override val gray700 = Color(0xFF616161)
    override val gray800 = Color(0xFF424242)
    override val gray900 = Color(0xFF212121)

    // 黄色
    override val lightYellow: Color = Color(0xFFDCC389)
    override val yellow: Color = Color(0xFFD5AB3E)
    override val darkYellow: Color = Color(0xFF866E2B)

    // オレンジ
    override val lightOrange: Color = Color(0xFFFFBD92)
    override val orange: Color = Color(0xFFFF8B42)
    override val darkOrange: Color = Color(0xFFAB8352)

    // 紫
    override val lightPurple: Color = Color(0xFFC49DFF)
    override val purple: Color = Color(0xFFA264FF)
    override val darkPurple: Color = Color(0xFF5E3F8F)

    // 緑
    override val lightGreen: Color = Color(0xFFBDFF96)
    override val green: Color = Color(0xFF47D23E)
    override val darkGreen: Color = Color(0xFF286E21)

    // ピンク
    override val lightPink: Color = Color(0xFFC9879E)
    override val pink: Color = Color(0xFFE1628A)
    override val darkPink: Color = Color(0xFF8F445D)

    // 青
    override val lightBlue: Color = Color(0xFF86AAFF)
    override val blue: Color = Color(0xFF4865FF)
    override val darkBlue: Color = Color(0xFF243ABB)

    // 赤
    override val lightRed: Color = Color(0xFFF19C9C)
    override val red: Color = Color(0xFFE74343)
    override val darkRed: Color = Color(0xFF802323)
}

object LightColorScheme : BaseColorScheme() {
    override val text: Color = black
    override val secondaryText: Color = gray800
    override val background: Color = white
    override val secondaryBackground: Color = gray100
    override val shadow: Color = black
}

object DarkColorScheme : BaseColorScheme() {
    override val text: Color = white
    override val secondaryText: Color = gray200
    override val background: Color = black
    override val secondaryBackground: Color = gray900
    override val shadow: Color = white
}
