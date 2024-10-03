package com.acomputerengineer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.text.SpannableString
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.effect.OverlayEffect
import androidx.media3.effect.TextOverlay
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.transformer.Composition
import androidx.media3.transformer.EditedMediaItem
import androidx.media3.transformer.Effects
import androidx.media3.transformer.ExportException
import androidx.media3.transformer.ExportResult
import androidx.media3.transformer.ProgressHolder
import androidx.media3.transformer.Transformer
import androidx.media3.transformer.Transformer.PROGRESS_STATE_NOT_STARTED
import androidx.media3.ui.PlayerView
import com.google.common.collect.ImmutableList
import java.io.File


@OptIn(UnstableApi::class)
class AddTextOnVideoActivity : ComponentActivity() {

    @kotlin.OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transformer = Transformer.Builder(this).build()

        setContent {
            // add top bar
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Add Text on Video") },
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                    )
                }
            ) { padding ->
                PickVideoButton(modifier = Modifier.padding(padding), transformer = transformer)
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun PickVideoButton(modifier: Modifier, transformer: Transformer) {
    val context = LocalContext.current
    val videoUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            videoUri.value = result.data?.data
        }
    }
    val text = remember { mutableStateOf("") }
    val isGeneratedVideoWithText = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isExported = remember { mutableStateOf(false) }
    val isExportCompleted = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "video/*"
                }
                launcher.launch(intent)
            }) {
            Text("Pick Video")
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Original Preview:"
        )

        PlayVideoFromUri(
            modifier = Modifier.padding(top = 8.dp),
            videoUri = videoUri.value
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Edited Preview:"
        )

        TextField(
            modifier = Modifier.padding(top = 8.dp),
            value = text.value,
            onValueChange = { text.value = it },
            enabled = videoUri.value != null
        )

        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                keyboardController?.hide()
                isGeneratedVideoWithText.value = true
            },
            enabled = videoUri.value != null && text.value.isNotBlank()
        ) {
            Text("Generate Video")
        }

        if (isGeneratedVideoWithText.value) {
            PlayVideoFromUriWithText(
                modifier = Modifier.padding(top = 8.dp),
                videoUri = videoUri.value,
                text = text.value
            )
        }

        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                if (videoUri.value != null && text.value.isNotBlank()) {
                    exportResultVideo(
                        videoUri = videoUri,
                        text = text,
                        context = context,
                        transformer = transformer,
                        onCompleted = {
                            isExported.value = false
                            isExportCompleted.value = true
                        },
                        onError = { exportException ->
                            Log.e("AComputerEngineer", "error: $exportException")
                            isExported.value = false
                        }
                    )

                    isExported.value = true
                }
            },
            enabled = videoUri.value != null && text.value.isNotBlank()
        ) {
            Text("Export Video")
        }

        ProgressDialog(isExported.value, transformer)

        LaunchedEffect(isExportCompleted.value) {
            if (isExportCompleted.value) {
                Toast.makeText(context, "Video exported successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

private fun exportResultVideo(
    videoUri: MutableState<Uri?>,
    text: MutableState<String>,
    context: Context,
    transformer: Transformer,
    onCompleted: () -> Unit,
    onError: (ExportException) -> Unit
) {
    val mediaItem = MediaItem.Builder()
        .setUri(videoUri.value)
        .build()

    val textEffect = TextOverlay.createStaticTextOverlay(
        SpannableString(text.value),
    )

    val effects = Effects(
        listOf(),
        listOf(OverlayEffect(ImmutableList.of(textEffect))),
    )

    val editedMediaItem = EditedMediaItem.Builder(mediaItem)
        .setEffects(effects)
        .build()

    val outputFile = File(context.filesDir, "test.mp4")
    transformer.start(editedMediaItem, outputFile.absolutePath)
    transformer.addListener(object : Transformer.Listener {
        override fun onCompleted(composition: Composition, exportResult: ExportResult) {
            onCompleted()
        }

        override fun onError(
            composition: Composition,
            exportResult: ExportResult,
            exportException: ExportException
        ) {
            onError(exportException)
        }
    })
}

@Composable
fun PlayVideoFromUri(modifier: Modifier, videoUri: Uri?) {
    if (videoUri == null) {
        Box(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
        }
    } else {
        val context = LocalContext.current
        val player = ExoPlayer.Builder(context).build()
        val mediaItem = MediaItem.Builder().setUri(videoUri).build()
        player.setMediaItem(mediaItem)
        player.prepare()
        DisposableEffect(Unit) {
            onDispose {
                player.release()
            }
        }
        AndroidView(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth(),
            factory = {
                PlayerView(context).apply {
                    this.player = player
                }
            }
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun PlayVideoFromUriWithText(modifier: Modifier, videoUri: Uri?, text: String) {
    val context = LocalContext.current
    if (videoUri == null) {
        Box(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
        }
    } else {
        val mediaItem = MediaItem.Builder()
            .setUri(videoUri)
            .build()

        val textEffect = TextOverlay.createStaticTextOverlay(
            SpannableString(text),
        )

        val player = ExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.setVideoEffects(listOf(OverlayEffect(ImmutableList.of(textEffect))))
                exoPlayer.prepare()
            }

        DisposableEffect(Unit) {
            onDispose {
                player.release()
            }
        }
        AndroidView(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth(),
            factory = {
                PlayerView(context).apply {
                    this.player = player
                }
            }
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun ProgressDialog(isVisible: Boolean, transformer: Transformer) {
    val progress = remember { mutableIntStateOf(0) }
    val mainHandler = Handler(getMainLooper())
    val progressHolder = ProgressHolder()
    mainHandler.post(
        object : Runnable {
            override fun run() {
                if (transformer.getProgress(progressHolder) != PROGRESS_STATE_NOT_STARTED) {
                    // this will be 0 to 100
                    progress.intValue = progressHolder.progress
                    mainHandler.postDelayed(this, 500)
                }
            }
        })
    if (isVisible) {
        Dialog(onDismissRequest = {}) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium)
            ) {
                LinearProgressIndicator(
                    progress = {
                        progress.intValue.toFloat() / 100
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
