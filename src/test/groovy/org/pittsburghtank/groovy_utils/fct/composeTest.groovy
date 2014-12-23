package org.pittsburghtank.groovy_utils.fct

import static org.pittsburghtank.groovy_utils.fct.Compose.compose

class ComposeTest extends GroovyTestCase {

    def add(a, b) { a + b }
    def add3(a, b, c) { a + b + c }
    def add4(a, b, c, d) { a + b  + c + d }
    def sub(a, b) { a - b }
    def mul(a, b) { a * b }
    def mul3(a, b, c) { a * b * c }
    def mul4(a, b, c, d) { a * b * c * d }
    def dbl(a) { a * 2 }
    def inc(a) { a + 1 }
    def dec(a) { a - 1 }
    def pos_and_neg(a) { [a, -a] }
    def inc_and_dec(a) { [a + 1, a - 1] }

    void testCompose2() {
        def f1 = compose(this.&add, this.&inc)
        assert f1(2, 3) == 6

    }

    void testComposeMult() {
        def f1 = compose(this.&add, this.&inc, this.&dbl, this.&dec)
        assert f1(2, 3) == 11
    }
}

