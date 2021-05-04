#!/bin/bash
#ps -ef |grep $2 |grep -v grep |awk '{print $2}' | xargs kill -9


#nohup sh $1 -n -t $2 > $3 2>&1 &
#sleep 10
#echo "--------------------------------------------------"
#ps -ef |grep jmeter
#ps -ef | grep $2 |grep -v grep
#ps -ef | grep $2 |grep -v grep |awk '{print $2}'

#echo "--------------------------------------------------"
#echo "6666"
#echo $2
nohup ps -ef | grep $1 |grep -v grep | grep -v "stop_shell.sh"|awk '{print $2}' |xargs kill -9 &
#echo $2
# shellcheck disable=SC1007
res= ps -ef |grep $1 |grep -v grep | grep -v "stop_shell.sh"
# shellcheck disable=SC2154
if [ "$res" == "" ]
then
  echo true
else
  echo false

fi