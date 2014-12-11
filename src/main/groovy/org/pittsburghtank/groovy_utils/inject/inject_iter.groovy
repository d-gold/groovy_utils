package org.pittsburghtank.groovy_utils.iter


Iterable.metaClass.zip = { Iterable i ->
    def len = ([delegate, i])*.size.min()
    (0..<len).collect { x -> [delegate[x], i[x]] }
}

Iterable.metaClass.zipLongest = { Iterable i ->
    def len = ([delegate, i]*.size).min()
    "bar"
}


