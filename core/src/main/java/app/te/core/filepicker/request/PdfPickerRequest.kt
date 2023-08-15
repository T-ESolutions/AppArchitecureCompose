package app.te.core.filepicker.request

import android.content.Intent
import android.net.Uri
import app.te.core.filepicker.decoder.Decoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

internal class PdfPickerRequest(
    private val decoder: Decoder,
    private val onPdfPicked: (Pair<String?, File?>?) -> Unit
) : PickerRequest {
    override val intent: Intent
        get() = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }

    override suspend fun invokeCallback(uri: Uri) {
        var result: Pair<String?, File?>? = null
        decoder.getStoragePDF(uri).collect { result = it }
        withContext(Dispatchers.Main) {
            onPdfPicked(result)
        }
    }
}