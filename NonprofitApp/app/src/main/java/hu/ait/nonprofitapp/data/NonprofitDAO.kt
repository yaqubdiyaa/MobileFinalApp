package hu.ait.nonprofitapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//TODO: figure out how many of these we actually need
@Dao
interface NonprofitDAO {

    @Query("SELECT * from nonprofittable")
    fun getAllShopping(): Flow<List<NonprofitItem>>

    /*
    @Query("SELECT * FROM nonprofittable WHERE category = 'Food'")
    fun getAllFood(): Flow<List<NonprofitItem>>



    @Query("SELECT * from nonprofittable WHERE id = :id")
    fun getItem(id: Int): Flow<NonprofitItem>
 */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: NonprofitItem)

    @Update
    suspend fun update(item: NonprofitItem)

    @Delete
    suspend fun delete(item: NonprofitItem)

    @Query("DELETE from nonprofittable")
    suspend fun deleteAll()

}