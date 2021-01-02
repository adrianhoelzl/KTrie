package com.adrianhoelzl.ktrie

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrieTest {

	@Test
	fun testInsert() {
		val exemplaryWord = listOf("A", "B", "C")
		val trie = emptyTrie<String>()
		val newTrie = trie.insert(exemplaryWord)
		assertTrue(newTrie.contains(exemplaryWord))
	}

	@ExperimentalUnsignedTypes
	@Test
	fun testEmptyTrie() {
		val emptyTrie = emptyTrie<String>()
		assertTrue(emptyTrie.size() == 0u)
	}

	@ExperimentalUnsignedTypes
	@Test
	fun testTrieOf() {
		val exemplaryWords = listOf(
			listOf("A", "B", "C"),
			listOf("A", "B"),
			listOf("A", "B", "C", "D"),
			listOf("A", "B", "E"),
			listOf("A", "B", "F"),
		)
		val trie = trieOf(*exemplaryWords.toTypedArray())
		exemplaryWords.forEach {
			assertTrue(trie.contains(it))
		}
		assertEquals(exemplaryWords.size.toUInt(), trie.size())
	}

	@ExperimentalUnsignedTypes
	@Test
	fun testDelete() {
		val exemplaryWord = listOf("A", "B", "C")
		val initialTrie = trieOf(exemplaryWord)
		val trie = initialTrie.delete(exemplaryWord)
		assertFalse(trie.contains(exemplaryWord))
		assertEquals(trie.size(), 0u)
	}

}