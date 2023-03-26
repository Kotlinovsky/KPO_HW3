package io.kotlinovsky.mas.agents

import io.kotlinovsky.mas.behaviours.ActualizeMenuBehaviour
import io.kotlinovsky.mas.consts.ReceiversTypesIds
import io.kotlinovsky.mas.entities.config.DishCardsConfig
import io.kotlinovsky.mas.entities.config.MenuDishesConfig
import io.kotlinovsky.mas.extensions.addToRegistry
import io.kotlinovsky.mas.extensions.removeFromRegistry
import jade.core.Agent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

/**
 * Агент меню.
 */
class MenuAgent : Agent() {

    @OptIn(ExperimentalSerializationApi::class)
    private val dishes by lazy {
        File("jsons/menu_dishes.json").inputStream()
            .use { Json.decodeFromStream<MenuDishesConfig>(it) }
            .dishes
            .associateBy { it.menuDishId }
            .toMutableMap()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val dishCards by lazy {
        File("jsons/dish_cards.json").inputStream()
            .use { Json.decodeFromStream<DishCardsConfig>(it) }
            .dishCards
            .associateBy { it.cardId }
    }

    override fun setup() {
        addToRegistry(name = "Menu agent", type = ReceiversTypesIds.MENU_TYPE)
        addBehaviour(ActualizeMenuBehaviour())
    }

    override fun takeDown() {
        removeFromRegistry()
    }
}