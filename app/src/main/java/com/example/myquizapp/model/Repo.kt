package com.example.myquizapp.model

import javax.inject.Inject

class Repo @Inject constructor() {
    val data = arrayListOf(
        "Кто выиграл ЧМ по футболу 1998 года? ",
        "Кто выиграл ЧМ по футболу 2002 года?",
        "Кто выиграл ЧМ по футболу 2006 года? ",
        "Кто выиграл ЧМ по футболу 2010 года? ",
        "Кто выиграл ЧМ по футболу 2014 года? "

    )

    val answersAsVariants = arrayListOf(
        arrayListOf("Италия", "Франция", "Бразилия", "Испания", "Германия"),
        arrayListOf("Италия", "Франция", "Бразилия", "Испания", "Германия"),
        arrayListOf("Италия", "Франция", "Бразилия", "Испания", "Германия"),
        arrayListOf("Италия", "Франция", "Бразилия", "Испания", "Германия"),
        arrayListOf("Италия", "Франция", "Бразилия", "Испания", "Германия"),

    )

    val answersId = arrayListOf(2, 3, 1, 4, 5)

}