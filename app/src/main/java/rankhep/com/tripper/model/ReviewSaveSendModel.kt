package rankhep.com.tripper.model

import com.google.gson.Gson

class ReviewSaveSendModel(val reviewnum: Int,
                          val reviews: ArrayList<ReviewDetail>,
                          val seqnum: Int) {
    companion object {
        fun toJson(reviewSaveSendModel: ReviewSaveSendModel): String = Gson().toJson(reviewSaveSendModel)

    }
}