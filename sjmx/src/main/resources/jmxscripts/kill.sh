#!/bin/bash

ps -ef | grep $thread_name | grep -v grep | awk '{print "kill -9 "$2}' | sh
