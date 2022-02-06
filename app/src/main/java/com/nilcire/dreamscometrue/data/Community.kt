package com.nilcire.dreamscometrue.data

data class Community(
    val uuid: String,
    val name: String,
    val imgUrl: String?,
    var owner: SimpleUser,
    var members: MutableList<SimpleUser>,
)
