package org.example.functional.chapter4

interface FunctionsFunctional {
    val add: Any
    val printNum: Any
    val triple: Any
    val longestOf: Any
}

class AnonymousFunctionalTypeSpecified : FunctionsFunctional {
    override val add: (Int, Int) -> Int = fun(num1, num2) = num1 + num2
    override val printNum: (Int) -> Unit = fun(num) = println(num)
    override val triple: (Int) -> Int = fun(num) = num * 3
    override val longestOf: (String, String, String) -> String =
        fun(str1, str2, str3) = maxOf(str1, str2, str3, compareBy { it.length })
}

class AnonymousFunctionalTypeInferred : FunctionsFunctional {
    override val add = fun(num1: Int, num2: Int) = num1 + num2
    override val printNum = fun(num: Int) = println(num)
    override val triple = fun(num: Int) = num * 3
    override val longestOf =
        fun(str1: String, str2: String, str3: String) = maxOf(str1, str2, str3, compareBy { it.length })
}

class LambdaFunctionalTypeSpecified : FunctionsFunctional {
    override val add: (Int, Int) -> Int = { num1, num2 -> num1 + num2 }
    override val printNum: (Int) -> Unit = { num -> println(num) }
    override val triple: (Int) -> Int = { num -> num * 3 }
    override val longestOf: (String, String, String) -> String =
        { str1, str2, str3 -> maxOf(str1, str2, str3, compareBy { it.length }) }
}

class LambdaFunctionalTypeInferred : FunctionsFunctional {
    override val add = { num1: Int, num2: Int -> num1 + num2 }
    override val printNum = { num: Int -> println(num) }
    override val triple = { num: Int -> num * 3 }
    override val longestOf = { str1: String, str2: String, str3: String ->
        maxOf(str1, str2, str3, compareBy { it.length })
    }
}

class FunctionReference : FunctionsFunctional {
    override val add: (Int, Int) -> Int = Int::plus
    override val printNum: (Int) -> Unit = ::println
    override val triple: (Int) -> Int = ::multiplyByThree
    override val longestOf: (String, String, String) -> String = ::longestOf

    fun multiplyByThree(num: Int): Int {
        return num * 3
    }

    fun longestOf(str1: String, str2: String, str3: String): String {
        return listOf(str1, str2, str3).maxByOrNull { it.length }!!
    }
}

class FunctionMemberReference : FunctionsFunctional {
    override val add: (Int, Int) -> Int = this::add
    override val printNum: (Int) -> Unit = this::printNum
    override val triple: (Int) -> Int = this::triple
    override val longestOf: (String, String, String) -> String = this::longestOf

    private fun add(num1: Int, num2: Int): Int = num1 + num2

    private fun printNum(num: Int) {
        print(num)
    }

    private fun triple(num: Int): Int = num * 3

    private fun longestOf(str1: String, str2: String, str3: String): String = listOf(str1, str2, str3)
        .maxByOrNull { it.length }!!
}

class BoundedFunctionReference : FunctionsFunctional {
    private val classic = FunctionsClassic()

    override val add: (Int, Int) -> Int = classic::add
    override val printNum: (Int) -> Unit = classic::printNum
    override val triple: (Int) -> Int = classic::triple
    override val longestOf: (String, String, String) -> String = classic::longestOf
}
