#!/bin/bash
ps -ef | grep jmeter
kill -9 `ps aux | grep jmeter | grep -v grep | awk '{print $2}'`
sleep 3s
ps -ef | grep jmeter
echo "stop successfully"