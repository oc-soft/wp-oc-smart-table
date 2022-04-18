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
import react.Children
import react.helper.toReactElement

/**
 * manage setting
 */
class Settings {

    /**
     * class instance
     */
    companion object {

        /**
         * update inspector controls
         */
        fun updateInspectorConrols(
            settings: dynamic,
            elem: ReactElement<PropsWithChildren>): 
                ReactElement<PropsWithChildren> {

            return elem.props.children?.let {
                var found = false
                val children = Children.map(it) {
                    toReactElement(it)?.let {
                        if (it.type == 
                            wordpress.blockEditor.InspectorControls) {
                            found = true
                            appendSmartSettings(settings, 
                                it as ReactElement<PropsWithChildren>) 
                        } else {
                            it
                        }
                    }?: it    
                }
                if (found) {
                    react.cloneElement(elem, js("undefined"), *children!!)
                } else {
                    elem
                }
            }?: elem
        }

        /**
         * append smart setting panels
         */
        fun appendSmartSettings(
            settings: dynamic,
            controlContainer: ReactElement<PropsWithChildren>):
                ReactElement<PropsWithChildren> {
            val panels = createSettingPanels(settings)

            val controls = ArrayList<react.ReactNode?>()

            Children.forEach(controlContainer.props.children) {
                controls.add(it)
            }
            controls.addAll(panels)
            val controlArray = controls.toTypedArray()

            return react.cloneElement(
                controlContainer, js("undefined"), *controlArray) 
                as ReactElement<PropsWithChildren>
            
        }
        /**
         * create smart table setting panes
         */
        fun createSettingPanels(
            settings: dynamic): Array<react.ReactNode> {
            val attr = settings.attributes
            val inheritColorsAndStyles = 
                attr.ocSmartTableInheritColorsAndStyles


            val changeOnColorsAndStyles:()-> Unit = {
                handleChangeOnInheritColorsAndStyles(settings)
            }

            val settingBody = wordpress.element.createElement(
                wordpress.components.PanelBody,
                object {
                    @JsName("title")
                    val title = wordpress.i18n.gettext(
                        "Smart table setting",
                        "oc-smart-table")
                }, 
                wordpress.element.createElement(
                    wordpress.components.ToggleControl,
                    object {
                        @JsName("label")
                        val label = wordpress.i18n.gettext(
                            "Inherit colors and styles",
                            "oc-smart-table")
                        @JsName("checked")
                        val checked = inheritColorsAndStyles
                        @JsName("onChange")
                        val onChange = changeOnColorsAndStyles
                    },
                    null
                )) as react.ReactNode
            return arrayOf(settingBody)
        }

        /**
         * handle change event for inherit colors and styles toggle control.
         */
        fun handleChangeOnInheritColorsAndStyles(
            settings: dynamic) {
            val attr = settings.attributes
            val inheritColorsAndStyles =
                attr.ocSmartTableInheritColorsAndStyles
            val setAttributes = settings.setAttributes

            setAttributes(object {
                @JsName("ocSmartTableInheritColorsAndStyles")
                val ocSmartTableInheritColorsAndStyles = !inheritColorsAndStyles
            })
            
        }
    }
}

// vi: se ts=4 sw=4 et:
