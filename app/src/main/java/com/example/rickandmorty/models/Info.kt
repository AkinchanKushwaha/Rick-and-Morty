package com.example.rickandmorty.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: @RawValue Any
) : Parcelable