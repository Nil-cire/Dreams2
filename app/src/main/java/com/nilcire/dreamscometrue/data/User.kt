package com.nilcire.dreamscometrue.data

import java.util.*

data class User(
    val uuid: String,
    var name: String,
    var gender: Gender,
    var region: Region,
)

enum class Gender {
    Male, Female,
}

enum class Region {
    Africa, Asia, NorthAmerica, SouthAmerica,
}
