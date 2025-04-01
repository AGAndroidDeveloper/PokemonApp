package com.ankit.pokedoxapp.domain.utill

object Helper {
    fun String.serialnumberFormatter(): String {
        return if (this.length < 3) {
            val outPut = this.padStart(3, '0')
            "#$outPut"
        } else {
            "#$this"
        }
    }
}