package com.insecureshop.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.insecureshop.util.Prefs


class InsecureShopProvider : ContentProvider() {

    companion object {
        var uriMatcher: UriMatcher? = null
        const val URI_CODE: Int = 100
    }

    override fun onCreate(): Boolean {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher?.addURI("com.insecureshop.provider", "insecure", URI_CODE)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        if (uriMatcher?.match(uri) == URI_CODE) {
            val cursor = MatrixCursor(arrayOf("username", "password"))
            cursor.addRow(arrayOf<String>(Prefs.username!!, Prefs.password!!))
            return cursor
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}