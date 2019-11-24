package com.github.testframework;

import java.io.File;

/**
 * PowerMockCase
 *
 * @author dongdaiming(董代明)
 * @date 2019-07-25
 */
public final class PowerMockCase {

    public static int staticMethod1(int n) {
        throw new UnsupportedOperationException();
    }

    private int privateMethod1(int n) {
        throw new UnsupportedOperationException();
    }

    public int toPrivateMethod1(int n) {
        return privateMethod1(n);
    }

    public final int finalMethod1(int n) {
        throw new UnsupportedOperationException();
    }

    private int voidMethod1(int n) {
        throw new UnsupportedOperationException();
    }

    public boolean fileExists(String path) {
        return new File(path).exists();
    }

}
