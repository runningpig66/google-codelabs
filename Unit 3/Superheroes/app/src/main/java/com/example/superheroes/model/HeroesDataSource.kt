package com.example.superheroes.model

import com.example.superheroes.R

/**
 * @author runningpig66
 * @date 2025/12/18 周四
 * @time 1:34
 */
object HeroesRepository {
    val heroes: List<Hero> = listOf(
        Hero(
            nameRes = R.string.hero_test,
            descriptionRes = R.string.description_test,
            imageRes = R.drawable.momonga
        ),
        Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        ),
        Hero(
            nameRes = R.string.hero2,
            descriptionRes = R.string.description2,
            imageRes = R.drawable.android_superhero2
        ),
        Hero(
            nameRes = R.string.hero3,
            descriptionRes = R.string.description3,
            imageRes = R.drawable.android_superhero3
        ),
        Hero(
            nameRes = R.string.hero4,
            descriptionRes = R.string.description4,
            imageRes = R.drawable.android_superhero4
        ),
        Hero(
            nameRes = R.string.hero5,
            descriptionRes = R.string.description5,
            imageRes = R.drawable.android_superhero5
        ),
        Hero(
            nameRes = R.string.hero6,
            descriptionRes = R.string.description6,
            imageRes = R.drawable.android_superhero6
        ),
        Hero(
            nameRes = R.string.hero_test,
            descriptionRes = R.string.description_test,
            imageRes = R.drawable.momonga
        )
    )

    /**
     * 把 heroes 复制 copies 份，并为每一行生成唯一的 rowId
     */
    fun heroesAsRows(copies: Int): List<HeroRow> {
        require(copies >= 1)
        val base = heroes
        return List(copies) { copyIndex ->
            base.mapIndexed { indexInBase, hero ->
                val rowId = copyIndex.toLong() * base.size + indexInBase
                HeroRow(rowId = rowId, hero = hero)
            }
        }.flatten()
    }
}
