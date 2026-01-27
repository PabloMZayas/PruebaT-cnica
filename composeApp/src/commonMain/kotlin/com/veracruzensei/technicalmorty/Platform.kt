package com.veracruzensei.technicalmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform