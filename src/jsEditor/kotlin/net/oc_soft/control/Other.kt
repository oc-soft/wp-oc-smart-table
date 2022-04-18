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

import kotlinx.js.Symbol

import react.ReactElement
import react.PropsWithChildren
import react.Children

import react.helper.toReactElement

import net.oc_soft.Icons
import net.oc_soft.action.Entity

/**
 * manage other menu in toolbar
 */
class Other {

    /**
     * class instance
     */
    companion object {
        /**
         * update dropdown
         */
        fun updateDropdown(
            settings: dynamic,
            entity: Entity,
            elem: ReactElement<PropsWithChildren>):
                ReactElement<PropsWithChildren> {
            return updateFragment(settings, entity, elem)
        }

        /**
         * find react fragment
         */
        fun updateFragment(
            settings: dynamic,
            entity: Entity,
            reactNode: ReactElement<PropsWithChildren>): 
                ReactElement<PropsWithChildren> {
            var updated = false
            val children = Children.map(reactNode.props.children) {
                toReactElement(it)?.let {
                    val elemType = it.type
                    val fragmentType = Symbol.`for`("react.fragment")
                    if (elemType == fragmentType) {
                        val other = updateOther(
                            settings,
                            entity,
                            it as ReactElement<PropsWithChildren>)
                        updated = it != other
                        other
                    } else {
                        it
                    }
                }?: it
            }
            return if (updated) {
                react.cloneElement(reactNode, js("undefined"), *children!!)
            } else {
                reactNode
            } 
        }


        /**
         * update other menu
         */
        fun updateOther(
            settings: dynamic,
            entity: Entity,
            reactNode: ReactElement<PropsWithChildren>):
            ReactElement<PropsWithChildren> {
            var result: ReactElement<PropsWithChildren>? = null
            result = reactNode.props.children?.let {
                var updated = false
                val children = Children.map(it) {
                    toReactElement(it)?.let {
                        val anElement = it
                        getGroup(it)?.let {
                            if ("other" == it) {
                                val controlContainer = updateControlContainer(
                                    settings, entity,
                                    anElement
                                        as ReactElement<PropsWithChildren>)
                                updated = controlContainer != anElement
                                controlContainer 
                            } else {
                                anElement
                            }
                        }?: it
                    }?: it
                }
                if (updated) {
                    react.cloneElement(reactNode, js("undefined"), *children!!)
                        as ReactElement<PropsWithChildren>
                } else {
                    reactNode
                }
                
            }?: reactNode
            return result
        }

        /**
         * get group
         */
        fun getGroup(element: ReactElement<*>): String? {
            val props = element.props
            var result: String? = null
            if (js("'group' in props")) {
                val groupValue = js("props.group") 
                if (groupValue is String) {
                    result = groupValue
                }
            }
            return result
        }  

        /**
         * add control into group element
         */
        fun updateControlContainer(
            settings: dynamic,
            entity: Entity,
            groupOtherElement: ReactElement<PropsWithChildren>):
                ReactElement<PropsWithChildren> {
            return groupOtherElement.props.children?.let {
                var updated = false
                val children = Children.map(it) {
                    toReactElement(it)?.let {
                        if (js("'controls' in it.props")) {
                            val controlContainer = appendSmartController(
                                settings, entity,
                                it as ReactElement<PropsWithChildren>)
                            updated = controlContainer != it
                            controlContainer
                        } else {
                            it
                        }
                    }?: it
                }
                if (updated) {
                    react.cloneElement(groupOtherElement, js("undefined"),
                        *children!!)
                } else {
                    groupOtherElement
                } 
            }?: groupOtherElement 
        }



        /**
         * append smart controller user interface
         */
        fun appendSmartController(
            settings: dynamic,
            entity: Entity,
            controlContainer: ReactElement<PropsWithChildren>)
            : ReactElement<PropsWithChildren> {

            val controls = js("controlContainer.props.controls") 

            val smTableControls = createSmartTableControls(settings, entity)

            // entity.bindReact()

            smTableControls.forEach {
                controls.push(it)
            }
            return controlContainer
        }

        /**
         * create smart table controls
         */
        fun createSmartTableControls(
            settings: dynamic,
            entity: Entity): Array<Any> {
            val svgData = Icons.smartTableSvg

            val attr = settings.attributes
     
            val title = if (js("attr.ocSmartTable == true")) {
                wordpress.i18n.gettext(
                    "Disable smart table", "oc-smart-table")
            } else {
                wordpress.i18n.gettext(
                    "Enable smart table", "oc-smart-table")
            }
           
            val control = object {
                @JsName("icon")
                val icon = svgData
                
                @JsName("title")
                val title = title

                @JsName("isDisabled")
                val isDisabled = false

                @JsName("onClick")
                val onClick: ()->Unit = { 
                    handleClickOnIcon(settings)
               }
            } 
            return arrayOf(control)
        }

        /**
         * handle click event on icon
         */
        fun handleClickOnIcon(
            settings: dynamic) {
            val attr = settings.attributes
            val setAttributes = settings.setAttributes
            
             setAttributes(object {
                @JsName("ocSmartTableRequest")
                    val ocSmartTableRequest = true
            })
        }
    }
}

// vi: se ts=4 sw=4 et:
