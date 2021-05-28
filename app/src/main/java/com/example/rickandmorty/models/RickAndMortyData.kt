package com.example.rickandmorty.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RickAndMortyData(
    val info: Info,
    val results: List<Result>
) : Parcelable