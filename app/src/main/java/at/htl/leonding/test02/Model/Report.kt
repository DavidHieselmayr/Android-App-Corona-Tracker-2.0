package at.htl.leonding.test02.Model

import java.time.LocalDateTime

data class Report(
    var id: String,
    var dateAndTime: LocalDateTime,
    var isPositiv: Boolean,
    var office: String
) {
    override fun toString(): String {
        return "Report(id='$id', dateAndTime=$dateAndTime, isPositiv=$isPositiv, office='$office')"
    }
}