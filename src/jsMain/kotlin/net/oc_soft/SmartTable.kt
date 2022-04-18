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

package net.oc_soft

import kotlinx.browser.document
import kotlinx.browser.window

import kotlin.collections.MutableList
import kotlin.collections.ArrayList

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLTableElement
import org.w3c.dom.HTMLTableRowElement
import org.w3c.dom.HTMLTableCellElement
import org.w3c.dom.get
import org.w3c.dom.url.URL

class SmartTable {

    /**
     * get pdf element
     */
    val tableElements: Array<HTMLElement>
        get() {
            val elems = document.querySelectorAll(Site.tableQuery) 
            return Array<HTMLElement>(elems.length) {
                elems[it] as HTMLElement
            }
        }

    /**
     * table and definition list(generated by this object) pairs
     */
    val smartTables: MutableList<Array<HTMLElement>> =
        ArrayList<Array<HTMLElement>>()

    /**
     * bind this object into html elements
     */
    fun bind() {
        tableElements.forEach {
            if (it is HTMLTableElement) {
                updateSmartTables(it)
            }
        }
    }



    /**
     * unbind this object from html elements
     */
    fun unbind() {
        smartTables.forEach {
            it[1].remove()
        }
    }



    /**
     * update smart tables field in this object
     */
    fun updateSmartTables(table: HTMLTableElement) {
        createListsFromTable(table)?.let {
            smartTables.add(arrayOf(table, it))
            table.after(it)
        }
    }

    /**
     * create table replacement element
     */
    fun createListsFromTable(
        table: HTMLTableElement): HTMLElement? {
        
        val headerRow = table.tHead?.let {
            val rows = it.rows
            var row : HTMLTableRowElement? = null
            for (idx in 0 until rows.length) {
                val elem = rows[idx]
                if (elem is HTMLTableRowElement) {
                    row = elem 
                    break
                }
            }
            row
        }

        val colorStyle = getColorStyle(table)

        val createListElement: 
            (rowElement: HTMLTableRowElement) -> HTMLElement = 
        if (headerRow != null) {
            { createDefintionList(headerRow, it) }
        } else {
            { createUList(it) } 
        }
        val bodies = table.tBodies
        val container = document.createElement(Site.containerTag)
            as HTMLElement
        attachColorStyle(container, colorStyle)
        container.classList.add(*Site.containerClasses)
        for (bodyIdx in 0 until bodies.length) {
            val body = bodies[bodyIdx]
            body?.let {
                val elems = it.children
                for (idx in 0 until elems.length) {
                    val elem = elems[idx]
                    if (elem is HTMLTableRowElement) {
                        val containerElem = document.createElement(
                            Site.containerElementTag)
                        containerElem.append(createListElement(elem))
                        container.append(containerElem)
                    }
                }
            }
        }
        return container
    }


    /**
     * attach color style
     */
    fun attachColorStyle(
        element: HTMLElement,
        colorStyle: dynamic) {

        if (colorStyle.className) {
            
            val classNames = if (colorStyle.className is Array<*>) {
                colorStyle.className as Array<String>
            } else {
                when (jsTypeOf(colorStyle.className)) {
                "string" -> 
                    (colorStyle.className as String).split(" ").toTypedArray()
                else -> emptyArray<String>()
                }
            }
            classNames.forEach {
                element.classList.add(it)
            }
        }
        if (colorStyle.style && jsTypeOf(colorStyle.style) == "object") {
            val keys = js("Object.keys(colorStyle.style)") as Array<String>
            keys.forEach {
                element.style.setProperty(it, colorStyle.style[it])
            }
        }

    }
    
    /**
     * create definition list element from table row element
     */
    fun createDefintionList(
        headerRow: HTMLTableRowElement,
        rowElement: HTMLTableRowElement): HTMLElement {
        val dtCells = headerRow.cells
        val ddCells = rowElement.cells
        val result = document.createElement("dl") as HTMLElement

        for (idx in 0 until ddCells.length) {
            val ddCell = ddCells[idx]

            val ddContents = if (ddCell is HTMLTableCellElement) {
                ddCell.innerHTML
            } else {
                ""
            }
            val dtContents = if (idx < dtCells.length) {
                val dtCell = dtCells[idx]
                if (dtCell is HTMLTableCellElement) {
                    dtCell.innerHTML
                } else {
                    ""
                }
            } else {
                ""
            }
             
            val dtElem = document.createElement("dt")
            dtElem.innerHTML = dtContents
            val ddElem = document.createElement("dd")
            ddElem.innerHTML = ddContents
            result.append(dtElem)
            result.append(ddElem)
        }
        return result
    }

    /**
     * create unorder list element from table row element
     */
    fun createUList(
        rowElement: HTMLTableRowElement): HTMLElement {
        val cells = rowElement.cells
        val result = document.createElement("ul") as HTMLElement

        for (idx in 0 until cells.length) {
            val cell = cells[idx]

            val contents = if (cell is HTMLTableCellElement) {
                cell.innerHTML
            } else {
                ""
            }
             
            val liElem = document.createElement("li")
            liElem.innerHTML = contents
            result.append(liElem)
        }
        return result
    }


    /**
     * get table element
     */
    fun getColorStyle(
        table: HTMLTableElement): dynamic {

        return table.parentElement?.let {
            if (it is HTMLElement) {
                it.dataset["ocSmartTableColorStyle"]?.let {
                    Codec.htmlAttrToJson(window, it)
                } 
                    
            } else null
        }
    }
        
}

// vi: se ts=4 sw=4 et:
