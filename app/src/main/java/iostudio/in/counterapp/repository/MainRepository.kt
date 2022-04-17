package iostudio.`in`.counterapp.repository

import android.content.Context
import iostudio.`in`.counterapp.pref.IOPref


class MainRepository(private val context: Context, private val pref: IOPref) {

    fun getCounts(): Int = pref.getInt(context, IOPref.PreferenceKey.COUNTER, 0)

    fun updateCount(counts: Int) = pref.saveInt(context, IOPref.PreferenceKey.COUNTER, counts + 1)

}