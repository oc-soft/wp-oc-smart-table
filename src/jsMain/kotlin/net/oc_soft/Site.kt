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
import org.w3c.dom.url.URL
import org.w3c.dom.get

/**
 * manage site related information
 */
class Site {

    /**
     * class instance
     */
    companion object {

        /**
         * request url
         */
        val requestUrl: URL get() =
            URL(window["oc"].smartTable.ajax.url as String)
            
        /**
         * table query
         */
        val tableQuery: String get() =
            window["oc"].smartTable.tableQuery as String

        /**
         * tag contains table replacement element
         */
        val containerTag: String get() =
            window["oc"].smartTable.containerTag as String

        /**
         * tag for container element to contain table row
         */
        val containerElementTag: String get() =
            window["oc"].smartTable.containerElementTag as String

        /**
         * container classes
         */
        val containerClasses: Array<String>
            get() {
                return window["oc"].smartTable.containerClasses as Array<String>
            }
    }
}

// vi: se ts=4 sw=4 et:
