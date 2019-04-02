#!/usr/bin/env bash
set -e

fn update app demo --syslog-url tcp://logs3.papertrailapp.com:24554
sudo -E fn deploy --app demo --no-bump
