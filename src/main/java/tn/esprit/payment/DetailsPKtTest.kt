package tn.esprit.payment

import org.junit.Test as Test1

class DetailsPKtTest {

    @Test1
    fun testGetLogementInformation() {
        val logementId = "655f0dc32ac054e28735519e"

        val result = getLogementInformation(logementId)


        if (result != null) {
            val prix = result.first
            val numberOfRooms = result.second

            println("Prix: $prix")
            println("Number of Rooms: $numberOfRooms")
        } else {
            error("Error fetching logement information")
        }
    }


}
