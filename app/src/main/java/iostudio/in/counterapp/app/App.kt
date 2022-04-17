package iostudio.`in`.counterapp.app

import android.app.Application
import iostudio.`in`.counterapp.di.module.appModule
import iostudio.`in`.counterapp.di.module.repoModule
import iostudio.`in`.counterapp.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}
