# Terraform play

### (Very good example) Two-Tier AWS Architecture
* https://github.com/terraform-providers/terraform-provider-aws/tree/master/examples/two-tier

### Useful Commands
```
terraform init;
terraform plan;
terraform graph | dot -Tsvg > graph.svg;
terraform show;
terraform apply; (then check http://localhost:8080)
terraform apply --auto-approve;
terraform apply -var "subnet_prefix=10.0.1.1";
terraform apply -var-file xxx.tfvars;
terraform refresh; (refresh states, generate outputs safely)
terraform destroy;
terraform destroy -target docker_container;
```

### Folder Structure
* Terraform will load all *.tf under current folder and then execute.
* We can split main.tf into many files like main + network + instances. We can also merge all variables + outputs into main.tf. (But don't declare lots of variables in *.tf. List them nicely in *.tfvars -- same format as using -var arguments.)

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