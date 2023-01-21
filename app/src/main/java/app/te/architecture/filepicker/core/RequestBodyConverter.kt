package app.te.architecture.filepicker.core

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

internal class RequestBodyConverter : FileConverter {

    override fun toRequestBody(file: File,type: MediaType?): RequestBody {
        return file.asRequestBody(type)
    }

    companion object {
        @JvmStatic
        val instance: FileConverter by lazy { RequestBodyConverter() }
    }



}