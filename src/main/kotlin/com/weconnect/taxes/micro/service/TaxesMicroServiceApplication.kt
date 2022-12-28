package com.weconnect.taxes.micro.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaxesMicroServiceApplication

fun main(args: Array<String>) {
    runApplication<TaxesMicroServiceApplication>(*args)
}
