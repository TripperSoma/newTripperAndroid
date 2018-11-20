package rankhep.com.tripper.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hotel_reservation.view.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.HotelInfoActivity
import rankhep.com.tripper.activity.MainActivity
import rankhep.com.tripper.adapter.TrippingHotelAdapter
import rankhep.com.tripper.model.HotelModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelReservationFragment : Fragment(), View.OnClickListener, TrippingHotelAdapter.OnItemClickedListener {
    lateinit var v: View
    val items = ArrayList<HotelModel>()
    val mAdapter = TrippingHotelAdapter(items, this)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbarMenuBtn -> {
                (activity as MainActivity).openDrawer()
            }
        }
    }


    companion object {
        fun newInstance(): HotelReservationFragment {
            return HotelReservationFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_hotel_reservation, null)
        v.toolbarMenuBtn.setOnClickListener(this)

        v.myPlanHotelRecommend.run {
            layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapter
        }
        NetworkHelper.networkInstance.getHotel().enqueue(object : Callback<List<HotelModel>> {
            override fun onFailure(call: Call<List<HotelModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("get hotel", t.message)
            }

            override fun onResponse(call: Call<List<HotelModel>>, response: Response<List<HotelModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        items.addAll(it)
                        mAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("get hotel", "" + response.code())
                }
            }

        })


        return v
    }

    override fun onItemClicked(v: View, position: Int) {
        items[position].placenum
        val intent = Intent(context, HotelInfoActivity::class.java).apply {
            putExtra("placenum", items[position].placenum)
        }
        startActivity(intent)
    }

}