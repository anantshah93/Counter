package iostudio.`in`.counterapp.di.module

import iostudio.`in`.counterapp.repository.MainRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(androidContext(), get())
    }
}