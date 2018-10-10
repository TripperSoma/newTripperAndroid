package rankhep.com.tripper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hotel_reservation.view.*
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.MainActivity

class HotelReservationFragment : Fragment(), View.OnClickListener {
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
        val v = inflater.inflate(R.layout.fragment_hotel_reservation, null)

        v.toolbarMenuBtn.setOnClickListener(this)
        return v
    }
}