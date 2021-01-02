package com.adrianhoelzl.ktrie

/**
 * A trie for strings.
 *
 * @property internalTrie the character trie used internally
 */
class StringTrie(
	private val internalTrie: ITrie<Char>
) {

	/**
	 * Insert a [word] into the trie.
	 *
	 * @param word the word to insert
	 * @return a new trie that also accepts the inserted [word]
	 */
	fun insert(word: String): StringTrie {
		return StringTrie(internalTrie.insert(word.toList()))
	}

	/**
	 * Delete a [word] in the trie.
	 *
	 * @param word the word to delete from the trie
	 * @return a new trie that does not accept the given [word]
	 */
	fun delete(word: String): StringTrie {
		return StringTrie(internalTrie.delete(word.toList()))
	}

	/**
	 * Determine the size of the trie, i.e., the number of all words that are accepted by it.
	 *
	 * @return the number of words accepted by the trie
	 */
	@OptIn(ExperimentalUnsignedTypes::class)
	fun size() = internalTrie.size()

	/**
	 * Check whether the trie accepts the given [word].
	 *
	 * @param word the word to look up
	 * @return whether the trie accepts the given [word]
	 */
	fun contains(word: String) = internalTrie.contains(word.toList())


}