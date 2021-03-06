package jp.kztproject.rewardedtodo.data.ticket

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named

class TicketRepository @Inject constructor(
        @Named("default") private val preferences: SharedPreferences
) : ITicketRepository {

    companion object {
        private const val NUMBER_OF_TICKET = "number_of_ticket"
        //TODO put into domain layer
        private const val NUMBER_OF_TICKETS_REQUIRED_FOR_LOTTERY = 1
    }

    override fun addTicket(numberOfTicket: Float) {
        val current = getNumberOfTicket()
        preferences.edit()
                .putFloat(NUMBER_OF_TICKET, current + numberOfTicket)
                .apply()
    }

    override fun consumeTicket() {
        val current = getNumberOfTicket()
        preferences.edit()
                .putFloat(NUMBER_OF_TICKET, current - NUMBER_OF_TICKETS_REQUIRED_FOR_LOTTERY)
                .apply()
    }

    override fun getNumberOfTicket(): Float {
        return preferences.getFloat(NUMBER_OF_TICKET, 0F)
    }
}