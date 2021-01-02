package com.adrianhoelzl.ktrie

import com.adrianhoelzl.ktrie.extension.head
import com.adrianhoelzl.ktrie.extension.tail

/**
 * A trie.
 *
 * @property children the children tries
 * @property isAccepting whether the current node is accepting
 */
class Trie<T>(
	private val children: Map<T, ITrie<T>>,
	private val isAccepting: Boolean
) : ITrie<T> {

	override fun insert(word: List<T>): ITrie<T> {
		if (word.isEmpty()) {
			return Trie(children, isAccepting = true)
		}

		// get the first component of the word
		val head = checkNotNull(word.head())

		// get the appropriate child for the first component
		val childTrie = children[head] ?: emptyTrie()

		// the remaining part of the word
		val suffix = word.tail()

		// insert or update the entry
		return Trie(
			children + (head to childTrie.insert(suffix)),
			isAccepting
		)
	}

	override fun delete(word: List<T>): ITrie<T> {
		if (word.isEmpty()) {
			return Trie(children, isAccepting = false)
		}

		// get the first component of the word
		val head = checkNotNull(word.head())

		// get the appropriate child for the first component
		val childTrie = children[head] ?: return this

		// the remaining part of the word
		val suffix = word.tail()

		// insert or update the entry
		return Trie(
			children + (head to childTrie.delete(suffix)),
			isAccepting
		)
	}

	@OptIn(ExperimentalUnsignedTypes::class)
	override fun size() = children.values.sumOf { it.size() } + if (isAccepting) 1u else 0u

	override fun contains(word: List<T>): Boolean {
		if (word.isEmpty()) {
			return isAccepting
		}

		// get the first component of the word
		val head = checkNotNull(word.head())

		// get the appropriate child for the first component
		val childTrie = children[head] ?: return false

		// propagate lookup to child trie
		return childTrie.contains(word.tail())
	}
}

/**
 * Construct an empty trie.
 *
 * @return a trie that accepts no words
 */
fun <T> emptyTrie(): ITrie<T> = Trie(emptyMap(), false)

/**
 * Construct a trie that accepts a given set of [words].
 *
 * @param words the words to insert
 * @return a trie that accepts the given [words]
 */
fun <T> trieOf(vararg words: List<T>): ITrie<T> = words.fold(emptyTrie()) { trie, word ->
	trie.insert(word)
}