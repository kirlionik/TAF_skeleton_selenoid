#!/usr/bin/env bash

mvn clean install
echo "Test report will be available by link http://127.0.0.1:8082"
lighttpd -D -f /etc/lighttpd/lighttpd.conf 2>&1