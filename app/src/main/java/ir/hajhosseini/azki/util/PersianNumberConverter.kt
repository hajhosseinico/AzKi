package ir.hajhosseini.azki.util

/**
 * A simple temporary class for changing english numbers to farsi numbers
 * In production products we use PERSIAN-CHAR-ONLY fonts for doing this
 */
class PersianNumberConverter {
    fun setPersianNumbers(str: String): String? {
        return str
            .replace("0", "۰")
            .replace("1", "۱")
            .replace("2", "۲")
            .replace("3", "۳")
            .replace("4", "۴")
            .replace("5", "۵")
            .replace("6", "۶")
            .replace("7", "۷")
            .replace("8", "۸")
            .replace("9", "۹")
    }
}