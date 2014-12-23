package org.pittsburghtank.groovy_utils.monad

import groovy.transform.TypeChecked

@TypeChecked
public interface Functor<F> {
    F fmap(Closure f)
}

@TypeChecked
public interface Monad<M> {
    M unit(Object v)
    M bind(Closure f)
}

@TypeChecked
public interface Monoid<M> {
    M mempty()                 // identity
    M mappend(M a, M b)
    M mconcat(Iterable<M> a)
    // mconcat = a.reverse().inject(this.mempty(), this.&mappend)
}
