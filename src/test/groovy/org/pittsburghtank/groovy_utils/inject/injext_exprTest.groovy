package org.pittsburghtank.groovy_utils.inject_expr

import org.pittsburghtank.groovy_utils.inject_expr.Inject
import org.pittsburghtank.groovy_utils.inject_expr._

class CaseOfTest extends GroovyTestCase {

    void testInjectionInjected() {
        def x = new Object()

        Inject.injectCaseOf()
        assert 'caseOf' in x.metaClass.methods*.name
        assert x.metaClass.respondsTo(x, "caseOf", Iterable)

        Inject.removeCaseOf()
        try {
            x.caseOf(null) 
        }
        catch (MissingMethodException) {
        }
    }

    void testClosureToClosure() {
        Inject.injectCaseOf()

        def fizzBuzzes = (1..15).collect { it.caseOf([
            [{it % 15 == 0}, {'FizzBuzz'}],
            [{it %  3 == 0}, {'Fizz'}],
            [{it %  5 == 0}, {'Buzz'}],
            [{true},         {it.toString()}]
        ])}

        def expectedFizzBuzzes = ['1', '2', 'Fizz', '4', 'Buzz', 'Fizz', '7', '8', 'Fizz',
                'Buzz', '11', 'Fizz', '13', '14', 'FizzBuzz']
        assert fizzBuzzes == expectedFizzBuzzes

        Inject.removeCaseOf()
    }

    void testClosureToValue() {
        Inject.injectCaseOf()

        def fizzBuzzes = (1..15).collect { it.caseOf([
            [{it % 15 == 0}, 'FizzBuzz'],
            [{it %  3 == 0}, 'Fizz'],
            [{it %  5 == 0}, 'Buzz'],
            [{true},         {it.toString()}]
        ])}

        def expectedFizzBuzzes = ['1', '2', 'Fizz', '4', 'Buzz', 'Fizz', '7', '8', 'Fizz',
                'Buzz', '11', 'Fizz', '13', '14', 'FizzBuzz']
        assert fizzBuzzes == expectedFizzBuzzes

        Inject.removeCaseOf()
    }

    void testValueToClosure() {
        Inject.injectCaseOf()

        def primes = (1..10).collect { it.caseOf([
            [2,      {'two'}],
            [3,      {'three'}],
            [5,      {'five'}],
            [7,      {'seven'}],
            [{true}, {it.toString()}]
        ])}

        def expectedPrimes = ['1', 'two', 'three', '4', 'five', '6', 'seven', '8', '9', '10']
        assert primes == expectedPrimes

        Inject.removeCaseOf()
    }

    void testValueToValue() {
        Inject.injectCaseOf()

        def primes = (1..10).collect { it.caseOf([
            [2,      'two'],
            [3,      'three'],
            [5,      'five'],
            [7,      'seven'],
            [{true}, {it.toString()}]
        ])}

        def expectedPrimes = ['1', 'two', 'three', '4', 'five', '6', 'seven', '8', '9', '10']
        assert primes == expectedPrimes

        Inject.removeCaseOf()
    }

    void testMixed() {
        Inject.injectCaseOf()

        def primes = (1..10).collect { it.caseOf([
            [2,         'two'],
            [{it == 3}, 'three'],
            [5,         {'five'}],
            [{it == 7}, {'seven'}],
            [{true},    {it.toString()}]
        ])}

        def expectedPrimes = ['1', 'two', 'three', '4', 'five', '6', 'seven', '8', '9', '10']
        assert primes == expectedPrimes

        Inject.removeCaseOf()
    }

    void testDefault() {
        Inject.injectCaseOf()

        def primes = (1..10).collect { it.caseOf([
            [2,   'two'],
            [3,   'three'],
            [5,   'five'],
            [7,   'seven'],
            [_,   {it.toString()}]
        ])}

        def expectedPrimes = ['1', 'two', 'three', '4', 'five', '6', 'seven', '8', '9', '10']
        assert primes == expectedPrimes

        Inject.removeCaseOf()
    }

    void testDefault2() {
        Inject.injectCaseOf()

        def primes = (1..10).collect { it.caseOf([
            [2,        'two'],
            [3,        'three'],
            [5,        'five'],
            [7,        'seven'],
            [new _(),  {it.toString()}]
        ])}

        def expectedPrimes = ['1', 'two', 'three', '4', 'five', '6', 'seven', '8', '9', '10']
        assert primes == expectedPrimes

        Inject.removeCaseOf()
    }

    void testNoDefault() {
        Inject.injectCaseOf()

        try {
            def primes = (1..10).collect { it.caseOf([
                [2,              'two'],
                [3,              'three'],
                [5,              'five'],
                [7,              'seven']
            ])}
        }
        catch (Exception) {
        }

        Inject.removeCaseOf()
    }

    void testNoDefaultIsOk() {
        Inject.injectCaseOf()

        def primes = (1..10).collect { it.caseOf([
            [2,              'two'],
            [3,              'three'],
            [5,              'five'],
            [7,              'seven'],
        ], false)}

        def expectedPrimes = [1, 'two', 'three', 4, 'five', 6, 'seven', 8, 9, 10]
        assert primes == expectedPrimes

        Inject.removeCaseOf()
    }

}
