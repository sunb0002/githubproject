# Terraform play

### (Very good example) Two-Tier AWS Architecture
* https://github.com/terraform-providers/terraform-provider-aws/tree/master/examples/two-tier

### Useful Commands
```
terraform init;
terraform plan;
terraform graph | dot -Tsvg > graph.svg;
terraform show;
terraform apply; (see http://localhost:8080)
terraform destroy;
```


.
├── README.md
├── ec2
├── local
│   ├── main.tf
│   ├── outputs.tf
│   ├── terraform.tfstate
│   ├── terraform.tfstate.backup
│   ├── terraform.tfvars
│   └── variables.tf
├── mac_expose_docker.sh
└── vpc