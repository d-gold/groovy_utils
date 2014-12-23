package org.pittsburghtank.groovy_utils.fct


public class Curry {
    static Closure fnull(Closure f, Object... args) {
        def defaultArgs = args.toList()

        def g = { Object... callArgs ->
            def len = callArgs.size()
            def mergedArgs = (0..<len).collect {i ->
                callArgs[i] == null ? (i < defaultArgs.size() ? defaultArgs[i] : null) : callArgs[i]
            }

            f(mergedArgs)
        }

        g
    }

    public Closure fnull() {
        return null
    }
}
