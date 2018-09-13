package rankhep.com.tripper.fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.MainActivity
import rankhep.com.tripper.adapter.MainReviewAdapter
import rankhep.com.tripper.model.MainReviewListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), View.OnClickListener, AppBarLayout.OnOffsetChangedListener {

    private lateinit var v: View
    private var mAdapter = MainReviewAdapter()
    private var items = ArrayList<MainReviewListData>()

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
        mAdapter.items = items

        getMainListData()
        v.apply {
            main_toolbar_menu_btn.setOnClickListener(this@MainFragment)
            toolbar_container.addOnOffsetChangedListener(this@MainFragment)
            review_list.run{
                layoutManager = GridLayoutManager(context, 2)
                adapter = mAdapter
            }
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

    private fun changeActionBar(color: Int) {
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

    private fun getMainListData() {
        NetworkHelper.networkInstance.getMainReviewData().enqueue(object : Callback<List<MainReviewListData>> {
            override fun onFailure(call: Call<List<MainReviewListData>>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<MainReviewListData>>?, response: Response<List<MainReviewListData>>?) {
                Log.e("asd", "" + response?.code())
                response?.run {
                    when (this.code()) {
                        200 -> {
                            this.body()?.let { items.addAll(it) }
                            mAdapter.notifyDataSetChanged()
                        }
                        else -> {
                        }
                    }
                }
            }

        })
    }
}