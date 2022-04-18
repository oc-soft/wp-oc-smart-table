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



import kotlinx.browser.window

import net.oc_soft.control.Edit
import net.oc_soft.control.Save

/**
 * application
 */
class App (
    /**
     * operation to edit 
     */
    val edit: Edit = Edit(),
    /**
     * opration to save
     */
    val save: Save = Save()){

    /**
     * bind this object into wordpress widgets
     */
    fun bind() {
        registerTable()
        edit.bind()
    }

    /**
     * detach this object from wordpress widgets
     */
    fun unbind() {
        edit.unbind()
    }


    /**
     * register extend table
     */
    fun registerTable() {
        val originalSettings = wordpress.blocks.unregisterBlockType(
            "core/table")
        if (originalSettings != null) {
            wordpress.blocks.registerBlockType(
                "core/table", extendTable(originalSettings))
        }
    }

    /**
     * extend table edit
     */
    fun extendTable(originalTableSettings: dynamic): dynamic {

        val extendedEdit: (dynamic)-> dynamic = {
            originalTableEdit ->
            val editFunc: (dynamic)-> dynamic = {
                setting ->
                editTable(setting, originalTableEdit)
            }
            val saveFunc: (dynamic)-> dynamic = {
                saveTable(it, originalTableEdit)
            }
            

            val attributes = js(
                "Object.assign({}, originalTableEdit.attributes)")
            attributes.ocSmartTable = object {
                @JsName("default")
                val default = false
                @JsName("type")
                val type = "boolean"
            }
            attributes.ocSmartTableRequest = object {
                @JsName("default")
                val defualt = false
                @JsName("type")
                val type = "boolean"
            }
            attributes.ocSmartTableInheritColorsAndStyles = object {
                @JsName("default") 
                val default = false
                @JsName("type")
                val type = "boolean"
            }

            val settings = js("Object.assign({}, originalTableEdit)")
            settings.edit = editFunc
            settings.save = saveFunc
            settings.attributes = attributes
            settings 
        }
       
        val result = wordpress.compose.createHigherOrderComponent(
            extendedEdit, "smartTableEdit")(originalTableSettings)

        return result 
    }

    /**
     * save table data
     */
    fun saveTable(
        settings: dynamic,
        originalTableEdit: dynamic): dynamic {
        return save.run(settings, originalTableEdit)
    }

    /**
     * edit table
     */
    fun editTable(
        settings: dynamic,
        originalTableEdit: dynamic): dynamic {
        return edit.run(settings, originalTableEdit)
    }


    /**
     * start this application
     */
    fun run() {
        window.addEventListener("load", {
            bind()
        }, object {
            @JsName("once")
            val once = true
        })

        window.addEventListener("unload", {
            unbind()
        }, object {
            @JsName("once")
            val once = true
        })
    }
}

// vi: se ts=4 sw=4 et:
