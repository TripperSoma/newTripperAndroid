package rankhep.com.tripper.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_place_search.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.PlaceSearchListAdapter
import rankhep.com.tripper.model.PlaceSearchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceSearchActivity : AppCompatActivity(), PlaceSearchListAdapter.OnItemClickListener {

    val placeItem = ArrayList<PlaceSearchModel>()
    private lateinit var mAdapter: PlaceSearchListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)

        mAdapter = PlaceSearchListAdapter(placeItem, this)
        searchList.adapter = mAdapter


        val version = intent.getIntExtra("version", 1)
        val seqnum = intent.getIntExtra("seqNum", 0)
        val day = intent.getIntExtra("dayNum", 1)
        Log.e("asd", "$version : $seqnum : $day")

        NetworkHelper.networkInstance.searchPlaceByCategory(version, seqnum, day)
                .enqueue(object : Callback<List<PlaceSearchModel>> {
                    override fun onFailure(call: Call<List<PlaceSearchModel>>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("asd", t.message)
                    }

                    override fun onResponse(call: Call<List<PlaceSearchModel>>, response: Response<List<PlaceSearchModel>>) {
                        if (response.isSuccessful) {
                            placeItem.clear()
                            response.body()?.let { placeItem.addAll(it) }
                            mAdapter.notifyDataSetChanged()
                        } else {

                        }
                        Log.e("asd", response.code().toString())

                    }

                })
        toolbarMenuBtn.setOnClickListener {
            finish()
        }

        searchBtn.setOnClickListener {
            Log.e("asd", "Asdas")
            NetworkHelper.networkInstance.search(searchEditText.text.toString())
                    .enqueue(object : Callback<List<PlaceSearchModel>> {
                        override fun onFailure(call: Call<List<PlaceSearchModel>>, t: Throwable) {
                            t.printStackTrace()
                            Log.e("search Error", t.message)
                        }

                        override fun onResponse(call: Call<List<PlaceSearchModel>>, response: Response<List<PlaceSearchModel>>) {
                            if (response.isSuccessful) {
                                placeItem.clear()
                                mAdapter.notifyDataSetChanged()
                                response.body()?.let { placeItem.addAll(it) }
                            } else {
                                Log.e("aa", "" + response.code())
                            }
                        }

                    })
        }
    }

    override fun onItemClick(v: View, position: Int) {
        val intent = Intent(this@PlaceSearchActivity, TouristInfoActivity::class.java)
        intent.putExtra("placeNum", placeItem[position].placenum)
        startActivityForResult(intent, 222)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 222) {
            if (resultCode == Activity.RESULT_OK) {
                setResult(Activity.RESULT_OK, data)
                finish()
            } else {

            }
        }
    }
}
