package project.novelreader.ui.info

import Novel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_reader.*
import project.novelreader.R

class ReaderActivity : AppCompatActivity() {

    private var novel_id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)

        val intent = getIntent()
        novel_id = intent.getIntExtra("novel_id", -1)

        if (novel_id != -1) {
            changeFragment(NovelInfoFragment(novel_id))
        } else {
            TODO("Go back to MainActivity")
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
