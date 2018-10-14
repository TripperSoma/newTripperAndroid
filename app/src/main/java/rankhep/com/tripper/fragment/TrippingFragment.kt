package rankhep.com.tripper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tripping.view.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.MainActivity
import rankhep.com.tripper.adapter.TrippingAdapter
import rankhep.com.tripper.model.PlanModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrippingFragment : Fragment(), View.OnClickListener {
    private lateinit var mAdapter: TrippingAdapter
    private val items = ArrayList<PlanModel>()
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
        mAdapter = TrippingAdapter(items)

        v.run {
            trippingToolbarMenuBtn.setOnClickListener(this@TrippingFragment)
            trippingList.adapter = mAdapter
        }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkHelper.networkInstance.getScheduleList("string").enqueue(object : Callback<List<PlanModel>> {
            override fun onFailure(call: Call<List<PlanModel>>, t: Throwable) {
                t.message
            }

            override fun onResponse(call: Call<List<PlanModel>>, response: Response<List<PlanModel>>) {
                if (response.code() == 200) {
                    response.body()?.let { items.addAll(it) }
                    mAdapter.notifyDataSetChanged()
                } else {
                    Log.e("asd", response.message())
                }
            }

        })
    }
}