#!/bin/bash
ps -ef | grep "jmeter" | grep -v "cgroup" | grep -v "grep" | awk '{print $2}' |wc -l

