package rankhep.com.tripper.view

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import rankhep.com.tripper.R
import rankhep.com.tripper.model.Photo


class CustomGridImageView : ConstraintLayout {
    private lateinit var firstImageView: ImageView
    private lateinit var secondImageView: ImageView
    private lateinit var thirdImageView: ImageView
    private lateinit var moreImageText: TextView
    private lateinit var imgList: List<Any>
    private val imgViews = ArrayList<ImageView>()

    constructor(context: Context) : super(context) {
        initView()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs) {
        initView()
        getAttrs(attrs, defStyle)
    }

    private fun initView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val li = context.getSystemService(infService) as LayoutInflater
        val v = li.inflate(R.layout.custom_grid_image_view, this, false)
        addView(v)
        firstImageView = v.findViewById(R.id.firstImg)
        secondImageView = v.findViewById(R.id.secondImg)
        thirdImageView = v.findViewById(R.id.thirdImg)
        moreImageText = v.findViewById(R.id.moreImgText)

        imgViews.apply {
            add(firstImageView)
            add(secondImageView)
            add(thirdImageView)
        }
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TasteProgressBar)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TasteProgressBar, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        typedArray.recycle()
    }

    private fun checkImageLength() {
        when (imgList.size) {
            0 -> {
                firstImageView.visibility = View.GONE
            }
            1 -> {
                secondImageView.visibility = View.GONE
                thirdImageView.visibility = View.GONE
            }
            2 -> {
                secondImageView.visibility = View.VISIBLE
                thirdImageView.visibility = View.GONE
            }
            3 -> {
                secondImageView.visibility = View.VISIBLE
                thirdImageView.visibility = View.VISIBLE
            }
            else -> {
                secondImageView.visibility = View.VISIBLE
                thirdImageView.visibility = View.VISIBLE
                moreImageText.visibility = View.VISIBLE
            }
        }
    }


    fun setImageList(imgs: List<Any>) {
        imgList = imgs
        checkImageLength()
        for (i in 0 until if (imgs.size >= 3) 3 else imgs.size) {
            if (imgList[i] is String) {
                Picasso.get()
                        .load(imgList[i] as String)
                        .into(imgViews[i])
            }else if(imgList[i] is Photo){
                Picasso.get()
                        .load((imgList[i] as Photo).bucket)
                        .into(imgViews[i])
            }
        }

        if (imgs.size > 3)
            moreImageText.text = "+${imgs.size - 3}"
    }
}