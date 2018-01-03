/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deservel.basic.thread;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author DeserveL
 * @date 2017/9/14 0014 上午 0:11
 * @since 1.0.0
 */
public class CacheFactorizer {

    public static void main(String[] args) {
        CacheFactorizerA cacheFactorizerA = new CacheFactorizerA();
        final CountDownLatch latch = new CountDownLatch(100000);
        for (int i = 0; i < 100000; i++) {
            int a = new Random().nextInt(11) % (11 - 10 + 1) + 10;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        cacheFactorizerA.service(new BigInteger(String.valueOf(a)));
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cacheFactorizerA.getHits());
        System.out.println(cacheFactorizerA.getCacheHits());
    }
}

class CacheFactorizerA {
    private BigInteger lastNumber;
    private BigInteger lastValue;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized long getCacheHits() {
        return cacheHits;
    }

    public void service(BigInteger i) throws InterruptedException {
        BigInteger factor = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factor = lastValue;
            }
        }
        if (factor == null) {
            factor = calculate(i);
            synchronized (this) {
                lastNumber = i;
                lastValue = factor;
            }
        }
        //System.out.println(factor);
    }

    public BigInteger calculate(BigInteger i) throws InterruptedException {
        Thread.sleep(1000);
        return i;
    }

}

class CacheFactorizerB {
    private BigInteger lastNumber;
    private BigInteger lastValue;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized long getCacheHits() {
        return cacheHits;
    }

    public synchronized void service(BigInteger i) throws InterruptedException {
        BigInteger factor = null;
        ++hits;
        if (i.equals(lastNumber)) {
            ++cacheHits;
            factor = lastValue;
        } else {
            factor = calculate(i);
            lastNumber = i;
            lastValue = factor;
        }
        //System.out.println(factor);
    }

    public BigInteger calculate(BigInteger i) throws InterruptedException {
        Thread.sleep(5000);
        return i;
    }

}
