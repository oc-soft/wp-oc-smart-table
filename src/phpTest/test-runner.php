<?php

$options = getopt('i:t:', ['include', 'test-file']);

foreach (['i', 'include'] as $opt_key) {
    if (isset($options[$opt_key])) {
        set_include_path(implode(
            PATH_SEPARATOR, [get_include_path(), $options[$opt_key]]));
    }
}


foreach (['t', 'test-file'] as $opt_key) {
    if (isset($options[$opt_key])) {
        foreach (explode(',', $options[$opt_key]) as $test) {
            echo "run $test\n";
            include "${test}.php";
        }
    }
}


// vi: se ts=4 sw=4 et:
