package com.acomputerengineer.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.annotation.LayoutRes
import com.acomputerengineer.AutoCompleteTextViewActivity
import com.acomputerengineer.databinding.ItemAutoCompleteTextViewBinding

class AutoCompleteTextViewAdapter(private val c: Context, @LayoutRes private val layoutResource: Int, private val movies: Array<AutoCompleteTextViewActivity.Movie>) :
        ArrayAdapter<AutoCompleteTextViewActivity.Movie>(c, layoutResource, movies) {

    var filteredMovies: List<AutoCompleteTextViewActivity.Movie> = listOf()

    override fun getCount(): Int = filteredMovies.size

    override fun getItem(position: Int): AutoCompleteTextViewActivity.Movie = filteredMovies[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemAutoCompleteTextViewBinding
        val view: View

        if (convertView == null) {
            binding = ItemAutoCompleteTextViewBinding.inflate(LayoutInflater.from(c), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemAutoCompleteTextViewBinding
            view = convertView
        }

        binding.tvMovieName.text = filteredMovies[position].name
        binding.tvMovieYear.text = filteredMovies[position].year.toString()

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                filteredMovies = filterResults.values as List<AutoCompleteTextViewActivity.Movie>

                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty())
                    movies.asList()
                else
                    movies.filter {
                        it.name.toLowerCase().contains(queryString) || it.year.toString().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}
