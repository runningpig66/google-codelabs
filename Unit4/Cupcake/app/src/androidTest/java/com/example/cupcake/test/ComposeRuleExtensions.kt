package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * @author runningpig66
 * @date 2026/1/17 周六
 * @time 7:33
 *
 * Finds a semantics node with the given string resource id.
 *
 * The [onNodeWithText] finder provided by compose ui test API, doesn't support usage of
 * string resource id to find the semantics node. This extension function accesses string resource
 * using underlying activity property and passes it to [onNodeWithText] function as argument and
 * returns the [SemanticsNodeInteraction] object.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int,
    vararg formatArgs: Any
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id, *formatArgs))
