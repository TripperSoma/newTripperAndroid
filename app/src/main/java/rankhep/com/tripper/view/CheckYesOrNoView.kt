package rankhep.com.tripper.custom

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import rankhep.com.tripper.R

class CheckYesOrNoView : ConstraintLayout {

    private lateinit var titleText: TextView
    private lateinit var subTitleText: TextView
    private lateinit var yes: RadioButton
    private lateinit var img: ImageView

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
        val v = li.inflate(R.layout.custom_check_yes_or_no, this, false)
        addView(v)
        titleText = v.findViewById(R.id.title)
        subTitleText = v.findViewById(R.id.sub_title)
        yes = v.findViewById(R.id.yes)
        img = v.findViewById(R.id.img)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckYesOrNoView)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckYesOrNoView, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val imgSrc = typedArray.getResourceId(R.styleable.CheckYesOrNoView_imgSrc, R.drawable.ic_accessible_black_24dp)
        img.setBackgroundResource(imgSrc)

//        val symbol_resID = typedArray.getResourceId(R.styleable.TasteProgressBar_subTitle, R.drawable.login_naver_symbol)
//        symbol.setImageResource(symbol_resID)
//        val textColor = typedArray.getColor(R.styleable.LoginButton_textColor, 0)
//        text.setTextColor(textColor)
//        val text_string = typedArray.getString(R.styleable.LoginButton_text)
//        text.text = text_string

        titleText.text = typedArray.getString(R.styleable.CheckYesOrNoView_withTitle)
        subTitleText.text = typedArray.getString(R.styleable.CheckYesOrNoView_withSubTitle)

        typedArray.recycle()
    }

    public fun getIsChecked():Boolean = yes.isChecked
}