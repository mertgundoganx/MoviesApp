package com.mertgundoganx.moviesapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResults(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")//
    val overview: String,
    @SerializedName("poster_path")//
    val poster_path: String,
    @SerializedName("release_date")//
    val release_date: String,
    @SerializedName("title")//
    val title: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
) : Parcelable
