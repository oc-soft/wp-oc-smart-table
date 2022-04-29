package net.oc_soft.action

import org.w3c.dom.events.Event

import react.ReactNode
import react.ReactElement
import react.PropsWithChildren


/**
 * entity action
 */
class Entity {



    /**
     * attach this object into html elements
     */
    fun bind() {
    } 

    /**
     * detach detach this object from html elements
     */
    fun unbind() {
    }

    /**
     * create react element
     */
    fun updateOperation(
        settings: dynamic,
        tableElement: ReactElement<PropsWithChildren>): 
        ReactElement<PropsWithChildren> {
        val props = js("{}")

        val (children, updated) = updateChildren(
            settings,
            tableElement.props.children) 

        return if (updated) {
            react.cloneElement(
                tableElement, props, *children) 
        } else {
            tableElement
        }
    } 

    /**
     * update children
     */
    fun updateChildren(
        settings: dynamic,
        children: dynamic): Pair<Array<react.ReactNode>, Boolean> {
        val attr = settings.attributes

        val childrenNew = ArrayList<react.ReactNode>()
        react.Children.forEach(children) {   
            childrenNew.add(it) 
        }
        var updated = false   
        if (attr.ocSmartTableRequest) {
            childrenNew.add(createTableEnableFragment(settings))
            updated = true
        }
        return Pair(childrenNew.toTypedArray(), updated)
    }

    /**
     * create modal dialog to notify user how to enable smart table.
     */
    fun createTableEnableFragment(
        settings: dynamic): react.ReactNode  { 
        val setAttributes = settings.setAttributes
        val onClose: ()->Unit = { 
            setAttributes(object {
                @JsName("ocSmartTableRequest")
                    val ocSmartTableRequest = false
            })
        }
        val onClickToClose: (Event)->Unit = { 
            setAttributes(object {
                @JsName("ocSmartTableRequest")
                    val ocSmartTableRequest = false
            })
        }

        val classesLabel = wordpress.i18n.gettext("Additional CSS class(es)")
        val advancedLabel = wordpress.i18n.gettext("Advanced")
        val smartTableSelector = "oc-smart-table"

        val msgFmts = arrayOf(
            wordpress.i18n.gettext(
"You put <b>%s</b> into <b>%s</b> at %s, if you use <b>Smart Table</b>.", 
            "oc-smart-table-free"),
            wordpress.i18n.gettext(
"You can buy <b>OC Smart Table</b> standard edition from %s.",
            "oc-smart-table-free"))
        val shopName = wordpress.i18n.gettext("ocsoft.base.shop in BASE",
            "oc-smart-table-free") 

        val shopURL = "https://ocsoft.base.shop/items/62050748"
        val shopLink = "<a href=\"$shopURL\" target=\"_blank\">$shopName</a>"

        val msgs = arrayOf(
            wordpress.i18n.sprintf(msgFmts[0],
                smartTableSelector, classesLabel, advancedLabel),
            wordpress.i18n.gettext(
"You enable easily <b>Smart Table</b> also with <b>OC Smart Table</b> standard edition.",
            "oc-smart-table-free"),
            wordpress.i18n.sprintf(msgFmts[1], shopLink)
        )

        return wordpress.element.createElement(wordpress.components.Modal,
            object {
                @JsName("onRequestClose")
                val onRequestClose = onClose
            },
            wordpress.element.createElement(
                "p",
                object {
                    @JsName("dangerouslySetInnerHTML")
                    val dangerouslySetInnerHTML = object {
                        @JsName("__html")
                        val html = msgs[0]
                    }
                } 
            ),
            wordpress.element.createElement(
                "p",
                object {
                    @JsName("dangerouslySetInnerHTML")
                    val dangerouslySetInnerHTML = object {
                        @JsName("__html")
                        val html = msgs[1]
                    }
                } 
            ),
            wordpress.element.createElement(
                "p",
                object {
                    @JsName("dangerouslySetInnerHTML")
                    val dangerouslySetInnerHTML = object {
                        @JsName("__html")
                        val html = msgs[2]
                    }
                } 
            ),
             wordpress.element.createElement(
                wordpress.components.Flex,
                object {
                    @JsName("justify")
                    val justify = "flex-end"
                },
                wordpress.element.createElement( 
                    wordpress.components.FlexItem,
                    null,
                    wordpress.element.createElement(
                        wordpress.components.Button,
                        object {
                            @JsName("variant")
                            val variant = "primary"

                            @JsName("text") 
                            val text = wordpress.i18n.gettext("Close")
                            @JsName("onClick")
                            val onClick = onClickToClose
                        }, 
                        null)
                )
            )
        ) as react.ReactNode
    }

}

// vi: se ts=4 sw=4 et:
