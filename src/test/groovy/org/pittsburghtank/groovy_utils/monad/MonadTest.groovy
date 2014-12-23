package org.pittsburghtank.groovy_utils.monad

import groovy.transform.TypeChecked
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable
import org.pittsburghtank.groovy_utils.monad.Monad


// I have no idea what I'm doing here...
//
@TypeChecked
@Canonical
class Maybe<T> implements Monad {

    public Just<T> just(v) {
        return new Just(v)
    }
    public Nothing nothing() {
        return new Nothing()
    }

    public T unit(Object v) {
        { T x -> just(x) }
    }

    public T bind(Closure f) {
        return null
    }
}

@EqualsAndHashCode(includeFields=true)
class Just<T> extends Maybe<T> {

    T value
    
    String toString() { "Just(${this.value?.toString()})" }
}

@EqualsAndHashCode(includeFields=true)
class Nothing extends Maybe {
    String toString() { "Nothing" }
}

public class MonadTest extends GroovyTestCase {

    void testSimpleMonad() {

        // m = Identity()
        // assert m.bind { x-> unit(x) } == m
        // assert unit(x).bind(f) == f(x)
        // assert m.bind(f).bind(g) == m.bind { x -> f(x).bind(g) }
    }

    void testMzero() {
        // m.flatMap { x -> mzero } == mzero
        // mzero.bind(f) = mzero
        // mzero.plus(m) == m
        // m.plus(mzero) = m
    }

    void testMaybe() {
        def m = new Maybe()

    }

}

