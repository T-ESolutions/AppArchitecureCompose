package app.te.architecture.filepicker.stream

import java.io.InputStream
import java.io.OutputStream

internal interface Streamer {
    fun copyFile(inputStream: InputStream, outputStream: OutputStream)
}