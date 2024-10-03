package com.acomputerengineer

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acomputerengineer.MainActivity.PostAdapter.PostViewHolder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.setHasFixedSize(true)
        val llm = LinearLayoutManager(this@MainActivity)
        rv.layoutManager = llm

        val alPost = ArrayList<String>()
        alPost.add("SHAREDPREFERENCES IN ANDROID KOTLIN (WITH HOW TO REMEMBER USER LOGIN/SESSION EXAMPLE)")
        alPost.add("DIFFERENT COLORS FOR SELECTED TAB IN BOTTOMNAVIGATIONVIEW IN ANDROID KOTLIN(PROGRAMMATICALLY)")
        alPost.add("POPULATE AND MANIPULATE AUTOCOMPLETETEXTVIEW IN ANDROID")
        alPost.add("OPEN CHAT PAGE IN WHATSAPP FOR GIVEN NUMBER IN ANDROID")
        alPost.add("DISPLAY IMAGE GRID IN RECYCLERVIEW IN KOTLIN ANDROID")
        alPost.add("ROOM SQLITE DEMO WITH CRUD OPERATIONS IN ANDROID")
        alPost.add("DOWNLOAD IMAGE AND SAVE IT TO SDCARD(PHONE STORAGE) WITHOUT ANY LIBRARY IN ANDROID")
        alPost.add("CREATE PDF FILE AND SAVE IT TO SDCARD IN ANDROID")
        alPost.add("DRAW LINE USING FINGER ON CANVAS IN ANDROID")
        alPost.add("DISPLAY LIST IN ALERTDIALOG IN ANDROID(SIMPLE LIST, RADIO BUTTON LIST, CHECK BOX LIST)")
        alPost.add("DISPLAY IMAGE GRID IN RECYCLERVIEW IN ANDROID")
        alPost.add("PICK IMAGE FROM GALLERY BEFORE AND AFTER KITKAT VERSION IN ANDROID(UPDATED)")
        alPost.add("HOW TO DISPLAY CANVAS ON IMAGEVIEW AND SAVE CANVAS AS BITMAP AND STORE IN SDCARD IN ANDROID")
        alPost.add("DRAW CIRCLE SHAPE IN IMAGEVIEW IN ANDROID")
        alPost.add("LIMIT NUMBER RANGE IN EDITTEXT USING INPUTFILTER IN ANDROID")
        alPost.add("RESIZE IMAGE DURING DECODE FROM FILE TO BITMAP IN ANDROID(TO PREVENT OOM)")
        alPost.add("VARIOUS MATERIAL DESIGNS FOR BUTTON IN ANDROID")
        alPost.add("SHARE IMAGE TO WHATSAPP IN ANDROID")
        alPost.add("CRUD FUNCTIONS IN SQLITE IN ANDROID")
        alPost.add("ADD TEXT ON VIDEO")

        val adapter = PostAdapter(alPost)
        rv.adapter = adapter

        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            })
            .onSameThread()
            .check()
    }

    inner class PostAdapter internal constructor(var alPost: ArrayList<String>) :
        RecyclerView.Adapter<PostViewHolder>() {
        inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var cv: CardView = itemView.findViewById(R.id.cv)
            var tvName: TextView = itemView.findViewById(R.id.tv_name)

            init {
                cv.setOnClickListener {
                    val position = adapterPosition
                    when (position) {
                        0 -> {
                            val intentSharedPreferences =
                                Intent(this@MainActivity, SharedPreferencesActivity::class.java)
                            startActivity(intentSharedPreferences)
                        }

                        1 -> {
                            val intentBottomNavigationView =
                                Intent(this@MainActivity, BottomNavigationViewActivity::class.java)
                            startActivity(intentBottomNavigationView)
                        }

                        2 -> {
                            val intentAutoCompleteTextView =
                                Intent(this@MainActivity, AutoCompleteTextViewActivity::class.java)
                            startActivity(intentAutoCompleteTextView)
                        }

                        3 -> {
                            val intentOpenWhatsappNumber =
                                Intent(this@MainActivity, OpenWhatsappNumberActivity::class.java)
                            startActivity(intentOpenWhatsappNumber)
                        }

                        4 -> {
                            val intentImageGridKotlin = Intent(this@MainActivity, ImageGridKotlinActivity::class.java)
                            startActivity(intentImageGridKotlin)
                        }

                        5 -> {
                            val intentRoomSQLite = Intent(this@MainActivity, RoomSQLiteActivity::class.java)
                            startActivity(intentRoomSQLite)
                        }

                        6 -> {
                            val intentDownloadImage = Intent(this@MainActivity, DownloadImageActivity::class.java)
                            startActivity(intentDownloadImage)
                        }

                        7 -> {
                            val intentPDF = Intent(this@MainActivity, PDFActivity::class.java)
                            startActivity(intentPDF)
                        }

                        8 -> {
                            val intentDrawLineWithFinger =
                                Intent(this@MainActivity, DrawLineWithFingerActivity::class.java)
                            startActivity(intentDrawLineWithFinger)
                        }

                        9 -> {
                            val intentListAlertDialog = Intent(this@MainActivity, ListAlertDialogActivity::class.java)
                            startActivity(intentListAlertDialog)
                        }

                        10 -> {
                            val intentImageGrid = Intent(this@MainActivity, ImageGridActivity::class.java)
                            startActivity(intentImageGrid)
                        }

                        11 -> {
                            val intentPickImage = Intent(this@MainActivity, PickImageActivity::class.java)
                            startActivity(intentPickImage)
                        }

                        12 -> {
                            val intentCanvasDemo = Intent(this@MainActivity, CanvasDemoActivity::class.java)
                            startActivity(intentCanvasDemo)
                        }

                        13 -> {
                            val intentCircleImageView = Intent(this@MainActivity, CircleImageViewActivity::class.java)
                            startActivity(intentCircleImageView)
                        }

                        14 -> {
                            val intentLimitNumberRange = Intent(this@MainActivity, LimitNumberRangeActivity::class.java)
                            startActivity(intentLimitNumberRange)
                        }

                        15 -> {
                            val intentResizeImageDecodeBitmap =
                                Intent(this@MainActivity, ResizeImageDecodeBitmapActivity::class.java)
                            startActivity(intentResizeImageDecodeBitmap)
                        }

                        16 -> {
                            val intentMaterialDesignButtons =
                                Intent(this@MainActivity, MaterialDesignButtonsActivity::class.java)
                            startActivity(intentMaterialDesignButtons)
                        }

                        17 -> {
                            val intentShareImageWhatsapp =
                                Intent(this@MainActivity, ShareImageWhatsappActivity::class.java)
                            startActivity(intentShareImageWhatsapp)
                        }

                        18 -> {
                            val intentSqliteCRUD = Intent(this@MainActivity, SqliteCRUDActivity::class.java)
                            startActivity(intentSqliteCRUD)
                        }

                        19 -> {
                            val intentAddTextOnVideo = Intent(this@MainActivity, AddTextOnVideoActivity::class.java)
                            startActivity(intentAddTextOnVideo)
                        }
                    }
                }
            }
        }

        override fun getItemCount(): Int {
            return alPost.size
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_post, viewGroup, false)
            val pvh = PostViewHolder(v)
            return pvh
        }

        override fun onBindViewHolder(personViewHolder: PostViewHolder, i: Int) {
            personViewHolder.tvName.text = alPost[i].toString()
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
        }
    }
}
