package com.insecureshop

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.OpenableColumns
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.InputStream
import java.io.OutputStream


class ChooserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooser)
        if (intent.extras != null) {
            var uri = intent.getParcelableExtra<Parcelable>("android.intent.extra.STREAM") as Uri
            uri = Uri.fromFile(File(uri.toString()))
            makeTempCopy(uri, getFilename(uri))
        }

    }
    private fun makeTempCopy(fileUri: Uri, original_filename: String?): Uri? {
        try {
            val path : String = Environment.getExternalStorageDirectory().absolutePath + File.separator + "insecureshop";
            val directory =
                File(path)
            if (!directory.exists()) {
                val directoryBoolean = directory.mkdirs()
            }
            val fileTemp = File(path, original_filename)
            val fileBoolean = fileTemp.createNewFile()
            val out = Uri.fromFile(fileTemp)

            val inputStream: InputStream? = contentResolver.openInputStream(fileUri)
            val outputStream: OutputStream? = contentResolver.openOutputStream(out)
            val buffer = ByteArray(8192)
            while (true) {
                val len: Int? = inputStream?.read(buffer)
                if (len != -1) {
                    len?.let { outputStream?.write(buffer, 0, it) }
                }
            }
            return out
        } catch (e: Exception) {
            return null
        }
    }

    fun getFilename(uri : Uri): String? {
//        val uri = intent.data
        var fileName: String? = null
        val context = applicationContext
        val scheme = uri!!.scheme
        if (scheme == "file") {
            fileName = uri.lastPathSegment
        } else if (scheme == "content") {
            val proj =
                arrayOf(OpenableColumns.DISPLAY_NAME)
            val contentUri: Uri? = null
            val cursor: Cursor? = context.contentResolver.query(uri, proj, null, null, null)
            if (cursor != null && cursor.getCount() !== 0) {
                val columnIndex: Int = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                fileName = cursor.getString(columnIndex)
            }
        }
        return fileName
    }

}