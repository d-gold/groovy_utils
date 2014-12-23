package org.pittsburghtank.groovy_utils.fct

public class Compose {
    // Needs real home
    def identity = { x -> x }

    static Closure compose(Closure... functions) {
        // You should use foo << bar << baz instead of this. 

        def f = { Object... callArgs -> 
            def result = callArgs.clone()
            functions.each { result = it(result) }
            result
        }
        return f
    }

    static Closure mergeCompose(Closure f, Iterable<Closure> g) {
        return null
    }

    static Closure splitCompose(Iterable<Closure> f, Closure g) {
        return null
    }

    static Closure diamondCompose() {
        return null
    }

    static Closure bowtieCompose() {
        return null
    }
}
