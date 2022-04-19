
function usage()
{
  echo "sh gen-index.sh [OPTIONS]
generate index.php from index.php.in
  -s [DIR]  Specify source directory
  -a [DIR]  Specify asset directrory
  -h        Display this help"
}

srcdir=.
assetsdir=../assets

while getopts s:a:m:j:h opt; do
  case "$opt" in
    s)
      srcdir=$OPTARG
      ;;
    a)
      assetsdir=$OPTARG
      ;;
    h|?|:)
      usage
      exit 0
      ;;
  esac
done

plugin_name=$(cat ${assetsdir}/plugin-name.txt)
requires_php=$(cat ${assetsdir}/requires-php.txt)
requires_wp=$(cat ${assetsdir}/requires-wp.txt)






# vi: se ts=4 sw=4 et: