package com.adrianhoelzl.ktrie

/**
 * A trie.
 *
 * @param T the type of the components of a word
 */
interface ITrie<T> {

	/**
	 * Insert a [word] into the trie.
	 *
	 * @param word the word to insert
	 * @return a new trie that also accepts the inserted [word]
	 */
	fun insert(word: List<T>): ITrie<T>

	/**
	 * Delete a [word] in the trie.
	 *
	 * @param word the word to delete from the trie
	 * @return a new trie that does not accept the given [word]
	 */
	fun delete(word: List<T>): ITrie<T>

	/**
	 * Determine the size of the trie, i.e., the number of all words that are accepted by it.
	 *
	 * @return the number of words accepted by the trie
	 */
	@OptIn(ExperimentalUnsignedTypes::class)
	fun size(): UInt

	/**
	 * Check whether the trie accepts the given [word].
	 *
	 * @param word the word to look up
	 * @return whether the trie accepts the given [word]
	 */
	fun contains(word: List<T>): Boolean

}