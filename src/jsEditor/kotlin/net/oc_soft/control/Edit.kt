/*
 * Copyright 2022 oc-soft
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.oc_soft.control

import react.ReactElement
import react.PropsWithChildren
import react.ReactNode
import react.Children
import react.helper.toReactElement

import org.w3c.dom.events.Event

import kotlinx.js.Symbol
import kotlin.collections.ArrayList


import net.oc_soft.Table
import net.oc_soft.action.Entity


/**
 * handle edit procedure
 */
class Edit(
    /**
     * plugin entitty action
     */
    val entity: Entity = Entity()) {

    

    /**
     * bind this object into html elements
     */
    fun bind() {
        entity.bind()
    }

    /**
     * unbind this object from html elements
     */
    fun unbind() {
        entity.unbind()
    }

    /**
     * edit procedure
     */
    fun run(
        settings: dynamic,
        originalTableEdit: dynamic): dynamic {
        var tableContainer = originalTableEdit.edit(settings) 
            as ReactElement<PropsWithChildren>
 
        if (settings.isSelected) {
            Table.findTableElementInReact(tableContainer)?.let {
                tableContainer = entity.updateOperation(
                    settings,
                    tableContainer) 
                tableContainer = Other.updateDropdown(
                    settings, entity, tableContainer)
                tableContainer = Settings.updateInspectorConrols(
                    settings, tableContainer)
                Color.updateSetting(settings)

            }
        }
        val result = tableContainer 
        return result
    }
}


// vi: se ts=4 sw=4 et:
