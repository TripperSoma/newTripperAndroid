package rankhep.com.tripper.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_tripping.view.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.CalenderActivity
import rankhep.com.tripper.activity.MainActivity
import rankhep.com.tripper.activity.ReviewEditActivity
import rankhep.com.tripper.adapter.TrippingAdapter
import rankhep.com.tripper.model.TrippingListModel
import rankhep.com.tripper.util.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrippingFragment : Fragment(), View.OnClickListener, TrippingAdapter.OnClickListener {

    override fun onChangeButtonClickedListener(v: View, position: Int, item: TrippingListModel) {
        val intent = Intent(context, CalenderActivity::class.java)
        intent.run {
            putExtra("planSeqNum", item.seqnum)
            putExtra("isEdit", true)
        }
        startActivityForResult(intent, 900)
    }

    override fun onReviewButtonClickedListener(v: View, position: Int, item: TrippingListModel) {
        val intent = Intent(context, ReviewEditActivity::class.java).apply {
            putExtra("reviewnum", item.reviewnum)
        }
        startActivityForResult(intent, 333)
    }

    override fun onDeleteButtonClickedListener(v: View, position: Int, item: TrippingListModel) {

        NetworkHelper.networkInstance.deleteSchedule(items[position].seqnum).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
                Log.e("Error", t.message)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    items.removeAt(position)
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private lateinit var mAdapter: TrippingAdapter
    private val items = ArrayList<TrippingListModel>()
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.trippingToolbarMenuBtn -> {
                (activity as MainActivity).openDrawer()
            }
        }
    }

    companion object {
        fun newInstance(): TrippingFragment {
            return TrippingFragment()
        }

        fun newInstance(name: String): TrippingFragment {
            return TrippingFragment().apply {
                arguments = Bundle().apply {
                    putString("string", name)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tripping, null)
        mAdapter = TrippingAdapter(items, this@TrippingFragment)

        v.run {
            trippingToolbarMenuBtn.setOnClickListener(this@TrippingFragment)
            trippingList.adapter = mAdapter
        }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkHelper.networkInstance.getScheduleList(SharedPrefManager(view.context).getUserData().user_num).enqueue(object : Callback<List<TrippingListModel>> {
            override fun onFailure(call: Call<List<TrippingListModel>>, t: Throwable) {
                t.message
                Log.e("get tripping fail", t.message)
            }

            override fun onResponse(call: Call<List<TrippingListModel>>, response: Response<List<TrippingListModel>>) {
                if (response.code() == 200) {
                    items.clear()
                    response.body()?.let { items.addAll(it) }
                    mAdapter.notifyDataSetChanged()
                } else {
                    Log.e("get tripping fail", response.code().toString())
                }
            }

        })
    }
}