package com.example.hanas.android.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.hanas.android.R
import com.example.hanas.android.ui.theme.ZenMaruGothic.zenMaruGothicBold
import com.example.hanas.android.ui.theme.ZenMaruGothic.zenMaruGothicRegular

private object ZenMaruGothic {
    val zenMaruGothicBold = FontFamily(Font(resId = R.font.zenmarugothic_bold, FontWeight.Normal))
    val zenMaruGothicRegular = FontFamily(Font(resId = R.font.zenmarugothic_regular, FontWeight.Bold))
}

private enum class FontSize(val value: TextUnit) {
    XL(36.sp),
    L(30.sp),
    M(24.sp),
    S(20.sp),
    XS(16.sp)
}

class Typography(initialColor: Color) {
    val xlBold = TextStyle(
        fontFamily = zenMaruGothicBold,
        fontSize = FontSize.XL.value,
        color = initialColor
    )
    val xlRegular = TextStyle(
        fontFamily = zenMaruGothicRegular,
        fontSize = FontSize.XL.value,
        color = initialColor
    )

    val lBold = TextStyle(
        fontFamily = zenMaruGothicBold,
        fontSize = FontSize.L.value,
        color = initialColor
    )
    val lRegular = TextStyle(
        fontFamily = zenMaruGothicRegular,
        fontSize = FontSize.L.value,
        color = initialColor
    )

    val mBold  = TextStyle(
        fontFamily = zenMaruGothicBold,
        fontSize = FontSize.M.value,
        color = initialColor
    )
    val mRegular = TextStyle(
        fontFamily = zenMaruGothicRegular,
        fontSize = FontSize.M.value,
        color = initialColor
    )

    val sBold  = TextStyle(
        fontFamily = zenMaruGothicBold,
        fontSize = FontSize.S.value,
        color = initialColor
    )
    val sRegular = TextStyle(
        fontFamily = zenMaruGothicRegular,
        fontSize = FontSize.S.value,
        color = initialColor
    )

    val xsBold  = TextStyle(
        fontFamily = zenMaruGothicBold,
        fontSize = FontSize.XS.value,
        color = initialColor
    )
    val xsRegular = TextStyle(
        fontFamily = zenMaruGothicRegular,
        fontSize = FontSize.XS.value,
        color = initialColor
    )
}