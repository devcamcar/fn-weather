#!/usr/bin/env bash
export http_proxy=proxy.orcltest1.com:80

curl -X POST -H "Content-Type: application/json" -d '{"city": "Seattle"}' http://fn.lb.orcltest1.com:90/invoke/01D5A78MS31BT0A780A0000002

