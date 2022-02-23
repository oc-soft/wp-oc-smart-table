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
