package project.novelreader.ui.main

import Chapter
import Novel
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_novel_list.*
import project.novelreader.R
import project.novelreader.ui.info.ReaderActivity

class NovelListFragment : Fragment() {

    private val novelList = ArrayList<Novel>()
//    private var novelListRecyclerView: RecyclerView? = null
    private var novelListAdapter: NovelListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_novel_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        novelListRecyclerView = view.findViewById<View>(R.id.novelListRecycler) as RecyclerView
        setupNovelList()
        prePopulate(20)
    }

    fun showNovel(position: Int) {
        println("debug: showing ${novelList[position]}")
        val readIntent = Intent(this.context, ReaderActivity::class.java).apply {
            putExtra("novel_id", "1")
        }
        startActivity(readIntent)
    }

    private fun prePopulate(n: Int = 10) {
        for (count in 0..n) {
            novelList.add(Novel(
                "All Hail The King",
                "https://boxnovel.com/novel/hail-the-king/",
                "https://boxnovel.com/wp-content/uploads/2019/02/hail-the-king-193x278.jpg",
                arrayListOf(Chapter(
                    "Chapter 1275.4 - The Grand Finale (Part Four)",
                    "https://boxnovel.com/novel/hail-the-king/chapter-1275-4"))
            ))
        }
        novelListAdapter!!.notifyDataSetChanged()
    }
//SETUPS
    private fun setupNovelList() {
        novelListAdapter = NovelListAdapter(this, novelList)
        val gridLayoutManager = GridLayoutManager(this.context, 3)
        novelListRecycler?.apply {
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this.context, GridLayoutManager.HORIZONTAL))
            adapter = novelListAdapter
//            layoutManager = gridLayoutManager
        }
    }
}

class NovelListAdapter(
    private val novelListFragment: NovelListFragment,
    private val novelList: List<Novel>)
    : RecyclerView.Adapter<NovelListAdapter.ListItemHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NovelListAdapter.ListItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_novel, parent, false)
        return ListItemHolder(view)
    }

    override fun getItemCount(): Int {
        if(novelList != null) {
            return novelList.size
        }
        // error
        return -1
    }

    override fun onBindViewHolder(holder: NovelListAdapter.ListItemHolder, position: Int) {
        val novel = novelList[position]
        holder.title.text = novel.title
        holder.chapterCounter.text = "0/${novel.chapters.size}"
    }

    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        internal var title = view.findViewById<View>(R.id.novelTitle) as TextView
        internal var bookCover = view.findViewById<View>(R.id.novelBookCover) as ImageView
        internal var chapterCounter = view.findViewById<View>(R.id.novelChapterCounter) as TextView

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            novelListFragment.showNovel(adapterPosition)
        }
    }
}