
package tn.esprit.payment
import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


fun getLogementInformation(logementId: String): Pair<Double, Int>? {
    val logementUrl = URL("http://localhost:3000/logements/logements/$logementId")
    val logementConnection = logementUrl.openConnection() as HttpURLConnection
    logementConnection.requestMethod = "GET"
    logementConnection.setRequestProperty("Accept", "application/json")

    return try {
        val logementResponseCode = logementConnection.responseCode
        if (logementResponseCode == HttpURLConnection.HTTP_OK) {
            val logementReader = BufferedReader(InputStreamReader(logementConnection.inputStream))
            val logementResponse = StringBuilder()
            var logementLine: String?
            while (logementReader.readLine().also { logementLine = it } != null) {
                logementResponse.append(logementLine)
            }
            logementReader.close()

            val logementJson = JsonParser.parseString(logementResponse.toString()).asJsonObject
            val prix = logementJson.get("prix").asDouble
            val nombreChambre = logementJson.get("nombreChambre").asInt

            Pair(prix, nombreChambre)
        } else {
            null
        }
    } finally {
        logementConnection.disconnect()
    }
}
