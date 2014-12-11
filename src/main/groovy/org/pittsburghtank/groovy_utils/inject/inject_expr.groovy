package org.pittsburghtank.groovy_utils.inject


Object.metaClass.caseOf = { Iterable<Tuple<Closure, Closure>> expr ->
    // Pretty imperative for now...
    def results = delegate
    for (Tuple<Closure, Closure> testCase: expr) {
        if (testCase[0](delegate)) {
            results = testCase[1](delegate)
            break
        }
    }
    results
}

