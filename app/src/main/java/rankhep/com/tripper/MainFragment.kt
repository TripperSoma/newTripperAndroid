package rankhep.com.tripper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

    private lateinit var v: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_main, null)

        v.apply {
            main_toolbar_menu_btn.setOnClickListener(this@MainFragment)

        }
        return v
    }
}