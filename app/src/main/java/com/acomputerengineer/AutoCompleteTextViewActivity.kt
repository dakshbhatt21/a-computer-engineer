package com.acomputerengineer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.acomputerengineer.Adapters.AutoCompleteTextViewAdapter
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.*


class AutoCompleteTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_text_view)

        //1. Display Array of strings to AutoCompleteTextView
        val movies = arrayOf("Avengers: Endgame", "Captain Marvel", "Shazam!", "Spider-Man: Far From Home", "Dark Phoenix", "Hellboy", "Glass", "Reign of the Superman", "Brightburn")
        val adapter1 = ArrayAdapter(this, android.R.layout.select_dialog_item, movies)


        //2. Display Array of data class to AutoCompleteTextView
        //if you want to show data from your model data class without implementing whole adapter, just override toString() in your data class
        val movieObjects = arrayOf(Movie("Avengers: Endgame", 2019), Movie("Captain Marvel", 2019), Movie("Shazam!", 2019), Movie("Spider-Man: Far From Home", 2019), Movie("Dark Phoenix", 2019), Movie("Hellboy", 2019), Movie("Glass", 2019), Movie("Reign of the Superman", 2019), Movie("Brightburn", 2019))
        val adapter2 = ArrayAdapter(this, android.R.layout.select_dialog_item, movieObjects)


        //3. Use custom adapter to change layout and filter preferences of AutoCompleteTextView
        val movieObjects1 = arrayOf(Movie("Avengers: Endgame", 2019), Movie("Captain Marvel", 2019), Movie("Shazam!", 2019), Movie("Spider-Man: Far From Home", 2019), Movie("Dark Phoenix", 2019), Movie("Hellboy", 2019), Movie("Glass", 2019), Movie("Reign of the Superman", 2019), Movie("Brightburn", 2019))
        val adapter3 = AutoCompleteTextViewAdapter(this, R.layout.item_auto_complete_text_view, movieObjects1)

        //start search from 1st character
        actv.threshold = 1
        actv.setAdapter(adapter3)

        actv.setOnItemClickListener { adapterView, view, i, l ->
            val movie = adapterView.getItemAtPosition(i)
            //for adapter2 and adapter3
            if (movie is Movie) {
                Toast.makeText(this, "Selected item: ".plus(movie.name), Toast.LENGTH_LONG).show()
            }
            //for adapter1
            else if (movie is String) {
                Toast.makeText(this, "Selected item: ".plus(movie), Toast.LENGTH_LONG).show()
            }
        }
    }

    data class Movie(
            val name: String,
            val year: Int
    ) {
        override fun toString(): String {
            return name.plus(" - ").plus(year)
        }
    }


}
