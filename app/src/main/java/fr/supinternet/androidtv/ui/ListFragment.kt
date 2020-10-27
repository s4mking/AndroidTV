package fr.supinternet.androidtv.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import fr.supinternet.androidtv.R
import fr.supinternet.androidtv.data.network.NetworkManager
import fr.supinternet.androidtv.data.network.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query


class ListFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = fragmentAdapter

        GlobalScope.launch {
            val boxOffice = NetworkManager.getBoxOffice()
        }

    }
}


class CardViewHolder(view: View) : Presenter.ViewHolder(view) {

    val card: ImageCardView = view as ImageCardView

    init {
        card.setMainImageDimensions(view.resources.getDimensionPixelSize(R.dimen.card_width),
            view.resources.getDimensionPixelSize(R.dimen.card_height))
    }

    fun loadImage(url: String) {
        Glide.with(view)
            .load(url)
            .into(card.mainImageView)
    }
}

class ListPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return CardViewHolder(ImageCardView(parent?.context))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        // On récupère l'objet de la requête
        val movie = item as Movie

        // On récupère le ViewHolder et l'ImageCardView
        val holder = viewHolder as CardViewHolder
        val img = holder.card

        img.titleText = movie.name
        img.contentText = movie.rating.toString()
        movie.posterURL?.let { holder.loadImage(it) }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        // Nous ne l'utiliserons pas
    }
}
