package com.nilcire.dreamscometrue.data

import java.security.Timestamp
import java.util.*
import kotlin.collections.ArrayList

data class HomePost(
    val uuid: String,
    val imageUrl: String,
    val title: String,
    val content: String,
    val poster: User,
    val tag: ArrayList<String>,
    val postTimeStamp: Long,
    val comments: MutableList<Comment>,

)

data class Comment(
    val poster: User,
    val content: String,
    val postTimeStamp: Long
)

enum class PostTag(val categories: ArrayList<Category>) {
    NBA(arrayListOf(Category.Sport, Category.Basketball)),
    Dua_Lipa(arrayListOf(Category.Music, Category.Singer, Category.POP)),
    Stephen_Curry(arrayListOf(Category.Basketball, Category.Sport, Category.NBA)),
}

enum class Category {
    Sport, Basketball, NBA,
    Music, Piano, POP, Singer,

}
