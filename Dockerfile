# Use a lightweight Nginx (Alpine) image as the base
FROM nginx:alpine

# Copy all your application files (HTML, CSS, etc.) from the Jenkins workspace 
# into the Nginx default web root directory inside the container.
COPY . /usr/share/nginx/html

# Document that the container will listen on port 80 at runtime
EXPOSE 80

# Default command to start the Nginx web server
CMD ["nginx", "-g", "daemon off;"]
