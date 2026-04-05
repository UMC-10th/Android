package com.clone.nike.ui.purchase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GoodsData (
    val goodsImgResId: Int,
    val goodsName: String,
    val category: String,
    val numberOfColour: String,
    val goodsPrice: String,
    var isWished: Boolean
): Parcelable