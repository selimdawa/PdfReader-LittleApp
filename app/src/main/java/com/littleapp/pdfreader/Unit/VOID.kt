package com.littleapp.pdfreader.Unit

import android.Manifest
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object VOID {
    fun Intent1(context: Context, c: Class<*>?) {
        val intent = Intent(context, c)
        context.startActivity(intent)
    }

    fun plainTextShareIntent(chooserTitle: String?, text: String?): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return Intent.createChooser(intent, chooserTitle)
    }

    fun fileShareIntent(chooserTitle: String?, fileName: String?, fileUri: Uri?): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "application/pdf"
        intent.putExtra(Intent.EXTRA_STREAM, fileUri)
        intent.clipData = ClipData(fileName, arrayOf("application/pdf"), ClipData.Item(fileUri))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        return Intent.createChooser(intent, chooserTitle)
    }

    fun canWriteToDownloadFolder(context: Context?): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            true
        } else ContextCompat.checkSelfPermission(
            context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun readBytesToEnd(inputStream: InputStream): ByteArray {
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(8 * 1024)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
        return output.toByteArray()
    }

    fun writeBytesToFile(directory: File?, fileName: String?, fileContent: ByteArray?) {
        val file = File(directory, fileName!!)
        FileOutputStream(file).use { stream -> stream.write(fileContent) }
    }
}