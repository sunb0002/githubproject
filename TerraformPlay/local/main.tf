# Configure the Docker provider
provider "docker" {
  host = "unix:///var/run/docker.sock"
}

# Create a container
resource "docker_container" "foo" {
  image   = "${docker_image.dockercloud.latest}"
  name    = "foo_container_by_tf"
  restart = "always"

  ports {
    internal = 80
    external = 8080
    ip       = "127.0.0.1"
  }
}

resource "docker_image" "dockercloud" {
  name = "dockercloud/hello-world:latest"
}
