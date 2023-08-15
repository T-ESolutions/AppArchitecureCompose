package app.te.core.filepicker.stream

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

internal class FileStreamer : Streamer {

    companion object {
        private const val BASE_BUFFER_SIZE = 8192
    }

    override fun copyFile(inputStream: InputStream, outputStream: OutputStream) {
        try {
            val bytes = ByteArray(BASE_BUFFER_SIZE)
            var count: Int
            while (inputStream.read(bytes).also { count = it } != -1) {
                outputStream.write(bytes, 0, count)
            }
            close(inputStream)
            close(outputStream)
        } catch (e: Exception) {
            print(e)
        }
    }

    private fun close(stream: OutputStream?) {
        if (stream != null) {
            try {
                stream.flush()
            } catch (ignored: IOException) {
            }
            try {
                stream.close()
            } catch (ignored: IOException) {
            }
        }
    }

    private fun close(stream: InputStream?) {
        if (stream != null) {
            try {
                stream.close()
            } catch (ignored: IOException) {
            }
        }
    }

}