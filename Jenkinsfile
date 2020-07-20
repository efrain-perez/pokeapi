pipeline {
    agent any

    def dockerImage

    parameters {
        string(name: 'container-version', defaultValue: 'latest', description: 'Version for the container.')
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
                unstash 'targetfiles'
                echo 'Building docker container.'
                dockerImage = docker.build("efrainperez/pokeapi:${container-version}", ' .')
            }
        }
        stage('Push image') {
            steps {
                docker.withRegistry('https://registry-1.docker.io/v2/', 'docker-hub-credentials') {
                    dockerImage.push()
                }
            }
        }
    }
}