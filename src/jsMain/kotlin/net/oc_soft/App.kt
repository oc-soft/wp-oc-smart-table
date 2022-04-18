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
import kotlinx.browser.document


/**
 * application
 */
class App(
    val smartTable: SmartTable = SmartTable()) {


    /**
     * bind this into html elements
     */
    fun bind() {
        
        smartTable.bind()
    }

    /**
     * unbind this from html elements
     */
    fun unbind() {
        smartTable.unbind()
    }

    /**
     * run application
     */
    fun run() {
        window.addEventListener("load",
            { this.bind() },
            object {
                @JsName("once")
                val once = true
            })
        window.addEventListener("unload",
            { this.unbind() },
            object {
                @JsName("once")
                val once = true
            })
        
    }
}

// vi: se ts=4 sw=4 et:
