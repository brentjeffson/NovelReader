package project.novelreader.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.novelreader.R

class NovelListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize ui widgets, setup lambdas, listeners and more
        return inflater.inflate(R.layout.fragment_novel_list, container, false)
    }

//SETUPS
    private fun setupNovelList() {

    }

}

//class NovelListAdapter(
//    private val mainActivity: MainActivity,
//    private val novelList: List<Novel>)
//    : RecyclerView.Adapter<NovelListAdapter.ListItemHolder> {
//
//}