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
 * smart table application
 */
class OcSmartTable {


    /**
     * smart table instance
     */
    static $instance = null;

    /**
     * classes parent of table
     */
    static $table_container_classes = [
        'wp-block-table', 'oc-smart-table'
    ];
    
    /**
     * classes for html element
     */
    static $table_classes = [];
    

    /**
     * container tag for dl which replaces table row.
     */
    static $container_tag = 'ul';


    /**
     * container element tag for dl which replace table row.
     */
    static $container_element_tag = 'li';

    /**
     * container classes for container tag
     */
    static $container_classes = ['oc-smart-table-container'];

    /**
     * script handle
     */
    static $script_handle = 'oc-smart-table';

    /**
     * script handle for editor
     */
    static $script_editor_handle = 'oc-smart-table-editor';

    /**
     * javascript name
     */
    static $js_script_name = 'oc-smart-table.js';

    /**
     *  javascript editor name 
     */
    static $js_script_editor_name ='oc-smart-table-editor.js';

    /**
     * css style sheet name
     */
    static $css_style_name = 'oc-smart-table.css';

    /**
     * activate plugin
     */
    function activate() {
    }

    /**
     * deactivate plugin 
     */
    function deactivate() {
    }
     

    /**
     * get inline script
     */
    function get_ajax_inline_script() {
        $ajax_url = admin_url('admin-ajax.php');
        $result = "window.oc = window.oc || { }\n"
            . "window.oc.smartTable = window.oc.smartTable || {}\n"
            . "window.oc.smartTable.ajax = window.oc.smartTable.ajax || { }\n"
            . "window.oc.smartTable.ajax.url = '$ajax_url'";
        return $result;
    }

    /**
     * get support size query
     */
    function get_support_size_query() {
        return Config::$instance->get_media_query()['support-query'];
    }

    

    /**
     * create viewer setting inline script
     */
    function create_viewer_setting_inline_script() {
        $insert_dot = function($elem) {
            return ".$elem";
        };

        $container_classes = array_map($insert_dot, 
            self::$table_container_classes); 
        $container_classes = implode('', $container_classes);

        $classes = array_map($insert_dot, self::$table_classes);
        $classes = implode('', $classes);

        $table_query = implode(',', [
            "$container_classes:not([data-oc-smart-table]) table${classes}",
            "figure[data-oc-smart-table=\"true\"] table"
        ]);
        $container_tag = self::$container_tag;
        $container_element_tag = self::$container_element_tag;

        $container_classes = implode(',', 
            array_map(function($elem) {
                return "'$elem'";
            }, self::$container_classes));

        $result = "window.oc.smartTable = window.oc.smartTable || {}\n"
            . "window.oc.smartTable.tableQuery = '$table_query';\n"
            . "window.oc.smartTable.containerTag = '$container_tag';\n"
            . "window.oc.smartTable.containerElementTag = "
                . "'$container_element_tag';\n"
            . "window.oc.smartTable.containerClasses = [$container_classes];\n";
        return $result;
    }
    /**
     * setup style 
     */
    function setup_style($css_dir) {
        wp_register_style(self::$script_handle,
           implode('/', [$css_dir, self::$css_style_name])); 
        wp_enqueue_style(self::$script_handle);
    }
 
    /**
     * setup script
     */
    function setup_script($js_dir,
        $translations_dir) {


        wp_register_script(self::$script_handle,
            implode('/', [$js_dir, self::$js_script_name]),
            [], false);


        wp_add_inline_script(
            self::$script_handle,
            $this->get_ajax_inline_script());

        wp_add_inline_script(
            self::$script_handle,
            $this->create_viewer_setting_inline_script());

        wp_enqueue_script(self::$script_handle);

        wp_set_script_translations(
            self::$script_handle, 
            'oc-smart-table-free', 
            $translations_dir);

    }

    /**
     * setup wordpress administrator mode script
     */
    function setup_admin_script($js_dir,
        $translations_dir) {
        $deps = ['wp-block-library'];
        
        wp_register_script(self::$script_editor_handle,
            implode('/', [$js_dir, self::$js_script_editor_name]),
            $deps, 
            false, true);

 
    }

    /**
     * setup block editor scripts script
     */
    function setup_block_editor_scripts($js_dir,
        $translations_dir) {

        wp_enqueue_script(self::$script_editor_handle);

        wp_set_script_translations(
            self::$script_editor_handle,
            'oc-smart-table-free', 
            $translations_dir);

        add_filter('load_script_textdomain_relative_path',
            function($relative, $src) {
                return $relative;
            }, 10, 2);
    }



    
    /**
     * handle init event
     */
    function on_init(
        $js_dir,
        $css_dir,
        $translations_dir) {


        add_action('wp', function() use($js_dir, $css_dir, $translations_dir) {
            $this->setup_style($css_dir);
            $this->setup_script($js_dir, $translations_dir);

        });

        add_action('admin_init', function() use($js_dir, $translations_dir) {
            $this->setup_admin_script($js_dir, $translations_dir);
        });
        add_action('enqueue_block_editor_assets', function() 
            use($js_dir, $translations_dir) {
            $this->setup_block_editor_scripts($js_dir, $translations_dir);
        });
    }




    /**
     * start plugin
     */
    function run(
        $js_dir,
        $css_dir,
        $translations_dir) {

        add_action('init', function() 
            use($js_dir, $css_dir, $translations_dir) {
            $this->on_init($js_dir, $css_dir, $translations_dir);
        });
    }
}

OcSmartTable::$instance = new OcSmartTable;

// vi: se ts=4 sw=4 et:
