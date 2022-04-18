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
            // childrenNew.add(createModal(settings))
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
            
        }
        return wordpress.element.createElement(wordpress.components.Modal,
            object {
                @JsName("onRequestClose")
                val onRequestCose = onClose
            },
            wordpress.element.createElement(
                wordpress.components.Button,
                    object {
                        @JsName("text") 
                        val text = "OK"
                        @JsName("onClick")
                        val onClick = onClickToClose
                    }, 
            null)
        ) as react.ReactNode
    }

}

// vi: se ts=4 sw=4 et:
