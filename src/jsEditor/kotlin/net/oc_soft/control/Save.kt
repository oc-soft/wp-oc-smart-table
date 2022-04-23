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

import kotlin.js.Json
import kotlinx.browser.window

import react.ReactElement
import react.PropsWithChildren
import react.Children

import net.oc_soft.Codec

/**
 * save operation for table edit
 */
class Save {

    /**
     * run saving
     */
    fun run(
        settings: dynamic,
        originalTableEdit: dynamic): dynamic {
        var savedElement = originalTableEdit.save(settings)
            as ReactElement<PropsWithChildren>

        val attr = settings.attributes
        val props = js("{}")
        if (attr.ocSmartTableInheritColorsAndStyles) {
            val colorClassesAndStyles = 
                wordpress.blockEditor.getColorClassesAndStyles(attr)

            var colorClassesAndStylesStr = Codec.jsonToHtmlAttr(
                window,
                colorClassesAndStyles as Json)

            if (colorClassesAndStylesStr.isNotEmpty()) {
                props["data-oc-smart-table-color-style"] = 
                    colorClassesAndStylesStr
                
            }
        }

        if (props["data-oc-smart-table-color-style"]) {
            savedElement = react.cloneElement(
                savedElement, props, *Children.toArray(
                    savedElement.props.children))
        }

        return savedElement
    }
}

// vi: se ts=4 sw=4 et:
