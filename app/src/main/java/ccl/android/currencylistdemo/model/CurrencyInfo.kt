package ccl.android.currencylistdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ccl.android.currencylistdemo.TABLE_CURRENCY
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_CURRENCY)
data class CurrencyInfo(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "symbol")
    @SerializedName("symbol")
    val symbol: String
)

