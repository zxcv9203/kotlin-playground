package org.example.section2

import java.io.BufferedReader
import java.io.FileReader

/**
 * 코틀린에서는 try-with-resources 구문이 없는대신 언어적 특징을 활용해 close를 호출해줍니다.
 *
 * use 확장함수를 사용하면 BufferedReader를 사용한 후 자동으로 close를 호출해줍니다.
 */
fun readFile(path: String) = BufferedReader(FileReader(path)).use { reader ->
    println(reader.readLine())
}