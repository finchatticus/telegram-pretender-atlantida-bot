package ua.kpi.atlantida.properties

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.charset.Charset
import java.util.*

open class MyProperties(private val fileName: String) {

    private companion object {
        val CHARSET_UTF_8 = Charset.forName("UTF-8")!!
    }

    protected fun writeProperties(props: Properties) {
        FileOutputStream(fileName).use { output -> props.store(OutputStreamWriter(output, CHARSET_UTF_8), null) }
    }

    protected fun readProperties(key: String): String {
        val loader = Thread.currentThread().contextClassLoader
        val prop = Properties().apply {
            val inputStream = loader.getResourceAsStream(fileName)
            load(InputStreamReader(inputStream, "UTF-8"))
        }
        return prop.getProperty(key)
    }
}