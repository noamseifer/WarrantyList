package com.example.warrantylist.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

import java.time.* // LocalDate and Period are part of this class


data class WarrantyItem(
    val productName: String, // example: "laptop"
    val purchaseDate:LocalDate, // 23/01/2023
    val duration: Period, // Period(years,months,days)
    val productSpecification: String? = null, // example "Del xps 15" permitted to be null

)
{
    val expirationDate: LocalDate = purchaseDate.plus(duration)
}

// later needs to be converted to Mutable list of to enable client to add/remove warranty items
val warranties = listOf(
    WarrantyItem("Laptop", LocalDate.of(2023, 11, 18), Period.of(2,1,0),"Del xp15"),
    WarrantyItem("Refrigerator", LocalDate.of(2021, 1, 2), Period.of(3,1,0),null),
    WarrantyItem("Laptop", LocalDate.of(2022, 11, 30), Period.of(0,36,0),null),
)
