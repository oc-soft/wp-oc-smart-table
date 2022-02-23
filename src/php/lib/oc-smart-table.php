<?php

/**
 * pdf viwer
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
     * javascript name
     */
    static $js_script_name = 'oc-smart-table.js';

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
        $result = <<<EOT
window.oc = window.oc || { }
window.oc.smartTable = window.oc.smartTable || {}
window.oc.smartTable.ajax = window.oc.smartTable.ajax || { }
window.oc.smartTable.ajax.url = '$ajax_url'
EOT;
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

        $table_query = "$container_classes table${classes}";
        $container_tag = self::$container_tag;
        $container_element_tag = self::$container_element_tag;

        $container_classes = implode(',', 
            array_map(function($elem) {
                return "'$elem'";
            }, self::$container_classes));


        $support_size_query = $this->get_support_size_query();
        $result = <<<EOT
window.oc.smartTable = window.oc.smartTable || {}
window.oc.smartTable.tableQuery = '$table_query';
window.oc.smartTable.containerTag = '$container_tag';
window.oc.smartTable.containerElementTag = '$container_element_tag';
window.oc.smartTable.containerClasses = [$container_classes];
EOT;
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
    function setup_script($js_dir) {
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

    }


    
    /**
     * handle init event
     */
    function on_init(
        $js_dir,
        $css_dir) {
        add_action('wp', function() use($js_dir, $css_dir) {
            $this->setup_style($css_dir);
            $this->setup_script($js_dir);

        });
    }




    /**
     * start plugin
     */
    function run(
        $js_dir,
        $css_dir) {


        add_action('init', function() use($js_dir, $css_dir) {
            $this->on_init($js_dir, $css_dir);
        });
    }
}

OcSmartTable::$instance = new OcSmartTable;

// vi: se ts=4 sw=4 et:
