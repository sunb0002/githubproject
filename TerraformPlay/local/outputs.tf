output "users" {
  value = "${var.users}"
}

output "stands" {
  value = "${var.stands}"
}

# Get the "computed" values from apply phase
output "container_info" {
  value = ["IP and network data listed below",
    "${docker_container.foo.ip_address}",
    "${docker_container.foo.network_data}",
  ]
}
