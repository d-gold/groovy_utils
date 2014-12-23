package org.pittsburghtank.groovy_utils.fct

import org.pittsburghtank.groovy_utils.Example
import org.pittsburghtank.groovy_utils.fct.curry

class FNullTest extends GroovyTestCase {

    def testFunction(a, b, c, d) {
        return a + 2 * b + 4 * c + 8 * d    
    }

    void testBasicUsage() {
        f1 = curry.fnull(this.&testFunction)
    }

}
