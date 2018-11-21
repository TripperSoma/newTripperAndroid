package rankhep.com.tripper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_airplane_reservation.view.*
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.MainActivity

class AirplaneReservationFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.airplaneReservationToolbarMenuBtn -> {
                (activity as MainActivity).openDrawer()
            }
        }
    }

    companion object {
        fun newInstance(): AirplaneReservationFragment {
            return AirplaneReservationFragment()
        }

        fun newInstance(name: String): AirplaneReservationFragment {
            return AirplaneReservationFragment().apply {
                arguments = Bundle().apply {
                    putString("string", name)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_airplane_reservation, null)
        v.run {
            airplaneReservationToolbarMenuBtn.setOnClickListener(this@AirplaneReservationFragment)
            searchBtn.setOnClickListener {
                Toast.makeText(context, "서비스 준비중입니다.", Toast.LENGTH_SHORT).show()
            }
        }
        return v
    }
}