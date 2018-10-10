package rankhep.com.tripper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_setting.view.*
import rankhep.com.tripper.R
import rankhep.com.tripper.activity.MainActivity

class SettingFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.trippingToolbarMenuBtn -> {
                (activity as MainActivity).openDrawer()
            }
        }
    }

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }

        fun newInstance(name: String): SettingFragment {
            return SettingFragment().apply {
                arguments = Bundle().apply {
                    putString("string", name)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_setting, null)
        Log.e("asd", "asd")
        v.run {
            toolbarMenuBtn.setOnClickListener(this@SettingFragment)
            profileSettingBtn.setOnClickListener(this@SettingFragment)
            tasteSetingBtn.setOnClickListener(this@SettingFragment)
            noticeBtn.setOnClickListener(this@SettingFragment)
        }

        return v
    }
}