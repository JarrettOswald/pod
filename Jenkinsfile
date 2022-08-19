node {

    def pod

    stage('Loading') {
        git(url: 'https://github.com/JarrettOswald/pod.git', branch: 'master')
    }

    stage('Build image') {
        pod = docker.build("jarrettoswald/pod:v4")
    }

    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
        pod.push()
        }
    }
}