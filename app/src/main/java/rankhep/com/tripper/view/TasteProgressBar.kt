package rankhep.com.tripper.custom

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import rankhep.com.tripper.R


class TasteProgressBar : ConstraintLayout {
    private lateinit var progressBar: SeekBar
    private lateinit var goodText: TextView
    private lateinit var goodImg: ImageView
    private lateinit var badText: TextView
    private lateinit var badImg: ImageView
    private lateinit var tasteTitle: TextView
    private lateinit var tasteSubTitle: TextView

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
        val v = li.inflate(R.layout.custom_taste_progressbar, this, false)
        addView(v)
        progressBar = v.findViewById(R.id.seek_bar)
        goodText = v.findViewById(R.id.good_text)
        goodImg = v.findViewById(R.id.good_img)
        badImg = v.findViewById(R.id.bad_img)
        badText = v.findViewById(R.id.bad_text)
        tasteSubTitle = v.findViewById(R.id.taste_sub_title)
        tasteTitle = v.findViewById(R.id.taste_title)
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
//        val bg_resID = typedArray.getResourceId(R.styleable.TasteProgressBar_title, R.drawable.login_naver_bg)
//        bg.setBackgroundResource(bg_resID)
//        val symbol_resID = typedArray.getResourceId(R.styleable.TasteProgressBar_subTitle, R.drawable.login_naver_symbol)
//        symbol.setImageResource(symbol_resID)
//        val textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0)
//        text.setTextColor(textColor)
//        val text_string = typedArray.getString(R.styleable.LoginButton_text)
//        text.text = text_string
        tasteTitle.text = typedArray.getString(R.styleable.TasteProgressBar_title)
        tasteSubTitle.text = typedArray.getString(R.styleable.TasteProgressBar_subTitle)

        typedArray.recycle()
    }

    fun getTaste(): Int {

        return progressBar.progress
    }

}
