#!/usr/bin/env bash

mvn clean install
/etc/init.d/lighttpd start
echo "Test report is available by link http://127.0.0.1:8082"
tail -f testReport.txt