package com.example.practiceplacement.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun copyToDownloads(context: Context, assetFileName: String): File {
    val inputStream = context.assets.open(assetFileName)
    val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    if (!downloadsDir.exists()) downloadsDir.mkdirs()

    val outFile = File(downloadsDir, assetFileName)

    inputStream.use { input ->
        FileOutputStream(outFile).use { output ->
            input.copyTo(output)
        }
    }

    return outFile
}

fun openImage(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "image/png")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(intent, "Открыть изображение"))
}

fun openFile(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    try {
        context.startActivity(Intent.createChooser(intent, "Открыть документ"))
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "Нет приложения для открытия .docx", Toast.LENGTH_SHORT).show()
    }
}

