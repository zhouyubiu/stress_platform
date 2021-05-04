#!/bin/bash

ps -ef |grep $2 |grep -v grep |awk '{print $2}'
