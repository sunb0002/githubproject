#!/usr/bin/python

import sys

i = 0
N = 30
lines = []
with open("uniqueHubid.csv", "r") as f:
    for line in f:

        lines.append('\\\'')
        lines.append(line.strip())
        lines.append('\\\',')

        i += 1
        if (i % N == 0) or (not line):
            # close the line
            s = ''.join(lines)
            s = s[:-1]
            print '(' + s+')'
            lines = []


s = ''.join(lines)
s = s[:-1]
print '(' + s+')'
