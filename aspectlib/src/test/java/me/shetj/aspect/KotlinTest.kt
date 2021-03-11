package me.shetj.aspect

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class KotlinTest {

    suspend fun test():String{
        delay(100)
        println("test")
        return  "info"
    }


    fun run (){
       runBlocking {
            test()
        }
    }
}