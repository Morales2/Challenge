package com.morales.marvelapp.di

import javax.inject.Scope

@Scope
@Retention
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class FragmentScoped