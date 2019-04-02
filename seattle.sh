#!/usr/bin/env bash
curl -X POST -H "Content-Type: application/json" -d '{"city": "Seattle"}' `fn inspect fn --endpoint demo fn-weather`
