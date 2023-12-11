package hu.ait.nonprofitapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.ait.nonprofitapp.R
import java.io.Serializable

@Entity(tableName = "nonprofittable")
data class NonprofitItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "category") var category: NonprofitType,
    @ColumnInfo(name = "isLiked") var isLiked: Boolean,
    @ColumnInfo(name = "image") var image: String
    //TODO what else do we want? do we want a picture in the card?
) : Serializable

enum class NonprofitType {
    None, Environmental, Poverty, Rights_Justice, Education, Arts_Culture;

//    /*
    //returns a drawable
    fun getIcon(): Int {
        return if (this == Environmental) R.drawable.baseline_emoji_nature_24
        else if (this == Poverty) R.drawable.baseline_fastfood_24
        else R.drawable.baseline_menu_book_24
    }

//     */

}

