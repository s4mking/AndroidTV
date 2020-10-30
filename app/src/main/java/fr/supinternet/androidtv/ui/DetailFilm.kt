package fr.supinternet.androidtv.ui

import android.os.Bundle

import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import fr.supinternet.androidtv.data.network.NetworkManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class DetailFilm : DetailsSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val boxOffice = NetworkManager.getBoxOffice()
        }
        GlobalScope.launch {
            val waitings = NetworkManager.getAnticipatedMovies()
        }
        val fragmentAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = fragmentAdapter

        fragmentAdapter.add(ListRow(HeaderItem(0 , "Les sorties"), fragmentAdapter))
        fragmentAdapter.add(ListRow(HeaderItem(0 , "Attendus"), fragmentAdapter))

    }
}

