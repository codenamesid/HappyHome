package com.happyhome.logging

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.startup.Initializer
import timber.log.Timber
import java.util.Locale

class LoggingInitializer : Initializer<Timber.Tree> {
    override fun create(context: Context): Timber.Tree {
        val isDebuggable = 0 != context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        if (isDebuggable) {
            Timber.plant(DebugLogger)
        } else {
            Timber.plant(ReleaseLogger)
        }
        return Timber.asTree()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}

object DebugLogger : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            Locale.getDefault(),
            "%s:%s.%d",
            element.methodName,
            super.createStackElementTag(element),
            element.lineNumber
        )
    }
}

object ReleaseLogger : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

    }
}

fun Any.logd(message: String) {
    Timber.tag(this.javaClass.simpleName).d(message)
}

fun Any.logi(message: String) {
    Timber.tag(this.javaClass.simpleName).i(message)
}

fun Any.loge(message: String? = null, error: Throwable? = null) {
    Timber.tag(this.javaClass.simpleName).e(error, message)
}
