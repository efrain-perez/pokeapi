pipeline {

    agent any

    parameters {
        string(name: 'container-version', defaultValue: 'latest', description: 'Version for the container.')
    }
    environment {
        registryUrl = "https://registry.hub.docker.com"
        registryUser="efrainperez"
        imageName = "pokeapi"
        registryCredential = 'dockerhub'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building project'
                sh 'mvn clean package'
                stash includes: 'target/*.jar', name: 'targetfiles'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing'
                sh 'mvn clean test'
            }
        }
        stage('Build Image') {
            steps {
                script{
                    unstash 'targetfiles'
                    echo 'Building docker container.'
                    docker.build("${env.registry}:${params.'container-version'}", ' .')
                    docker.withRegistry("${env.registryUrl}/${env.registryUser}", registryCredential) {
                        def customImage = docker.build("${env.registryUrl}/${env.imageName}:${params.'container-version'}")
                        customImage.push()
                        sh "docker rmi --force \$(docker images -q ${customImage.id} | uniq)"
                    }
                }

            }
        }
    }
}