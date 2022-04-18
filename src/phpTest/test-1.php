<?php



require_once 'lib/oc-smart-table-i18n.php';


$expect = [
    [
        'domain' => 'a-test-data-file',
        'handle' => 'aa0f9105296b04e83ccc759c8b406da6'
    ]
];

$test_locale = 'en_US';

$res = OcSmartTableI18n::$instance->get_registration_param_for_scripts(
    array_map(function($elem) use($test_locale) {
        return $elem['domain'] 
            . '-' . $test_locale 
            . '-' . $elem['handle'] 
            . '.json';
    }, $expect));

if ($expect == $res) {
    echo "ok parsing registration param for script\n";
} else {
    echo "failed parsing registration param\n";
    echo "expect\n";
    var_dump($expect);
    echo "result\n";
    var_dump($res);
}


// vi: se ts=4 sw=4 et:
