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
