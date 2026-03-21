package com.example.dessertclicker.data

import com.example.dessertclicker.R
import com.example.dessertclicker.model.Dessert

/**
 * @author runningpig66
 * @date 2026/1/8 周四
 * @time 0:03
 */
object Datasource {
    val dessertList = listOf(
        Dessert(R.drawable.cupcake, 5, 0),
        Dessert(R.drawable.donut, 10, 5),
        Dessert(R.drawable.eclair, 15, 10),
        Dessert(R.drawable.froyo, 30, 15),
        Dessert(R.drawable.gingerbread, 50, 20),
        Dessert(R.drawable.honeycomb, 100, 25),
        Dessert(R.drawable.icecreamsandwich, 500, 30),
        Dessert(R.drawable.jellybean, 1000, 35),
        Dessert(R.drawable.kitkat, 2000, 40),
        Dessert(R.drawable.lollipop, 3000, 45),
        Dessert(R.drawable.marshmallow, 4000, 50),
        Dessert(R.drawable.nougat, 5000, 55),
        Dessert(R.drawable.oreo, 6000, 60)
    )
}
