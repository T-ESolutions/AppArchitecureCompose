package app.te.core.filepicker.core

import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import java.io.File
import java.lang.ref.WeakReference

interface FilePicker {

    /**
     * launching intent for picking image files.
     * This method should be called from activity and the result will be provided in the callback.
     *
     * Parameters:
     * @param onImagePicked Callback to receive the picker image result.
     */
    fun pickImage(onImagePicked: (Pair<Bitmap?, File?>?) -> Unit)

    /**
     * launching intent for picking images from gallery.
     * This method should be called from activity and the result will be provided in the callback.
     *
     * Parameters:
     * @param onImagePicked Callback to receive the picker gallery image result.
     */
    fun captureCameraImage(onImagePicked: (Pair<Bitmap?, File?>?) -> Unit)

    /**
     * launching intent for picking pdf files.
     * This method should be called from activity and the result will be provided in the callback.
     *
     * Parameters:
     * @param onPdfPicked Callback to receive the picker pdf result.
     */
    fun pickPdf(onPdfPicked: (Pair<String?, File?>?) -> Unit)

    /**
     * launching intent for picking files.
     * This method should be called from activity and the result will be provided in the callback.
     *
     * Parameters:
     * @param onFilePicked Callback to receive the picker file result.
     */
    fun pickFile(onFilePicked: (Pair<String?, File?>?) -> Unit)

    companion object {
        @JvmStatic
        fun getInstance(activity: ComponentActivity): FilePicker =
            StorageFilePicker(WeakReference(activity))
    }
}