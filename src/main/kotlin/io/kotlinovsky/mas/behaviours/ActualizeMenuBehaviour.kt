package io.kotlinovsky.mas.behaviours

import io.kotlinovsky.mas.consts.ConversationsIds
import io.kotlinovsky.mas.entities.messages.UpdateMenuRequest
import io.kotlinovsky.mas.extensions.deserialize
import jade.core.behaviours.CyclicBehaviour
import jade.lang.acl.MessageTemplate

/**
 * Действие актуализации меню.
 * Блокирует/разблокирует блюда в зависимости от наличия продуктов.
 */
class ActualizeMenuBehaviour(
    private val updateQuantity: (Long, Double) -> Unit
) : CyclicBehaviour() {

    override fun action() {
        val message = agent.receive(MessageTemplate.MatchConversationId(ConversationsIds.ACTUALIZE_MENU)) ?: return
        val requests = message.deserialize<List<UpdateMenuRequest>>()

        requests.forEach {
            it.newQuantity
        }

//        requests.forEach {
//            it.newQuantity
//        }
    }
}