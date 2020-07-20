pipeline {

    agent any

    parameters {
        string(name: 'container-version', defaultValue: 'latest', description: 'Version for the container.')
    }
    environment {
        registry = "efrainperez/pokeapi"
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
                    docker.build("efrainperez:${params.'container-version'}", ' .')
                }

            }
        }
    }
}