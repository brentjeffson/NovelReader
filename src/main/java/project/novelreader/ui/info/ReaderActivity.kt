package project.novelreader.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_reader.*
import project.novelreader.R

class ReaderActivity : AppCompatActivity() {

    private var novelUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)

        val intent = getIntent()
        novelUrl = intent.getStringExtra("novel_url")
        novelUrl?.apply {
            changeFragment(NovelInfoFragment(this))
        }
    }

    private fun changeFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, it)
                .commit()
            println("Debug: Changing Fragment...")
        }
    }
}
