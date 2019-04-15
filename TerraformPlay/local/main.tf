# Configure the Docker provider
provider "docker" {
  host = "tcp://localhost:2375/"
}

# Create a container
resource "docker_container" "foo" {
  image   = "dockercloud/hello-world:latest"
  name    = "foo_container_by_tf"
  restart = "always"

  ports {
    internal = 80
    external = 8080
    ip       = "127.0.0.1"
  }
}
