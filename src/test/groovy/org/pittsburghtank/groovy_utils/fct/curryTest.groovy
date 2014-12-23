package org.pittsburghtank.groovy_utils.fct

import static org.pittsburghtank.groovy_utils.fct.Curry.fnull

class FNullTest extends GroovyTestCase {

    def testFunction(a, b, c, d) {
        return a + 2 * b + 4 * c + 8 * d    
    }

    def testFunction2(a, b, c, d) {
        return a + 2 * (b?:0) + 4 * (c?:0) + 8 * (d?:0)
    }

    void testBasicUsage() {
        def f1 = fnull(this.&testFunction, 2, 3, 4, 5)
        assert f1(10, 20, 30, 40) == 490
        assert f1(null, null, null, null) == 64
        assert f1(10, null, null, null) == 72
        assert f1(null, null, null, 40) == 344
        assert f1(10, null, null, 40) == 352
        assert f1(null, 7, 11, null) == 100
    }

    void testExtraParametersIgnored() {
        def f1 = fnull(this.&testFunction, 2, 3, 4, 5, 6, 7, 8)
        assert f1(10, 20, 30, 40) == 490
        assert f1(null, null, null, null) == 64
        assert f1(10, null, null, null) == 72
        assert f1(null, null, null, 40) == 344
        assert f1(10, null, null, 40) == 352
        assert f1(null, 7, 11, null) == 100
    }

    void testShortParametersKindOfOk() {
        // This test mainly demonstrates that if you don't want to provide
        // defaults for all of the paramters, you have deal with them
        // defaulting to null.
        def f1 = fnull(this.&testFunction, 2, 3)
        assert f1(10, 20, 30, 40) == 490
        assert f1(null, null, 30, 40) == 448

        try {
            // This test is trying to do a;
            //    (2 * 1) + (3 * 2) + (30 * 4) + (null * 8)
            f1(null, null, 30, null)
        }
        catch(GroovyRuntimeException) {
        }
    }

    void testShortParametersKindOfBetter() {
        // This test mainly demonstrates that if you don't want to provide
        // defaults for all of the paramters, you have deal with them
        // defaulting to null.
        def f1 = fnull(this.&testFunction2, 2, 3)
        assert f1(10, 20, 30, 40) == 490
        assert f1(null, null, 30, 40) == 448
        assert f1(null, null, 30, null) == 128
    }
}
