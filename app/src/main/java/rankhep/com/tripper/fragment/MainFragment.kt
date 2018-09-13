package rankhep.com.tripper.fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.MainActivity

class MainFragment : Fragment(), View.OnClickListener, AppBarLayout.OnOffsetChangedListener {

    private lateinit var v: View

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }

        fun newInstance(name: String): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putString("string", name)
                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_main, null)

        v.apply {
            main_toolbar_menu_btn.setOnClickListener(this@MainFragment)
            toolbar_container.addOnOffsetChangedListener(this@MainFragment)
        }
        return v
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.main_toolbar_menu_btn -> {
                (activity as MainActivity).openDrawer()
            }
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        Log.d("MainToolbarOffset", "" + verticalOffset)
        when {
            Math.abs(verticalOffset) == appBarLayout.totalScrollRange -> {  //닫혔을때
                changeActionBar(Color.BLACK)
            }
            verticalOffset == 0 -> {        //열렸을
                changeActionBar(Color.WHITE)
            }
            else -> {
                if (verticalOffset > -300)
                    changeActionBar(Color.WHITE)
                else
                    changeActionBar(Color.BLACK)
            }
        }
    }

    fun changeActionBar(color: Int) {
        val menuBtn: Drawable = resources.getDrawable(R.drawable.ic_menu)
        menuBtn.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        val searchBtn: Drawable = resources.getDrawable(R.drawable.ic_search_black_24dp)
        searchBtn.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        v.apply {
            main_toolbar_menu_btn.setImageDrawable(menuBtn)
            main_toolbar_search_btn.setImageDrawable(searchBtn)
            main_toolbar_title_text.setTextColor(color)
        }
    }
}