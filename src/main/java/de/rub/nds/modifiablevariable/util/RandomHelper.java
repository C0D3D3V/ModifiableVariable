/**
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.modifiablevariable.util;

import java.util.Random;

/**
 * 
 */
public class RandomHelper {

    private static Random random;

    public static Random getRandom() {
        if (random == null) {
            random = new Random(0);
        }
        return random;
    }

    public static BadRandom getBadSecureRandom() {
        return new BadRandom(getRandom(), null);
    }

    public static void setRandom(Random random) {
        RandomHelper.random = random;
    }

    private RandomHelper() {
    }
}
