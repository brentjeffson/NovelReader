package project.novelreader.ui.main

import BoxNovelScraper
import Novel
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.novelreader.R
import project.novelreader.ui.info.ReaderActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment() {

    private var searchAdapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildSearchRecycler(view)
    }

    fun showNovel(url: String?) {
        val readIntent = Intent(this.context, ReaderActivity::class.java).apply {
            putExtra("novel_url", url)
        }
        startActivity(readIntent)
    }

    fun searchNovels(keyword: String) {

    }

    private fun buildSearchRecycler(view: View) {
        val searchRecycler = view.findViewById<View>(R.id.searchRecycler) as RecyclerView
        searchAdapter = SearchAdapter(this, arrayListOf())
        searchRecycler.apply {
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL))
            adapter = searchAdapter
        }
    }
}

class SearchAdapter(private val homeFragment: HomeFragment, private var searchList: List<Novel>?)
    : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return SearchHolder(view)
    }

    override fun getItemCount(): Int {
        searchList?.apply {
            return this.size
        }
        return -1
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchHolder, position: Int) {
        searchList?.apply {
            val novel = this[position]
            holder.title.text = novel.title
            holder.novelUrl = novel.url
        }
    }

    inner class SearchHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        internal var title = view.findViewById<View>(R.id.searchTitle) as TextView
        internal var novelUrl: String? = null

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            homeFragment.showNovel(novelUrl)
        }
    }
}