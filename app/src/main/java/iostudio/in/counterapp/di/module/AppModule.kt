package iostudio.`in`.counterapp.di.module

import android.content.Context
import iostudio.`in`.counterapp.pref.IOPref
import org.koin.dsl.module

val appModule = module {
    single { provideIOPref() }

}

private fun provideIOPref() = IOPref()
