package project.novelreader.ui.info

import Chapter
import Novel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_novel_info.*
import kotlinx.android.synthetic.main.item_chapter.view.*
import project.novelreader.R

class NovelInfoFragment(val novel_id: Int) : Fragment() {

    private var chapterAdapter: ChapterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_novel_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun buildChapterRecycler() {

    }

    private fun getNovel(id: Int): Novel {
        TODO("Get novel from database")
    }

    private fun updateContent(novel: Novel) {
        infoTitle.text = novel.title

    }
}

class ChapterAdapter(val activity: ReaderActivity, val chapterList: List<Chapter>?) : RecyclerView.Adapter<ChapterAdapter.ChapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ChapterHolder(view)
    }

    override fun getItemCount(): Int {
        return chapterList!!.size
    }

    override fun onBindViewHolder(holder: ChapterHolder, position: Int) {
        chapterList?.run {
            val chapter = this[position]
        }
    }

    inner class ChapterHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        internal var title = view.chapterTitle

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            TODO("Open content reader for chapters")
        }
    }
}
