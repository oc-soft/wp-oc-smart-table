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

import react.ReactElement
import react.PropsWithChildren
import react.Children
import react.helper.toReactElement

/**
 * table helper library
 */
class Table {

    /**
     * class instance
     */
    companion object {
        /**
         * find table element
         */
        fun findTableElementInReact(
            tableContainer :ReactElement<PropsWithChildren>): 
                ReactElement<*>? {
            var result: ReactElement<*>? = null 
            tableContainer.props.children?.let {
                Children.forEach(it) {
                    toReactElement(it)?.let {
                        if (js("'table' == it.type")) {
                            result = it as ReactElement<*>
                        }
                    }
                }
            } 
            return result
        }
    }
}

// vi: se ts=4 sw=4 et:
