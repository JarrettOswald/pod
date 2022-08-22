node {

    def pod

    stage('notification') {
        withCredentials([string(credentialsId: 'botToken', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
        sh  ("""
            curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID}} -d parse_mode=markdown -d text='*${env.JOB_NAME}* *Build* : START'
        """)
        }
    }

    stage('Loading') {
        git(url: 'https://github.com/JarrettOswald/pod.git', branch: 'master')
    }

    stage('Build image') {
        pod = docker.build("jarrettoswald/pod:v1")
    }

    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
        pod.push()
        }
    }

    stage('notification') {
        withCredentials([string(credentialsId: 'botToken', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
        sh  ("""
            curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID}} -d parse_mode=markdown -d text='*${env.JOB_NAME}* *Build* : OK'
        """)
        }
    }
}