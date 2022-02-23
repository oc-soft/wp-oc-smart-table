<?php

/**
 * Plugin Name: Oc Smart Table
 */
require_once implode('/', [
    plugin_dir_path( __FILE__), 'lib', 'oc-smart-table.php']);
/**
 * activate plugin
 */
function oc_smart_table_activate() {

    OcSmartTable::$instance->activate();
}

/**
 * deactivate plugin
 */
function oc_smart_table_deactivate() {
    OcSmartTable::$instance->deactivate();
}


register_activation_hook(__FILE__, 'oc_smart_table_activate');
register_deactivation_hook(__FILE__, 'oc_smart_table_deactivate');
OcSmartTable::$instance->run(
    implode('/', [plugin_dir_url(__FILE__), 'js']),
    implode('/', [plugin_dir_url(__FILE__), 'css']));


// vi: se ts=4 sw=4 et:
