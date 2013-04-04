/**
 * Usage is subject to license terms
 */
package cuckooHash;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of a Cuckoo Hashtable.
 * <h2>Creating a Cuckoo hash table</h2>
 * <pre>
 * Cuckoo c = new Cuckoo();     // Create the hashtable
 * c.add(1);                    // add an Integer to the hashtable
 * c.add("blah");               // add a string to the hashtable
 * c.contains(1);               // returns true
 * c.contains(2);               // returns false
 * </pre>
 * <h2>Limiting the hash table to a single data type</h2>
 * <pre>
 * Cuckoo&ltString> c = new Cuckoo&ltString>();     // Create the hashtable
 * c.add(1);                    // compilation error
 * c.add("blah");               // add a string to the hashtable
 * </pre>
 * <h2>Supplying your own hash functions</h2>
 * <pre>
 * HashFunction myHash1 = ...
 * HashFunction myHash2 = ...
 * Cuckoo c = new Cuckoo(myHash1, myHash2);     // Create the hashtable
 * </pre>
 * @author lmonson
 */
public class Cuckoo<T> {
    private int hashTableSize = 4194304;
    private List<T> hashTable = allocateList(hashTableSize);
    private final HashFunction h1;
    private final HashFunction h2;

    public Cuckoo() {
        this( new PseudoRandomHashFunction(1), new PseudoRandomHashFunction(2));
    }

    
    public Cuckoo(HashFunction h1, HashFunction h2) {
        this.h1 = h1;
        this.h2 = h2;
    }
    
    
    public boolean contains(T t) {
        int idx1 = hash(h1,t);
        if ( t.equals(hashTable.get(idx1)))
            return true;
        
        idx1 = hash(h2,t);
        if ( t.equals(hashTable.get(idx1)))
            return true;
        
        return false;
        
    }

    public void add(T t) {
        for(int idx=0; idx<500; ++idx) {
            if ( contains(t) )
                return;
            if ( addToExistingHashTable(t) )
                return;
            rehash();
        }
    }
    
    private List<T> allocateList(int size) {
        List<T> l = new ArrayList<T>(size);
        for(int i=0; i<size; ++i)
            l.add(null);
        return l;
    }
    
    private int hash(HashFunction func, T t) {
        return func.hash(t, hashTableSize);
    }
    
    private boolean addToExistingHashTable(final T t ) {
        int pos = hash(h1,t);
        T bubbleT = t;
        int loopCount = 0;
        while( bubbleT!=t || loopCount<hashTableSize) {
            T tmpT = hashTable.get(pos);
            if ( tmpT==null ) {
                hashTable.set(pos,bubbleT);
                return true;
            }
            hashTable.set(pos,bubbleT);
            bubbleT = tmpT;
            if ( pos==hash(h1,bubbleT))
                pos = hash(h2,bubbleT);
            else
                pos = hash(h1,bubbleT);
            ++loopCount;
        }
        return false;
    }
    
    private void rehash() {
        List<T> oldHashTable = hashTable;
        int oldHashTableSize = hashTableSize;
        hashTableSize = oldHashTableSize*2;
        hashTable = allocateList( hashTableSize );      // overflow possible!
        for(T t:oldHashTable) {
            if ( t!=null )
                add(t);
        }
    }
    
    

}
