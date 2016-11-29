#!/bin/sh

#cp -p $* $*.bak.`date +\%Y\%m\%d`;
for var in "$@"
  do
     cp -p $var $var.bak.`date +\%Y\%m\%d`;
  done

