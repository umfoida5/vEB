/**
 * Usage is subject to license terms
 */
package cuckooHash;

/**
 * An implementation of a deterministic hash function.
 * @author  lmonson
 * @see     PseudoRandomHashFunction
 */
public interface HashFunction<T> {
    int hash(T objectToHash, int indexLimit);
}
