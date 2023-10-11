package com.senemyalin.jetpackcomposetraining.common

interface Mapper<I, O> {
    fun map(input: I?): O
}