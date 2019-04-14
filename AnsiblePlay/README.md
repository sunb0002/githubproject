# Following Ansible officially recommended folder structure

.
├── LICENCE
├── README.md
├── ansible.cfg
├── docker-compose.yml
├── files
├── group_vars
│   ├── all
│   │   └── vars.yml
│   └── dev
│       ├── vars.yml
│       └── vault-vars.yml
├── host_vars
├── hosts
├── roles
│   ├── best-enemy
│   │   └── tasks
│   │       └── main.yml
│   ├── new-stand
│   │   ├── README.md
│   │   ├── handlers
│   │   ├── tasks
│   │   │   └── main.yml
│   │   └── vars
│   └── print-author
│       └── tasks
│           └── main.yml
├── secret.txt
├── site.yml
├── tasks
│   ├── new-stand.yml
│   └── wryyy.yml
└── templates
    └── jojotemplate.j2
    