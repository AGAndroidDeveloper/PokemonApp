package com.ankit.pokedoxapp.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import coil3.ImageLoader
import coil3.asDrawable
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        return withContext(Dispatchers.IO) { // Run in the background
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false) // Needed for Palette API
                .build()

            val imageResult = loader
                .execute(request)

            if (imageResult is SuccessResult) {
                imageResult.image.toBitmap()

            } else {
                null
            }
        }
    }


    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
        return mapOf(
            "vibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().vibrantSwatch
            ),
            "darkVibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            "onDarkVibrant" to parseBodyColor(
                color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            )
        )
    }

    private fun parseColorSwatch(color: Palette.Swatch?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        } else {
            "#000000"
        }
    }

    private fun parseBodyColor(color: Int?): String {
        return if (color != null) {
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        } else {
            "#FFFFFF"
        }
    }




    fun Bitmap.createPaletteAsync() : Int {
        var color : Int? = null
        Palette.from(this).generate { palette ->
           color =  palette?.dominantSwatch?.rgb?: androidx.compose.ui.graphics.Color.Gray.toArgb()
        }

        Log.d("Color", "createPaletteAsync: $color")
        return color!!
    }


    suspend fun getBitMapFromUrlImage(context: Context,url : String){
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .listener(onSuccess = { _,successResult ->
                val result = successResult as BitmapDrawable
                val bitmap = result.bitmap
                Log.e("TAG", "getBitMapFromUrlImage: $bitmap", )
            })
            .build()


       // request.execute(context)


    }



}