package rankhep.com.tripper.model

import java.io.Serializable

data class LocalDateTime(
        var chronology: Chronology,
        var dayOfMonth: Int,
        var dayOfWeek: String,
        var dayOfYear: Int,
        var hour: Int,
        var minute: Int,
        var month: String,
        var monthValue: Int,
        var nano: Int,
        var second: Int,
        var year: Int):Serializable {
}