# Use Linux logrotate
# (sestatus) SELinux is enabled in this server, use this script to bypass the logrotate f_context restriction
logs_apache_conf=/home/lalala/logs/apacherotate.conf
/usr/sbin/logrotate -f -v "$logs_apache_conf"
