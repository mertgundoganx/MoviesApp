package com.mertgundoganx.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    @SerializedName("results")
    val results: List<MoviesResults>
) : Parcelable
