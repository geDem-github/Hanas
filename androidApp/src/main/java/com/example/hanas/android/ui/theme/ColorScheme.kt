package com.example.hanas.android.ui.theme

import androidx.compose.ui.graphics.Color

interface ColorScheme {
    // 白
    val white: Color

    // 黒
    val black: Color

    // グレー
    val lightGray: Color
    val gray: Color
    val darkGray: Color

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

object LightColorScheme : ColorScheme {
    // 白
    override val white: Color = Color(0xfff0f0f0)

    // 黒
    override val black: Color = Color(0xff0f0f0f)

    // グレー
    override val lightGray: Color = Color(0xffbababa)
    override val gray: Color = Color(0xff696969)
    override val darkGray: Color = Color(0xFF3B3B3B)

    // 黄色
    override val lightYellow: Color = Color(0xFFFFE994)
    override val yellow: Color = Color(0xFFFFD94C)
    override val darkYellow: Color = Color(0xFF9F8C44)

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
    override val lightPink: Color = Color(0xFFFFA3FD)
    override val pink: Color = Color(0xFFE758DE)
    override val darkPink: Color = Color(0xFFAD50A8)

    // 青
    override val lightBlue: Color = Color(0xFF86AAFF)
    override val blue: Color = Color(0xFF4865FF)
    override val darkBlue: Color = Color(0xFF243ABB)

    // 赤
    override val lightRed: Color = Color(0xFFF19C9C)
    override val red: Color = Color(0xFFE74343)
    override val darkRed: Color = Color(0xFF802323)
}

object DarkColorScheme : ColorScheme {
    // 白
    override val white: Color = Color(0xfff0f0f0)

    // 黒
    override val black: Color = Color(0xff0f0f0f)

    // グレー
    override val lightGray: Color = Color(0xffbababa)
    override val gray: Color = Color(0xff696969)
    override val darkGray: Color = Color(0xFF3B3B3B)

    // 黄色
    override val lightYellow: Color = Color(0xFFFFE994)
    override val yellow: Color = Color(0xFFFFD94C)
    override val darkYellow: Color = Color(0xFF9F8C44)

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
    override val lightPink: Color = Color(0xFFFFA3FD)
    override val pink: Color = Color(0xFFE758DE)
    override val darkPink: Color = Color(0xFFAD50A8)

    // 青
    override val lightBlue: Color = Color(0xFF86AAFF)
    override val blue: Color = Color(0xFF4865FF)
    override val darkBlue: Color = Color(0xFF243ABB)

    // 赤
    override val lightRed: Color = Color(0xFFF19C9C)
    override val red: Color = Color(0xFFE74343)
    override val darkRed: Color = Color(0xFF802323)
}
