package org.pittsburghtank.groovy_utils.inject_expr

public class _ {
    // Right now just a placeholder. This will get some extra stuff
    // It will also need to find a real home
}

public class Inject {

    static public void removeMethod() { throw new MissingMethodException() }


    static public void injectCaseOf() {

        def isMatch = { v, arg -> 
            (v instanceof _ || v == _) ? true : (
                (v instanceof Closure) ? v(arg) : v == arg
            )
        }

        def getValue = { v, arg ->
            (v instanceof Closure) ? v(arg) : v
        }

        java.lang.Object.metaClass.caseOf = { Iterable<Tuple<Closure, Closure>> expr, requireDefault=true ->
            // Pretty imperative for now...
            def results = delegate
            def found = false
            for (Tuple<Closure, Closure> testCase: expr) {
                if (isMatch(testCase[0], delegate)) {
                    results = getValue(testCase[1], delegate)
                    found = true
                    break
                }
            }
            if (!found && requireDefault) {
                throw new Exception('caseOf requires all cases to be handled')
            } 
            results
        }
    }

    static public void removeCaseOf() {
        java.lang.Object.metaClass.caseOf = this.&removeMethod
    }

    static public void injectAll() {
        Inject.injectCaseOf()
    }

    static public void removeAll() {
        Inject.removeCaseOf()
    }
}

