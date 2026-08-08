package com.vald3nir.toolkit.core.services.threads

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val scope: JobScope)

enum class JobScope { Default, IO }