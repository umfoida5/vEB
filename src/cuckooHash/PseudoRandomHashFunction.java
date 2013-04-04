/**
 * Usage is subject to license terms
 */
package cuckooHash;

import java.util.Random;

/**
 * An implementation of {@link HashFunction} that uses a pseudo-random
 * number generator for generating hashes.   This implementation remains
 * deterministic by using the <ore>hashValue()</pre> method of an object to
 * seed the random number generator.
 * 
 * @author lmonson
 */
public class PseudoRandomHashFunction implements HashFunction {
    final int numberOfRounds;

    public PseudoRandomHashFunction(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        if ( numberOfRounds<=0 )
            throw new IllegalArgumentException();
    }

    
    public PseudoRandomHashFunction() {
        this(1);
    }

    public int hash(Object objectToHash, int maxBits) {
        Random r = new Random( objectToHash.hashCode());
        int result = r.nextInt(maxBits);
        int round = 1;
        while( round< numberOfRounds) {
            ++round;
            result = r.nextInt(maxBits);
        }
        return result;
    }
    

}

