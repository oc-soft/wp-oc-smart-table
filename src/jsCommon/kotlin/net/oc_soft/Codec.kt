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
import org.w3c.dom.WindowOrWorkerGlobalScope
import kotlin.js.Json

/**
 * manage encode/decode data
 */
class Codec {


    /**
     * class instance
     */
    companion object {

        /**
         * json to html attribute
         */
        fun jsonToHtmlAttr(
            converter: WindowOrWorkerGlobalScope,
            jsonObj: Json): String {
            return converter.btoa(JSON.stringify(jsonObj))
        }


        /**
         * html attribute to json
         */
        fun htmlAttrToJson(
            converter: WindowOrWorkerGlobalScope,
            htmlAttr: String): Any? {
            return if (htmlAttr.isNotEmpty()) {
                JSON.parse<Any>(converter.atob(htmlAttr))
            } else {
                null
            }
        }
    }
}

// vi: se ts=4 sw=4 et:
