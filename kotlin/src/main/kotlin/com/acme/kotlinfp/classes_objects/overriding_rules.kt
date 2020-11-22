package classes_objects

interface A {
    fun foo() {
        println("prints A")
    }
}

interface B {
    fun foo() {
        println("prints B")
    }
}

class C : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }
}