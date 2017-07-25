package com.soul.learn.common.lru;

/**
 * Created by chenjianhua on 2017/6/1.
 */

class testlru {
    private static final testlru ourInstance = new testlru();

    static testlru getInstance() {
        return ourInstance;
    }

    private testlru() {
    }
}
