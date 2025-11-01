package br.thiago.splitmateapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
import br.thiago.splitmateapp.domain.model.Split
import java.io.File
import java.io.FileOutputStream

/*
 * Função de extensão para extrair o valor do comprovante PIX de uma string.
 */
fun String.extractPixAmount(): Double {
    var index = this.indexOf("54")
    if (index == -1) return 0.0

    val lengthStr = this.substring(index + 2, index + 4)
    val length = lengthStr.toIntOrNull() ?: return 0.0

    val valueStr = this.substring(index + 4, index + 4 + length)
    return valueStr.toDoubleOrNull() ?: 0.0
}
/*
 * Função de extensão para compartilhar um comprovante como imagem.
 */
fun captureComposableAsBitmap(activity: Activity, split: Split, onCaptured: (Bitmap) -> Unit) {
    val container = FrameLayout(activity).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        visibility = View.INVISIBLE
    }

    val composeView = ComposeView(activity).apply {
        setContent { SplitReceiptCard(split) }
        setBackgroundColor(android.graphics.Color.WHITE)
    }

    container.addView(composeView)
    activity.addContentView(container, container.layoutParams)

    composeView.post {
        val bitmap = createBitmap(composeView.width, composeView.height)
        val canvas = Canvas(bitmap)
        composeView.draw(canvas)
        onCaptured(bitmap)

        // Remover view depois
        (container.parent as? ViewGroup)?.removeView(container)
    }
}

/*
 * Função de extensão para compartilhar um comprovante como imagem.
 */

fun Split.shareReceiptAsImage(activity: Activity) {
    captureComposableAsBitmap(activity, this) { bitmap ->
        try {
            val file = File(activity.cacheDir, "split_receipt.png")
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }

            val uri: Uri = FileProvider.getUriForFile(
                activity,
                "${activity.packageName}.provider",
                file
            )

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/png"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            activity.startActivity(Intent.createChooser(intent, "Compartilhar comprovante"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
