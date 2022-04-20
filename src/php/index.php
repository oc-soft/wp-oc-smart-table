<?php
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

/**
 * Plugin Name: OC Smart Table (free)
 * Plugin URI: https://oc-soft.net/en/oc-smart-table/
 * Description: Your page converts a table into a list automatically, if a viewer usees small display device. You can use this plugin with table block editor.
 * Author: Toshiyuki Ogawa
 * Version: 0.1.1
 * Licence: Apache License 2.0
 * Licence URL: https://www.apache.org/licenses/LICENSE-2.0
 * Requires at least: 5.0 
 * Requires PHP: 5.4
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
    implode('', [plugin_dir_url(__FILE__), 'js']),
    implode('', [plugin_dir_url(__FILE__), 'css']),
    plugin_dir_path(__FILE__));


// vi: se ts=4 sw=4 et:
