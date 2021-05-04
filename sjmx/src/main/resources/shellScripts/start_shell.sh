#!/bin/bash

nohup ps -ef | grep $2 |grep -v grep | grep -v "start_shell.sh"|awk '{print $2}' |xargs kill - &
nohup sh $1 -n -t $2 > $3 2>&1 &
sleep 1
ps -ef | grep $2 |grep -v grep | grep -v "start_shell.sh" |awk '{print $2}'