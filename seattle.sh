#!/usr/bin/env bash
export http_proxy=proxy.orcltest1.com:80
curl -X POST -H "Content-Type: application/json" -d '{"city": "Seattle"}' `fn inspect fn --endpoint demo fn-weather`
