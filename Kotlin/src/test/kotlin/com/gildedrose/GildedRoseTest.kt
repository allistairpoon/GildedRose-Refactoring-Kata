package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.update()

    }

    @Test
    //quality degrades twice as fast
    fun twice() {
        val items = listOf(Item("foo", 0, 5))
        val app = GildedRose(items)
        app.update()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(3, app.items[0].quality)
    }

    @Test
    //quality never negative
    fun negative() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.update()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    //quality increases
    fun agedBrie() {
        val items = listOf(Item("Aged Brie", 10, 5))
        val app = GildedRose(items)
        app.update()
        assertEquals(9, app.items[0].sellIn)
        assertEquals(6, app.items[0].quality)
    }

    @Test
    //quality never more than 50
    fun moreThan50() {
        val items = listOf(Item("Aged Brie", 10, 50))
        val app = GildedRose(items)
        app.update()
        assertEquals(9, app.items[0].sellIn)
        assertEquals(50, app.items[0].quality)
    }

    @Test
    //never drops
    fun sulfuras() {
        val items = listOf(Item("Sulfuras, Hand of Ragnaros", 10, 5))
        val app = GildedRose(items)
        app.update()
        assertEquals(10, app.items[0].sellIn)
        assertEquals(5, app.items[0].quality)
    }

    @Test
    //quality + 2
    fun backstageLessThan10() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 5))
        val app = GildedRose(items)
        app.update()
        assertEquals(9, app.items[0].sellIn)
        assertEquals(7, app.items[0].quality)
    }

    @Test
    //quality + 3
    fun backstageLessThan5() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 5))
        val app = GildedRose(items)
        app.update()
        assertEquals(4, app.items[0].sellIn)
        assertEquals(8, app.items[0].quality)
    }

    @Test
    //quality to 0
    fun backstage0() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 5))
        val app = GildedRose(items)
        app.update()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(0, app.items[0].quality)
    }
}


