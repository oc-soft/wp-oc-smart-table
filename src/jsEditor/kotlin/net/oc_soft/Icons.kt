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

import wordpress.primitives.SVG
import wordpress.primitives.G
import wordpress.primitives.Path


/**
 * icons
 */
class Icons {
    /**
     * class instance
     */
    companion object {

        val smartTableSvg: dynamic
            get() {
                val paths = arrayOf(
                    "M 20,5 H 10.5 V 6 H 20 " + 
                        "M 9.5,5 H 4 V 6 H 9.5 Z " + 
                        "M 20,3 H 10.5 V 4 H 20 Z " + 
                        "M 4,3 V 4 H 9.5 V 3 Z " + 
                        "M 3,2 H 21 V 7 H 3 Z",
                    "M 2 14 L 10 14 L 10 15 L 2 15 Z",
                    "M 8 16.5 L 21 16.5 L 21 17.5 L 8 17.5 Z",
                    "M 2 19 L 10 19 L 10 20 L 2 20 Z",
                    "M 8 21.5 L 21 21.5 L 21 22.5 L 8 22.5 Z",
                    "M 10.5 13 L 11 13 L 11 10.5 L 11.5 10.5 " +
                        "L 10.5 9.5 L 9.5 10.5 L 10 10.5 L 10 13", 
                    "M 14 9.5 L 13.5 9.5 L 13.5 12 L 13 12 " +
                        "L 14 13 L 15 12 L 14.5 12 L 14.5 9.5"
                ) 
                val pathData = object {
                    @JsName("d")
                    val d = paths.joinToString(" ")
                } 

                val path = wordpress.element.createElement(
                    Path, pathData, null)
                return wordpress.element.createElement(
                    SVG, 
                    object {
                        @JsName("viewBox")
                        val viewBox = "0 0 24 24"
                        @JsName("xmlns")
                        val xmlns = "http://www.w3.org/2000/svg"
                    }, path)
                
            }
    }
}


// vi: se ts=4 sw=4 et:
