package hu.ait.nonprofitapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "nonprofittable")
data class NonprofitItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "category") var category: NonprofitType,
    @ColumnInfo(name = "isLiked") var isLiked: Boolean
    //TODO what else do we want? do we want a picture in the card?
) : Serializable

enum class NonprofitType {
    None, Environmental, Poverty, Rights_Justice, Education, Arts_Culture;

    /*
    //returns a drawable
    fun getIcon(): Int {
        return if (this == Book) R.drawable.book
        else if (this == Food) R.drawable.food
        else R.drawable.electronics
    }

     */

}

